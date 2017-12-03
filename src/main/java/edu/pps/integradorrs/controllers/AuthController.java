package edu.pps.integradorrs.controllers;

import com.google.common.base.Strings;
import edu.pps.integradorrs.services.Email.EmailServiceSocialFocus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.apache.commons.lang3.ArrayUtils;

import edu.pps.integradorrs.model.Usuario;
import edu.pps.integradorrs.model.Llave;
import edu.pps.integradorrs.model.Puerta;

import edu.pps.integradorrs.services.usuario.UsuarioService;
import edu.pps.integradorrs.services.llave.LlaveService;
import edu.pps.integradorrs.services.puerta.PuertaService;
import edu.pps.integradorrs.services.log.LogService;

import it.ozimov.springboot.mail.service.exception.CannotSendEmailException;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import edu.pps.integradorrs.exceptions.EmptyResultException;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.Collection;

import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@RestController
@RequestMapping("/api/usuario")
public class AuthController extends AbstractController<Usuario> {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private LlaveService llaveService;

    @Autowired
    private PuertaService puertaService;

    @Autowired
    private LogService logService;

    @Autowired
    public EmailServiceSocialFocus emailService;

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public ResponseEntity<Collection<Usuario>> getAll() {

        System.out.println("getAll");

        Collection<Usuario> usuarios = usuarioService.getAllUsuarios();

        return super.collectionResult(usuarios);

    }

    // fix
    // http://stackoverflow.com/questions/3526523/spring-mvc-pathvariable-getting-truncated
    @RequestMapping(method = RequestMethod.GET, value = "/email/{email:.+}")
    public ResponseEntity<Usuario> getByEmail(@Validated @PathVariable("email") String email) {

        System.out.println("getByEmail");
        System.out.println("Email: " + email);

        Usuario usuario = usuarioService.getByEmail(email);

        if (null == usuario) {
            throw new EmptyResultException(Usuario.class);
        }
        return super.singleResult(usuario);

    }

    @RequestMapping(method = RequestMethod.GET, value = "/id/{id}")
    public ResponseEntity<Usuario> getById(@Validated @PathVariable("id") String id) {

        System.out.println("getById");
        System.out.println("Id: " + id);

        Usuario usuario = usuarioService.getById(id);
        if (null == usuario) {
            throw new EmptyResultException(Usuario.class);
        }
        return super.singleResult(usuario);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/")
    public ResponseEntity<Usuario> save(@RequestBody Usuario usuario) {

        usuarioService.save(usuario);

        return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);

    }


    @RequestMapping(method = RequestMethod.PATCH, value = "/addkeytouser/usuarioemail/{usuarioemail:.+}/llavepublicidentification/{llavepublicidentification}")
    public ResponseEntity<Usuario> addKeyToUser(@Validated @PathVariable("usuarioemail") String usuarioEmail,
                                                @Validated @PathVariable("llavepublicidentification") String llavepublicidentification) {

        System.out.println("addKeyToUser");
        System.out.println("usuarioEmail: " + usuarioEmail);
        System.out.println("llavepublicIdentification: " + llavepublicidentification);

        // Busco al usuario, en caso de no encontrarlo doy error
        System.out.println("Buscando Usuario");
        Usuario usuario = usuarioService.getByEmail(usuarioEmail);
        // TODO: retornar la excepcion correcta BusinessException
        if (null == usuario) {
            System.out.println("usuario no encontrado");
            throw new EmptyResultException(Usuario.class);
        }

        // Busco la llave, a traves de su identificacion publica, en caso de no
        // encontrarla doy error
        System.out.println("Buscando Llave");
        Llave llave = llaveService.getByPublicIdentification(llavepublicidentification);
        // TODO: retornar la excepcion correcta BusinessException
        if (null == llave) {
            System.out.println("llave no encontrada");
            throw new EmptyResultException(Puerta.class);
        }
        // verifico si el usuario ya tiene esa llave asignada, en cuyo caso retorno
        System.out.println("Verificando si el usuario posee la llave");
        if (ArrayUtils.contains(usuario.getLlaves(), llavepublicidentification)) {
            System.out.println("el usuario ya posee esa llave");
            return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
        }
        System.out.println("Verificando si la llave se encuentra asociada al usuario");
        if (null != llave.getUsuario() && !llave.getUsuario().isEmpty() && llave.getUsuario().contains(usuarioEmail)) {
            System.out.println("el usuario ya posee esa llave");
            return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
        }
        System.out.println("Asociando llave a usuariio y usuario a llave");
        // Le agrego la llave al usuario
        llave.setUsuario(usuarioEmail);
        llaveService.save(llave);
        // Le asocio el usuario a la llave
        usuarioService.addLlave(usuario.getEmail(), llave.getPublicIdentification());

        // Busco al usuario, para retornar la informacion que se persistio en la db
        Usuario usuarioUpdated = usuarioService.getByEmail(usuarioEmail);
        // TODO: retornar la excepcion correcta BusinessException
        if (null == usuarioUpdated) {
            throw new EmptyResultException(Usuario.class);
        }
        return new ResponseEntity<Usuario>(usuarioUpdated, HttpStatus.OK);

    }

    @RequestMapping(method = RequestMethod.PATCH, value = "/removekeytouser/usuarioemail/{usuarioEmail:.+}/llavepublicidentification/{llavepublicidentification}")
    public ResponseEntity<Usuario> removeKeyToUser(@Validated @PathVariable("usuarioEmail") String usuarioEmail,
                                                   @Validated @PathVariable("llavepublicidentification") String llavepublicidentification) {

        System.out.println("removeKeyToUser");
        System.out.println("usuarioEmail: " + usuarioEmail);
        System.out.println("llavepublicidentification: " + llavepublicidentification);

        // Busco al usuario, en caso de no encontrarlo doy error
        Usuario usuario = usuarioService.getByEmail(usuarioEmail);
        // TODO: retornar la excepcion correcta BusinessException
        if (null == usuario) {
            System.out.println("usuario no encontrado");
            throw new EmptyResultException(Usuario.class);
        }

        // Busco la llave, a traves de su identificacion publica, en caso de no
        // encontrarla doy error
        Llave llave = llaveService.getByPublicIdentification(llavepublicidentification);
        // TODO: retornar la excepcion correcta BusinessException
        if (null == llave) {
            System.out.println("llave no encontrada");
            throw new EmptyResultException(Puerta.class);
        }

        // verifico si el usuario tiene esa llave asignada, en cuyo caso retorno
        if (!ArrayUtils.contains(usuario.getLlaves(), llavepublicidentification)) {
            System.out.println("el usuario no posee esa llave");
            return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
        }

        // Le borro la llave al usuario
        usuarioService.removeLlave(usuarioEmail, llavepublicidentification);

        llave.setUsuario(null);
        llaveService.save(llave);

        // Busco al usuario, para retornar la informacion que se persistio en la db
        Usuario usuarioUpdated = usuarioService.getByEmail(usuarioEmail);
        // TODO: retornar la excepcion correcta BusinessException
        if (null == usuarioUpdated) {
            throw new EmptyResultException(Usuario.class);
        }
        return new ResponseEntity<Usuario>(usuarioUpdated, HttpStatus.OK);

    }


    @RequestMapping(method = RequestMethod.GET, value = "/checkaccess/puertapublicidentification/{puertapublicidentification}/llavepublicidentification/{llavepublicidentification}")
    public ResponseEntity<String> checkAccess(@Validated @PathVariable("puertapublicidentification") String puertaPublicIdentification,
                                              @Validated @PathVariable("llavepublicidentification") String llavePublicIdentification) {

        System.out.println("checkAccess");
        System.out.println("llavepublicidentification:       " + llavePublicIdentification);
        System.out.println("puertapublicidentification:      " + puertaPublicIdentification);


        // Busco la llave, a traves de su identificacion publica, en caso de no
        // encontrarla retorno error
        System.out.println("Buscando Llave");
        Llave llave = llaveService.getByPublicIdentification(llavePublicIdentification);
        // TODO: retornar la excepcion correcta BusinessException
        if (null == llave) {
            System.out.println("Llave no encontrada");
            return new ResponseEntity<String>("ERROR", HttpStatus.OK);
        }

        // Busco la puerta, a traves de su identificacion publica, en caso de no
        // encontrarla retorno error
        System.out.println("Buscando Puerta");
        Puerta puerta = puertaService.getByPublicIdentification(puertaPublicIdentification);
        if (null == puerta) {
            System.out.println("Puerta inexistente");
            return new ResponseEntity<String>("ERROR", HttpStatus.OK);
        }
        // Busco el usuario a traves de la llave, ya que una llave solo puede tener un usuario asociado
        String usuarioEmail = llave.getUsuario();

        // Busco al usuario, en caso de no encontrarlo retorno error
        System.out.println("Buscando Usuario");
        Usuario usuario = usuarioService.getByEmail(usuarioEmail);
        // TODO: retornar la excepcion correcta BusinessException
        if (null == usuario) {
            System.out.println("Usuario no encontrado");
            return new ResponseEntity<String>("ERROR", HttpStatus.OK);
        }

        // Verifico si el usuario tiene esa llave asignada, en cuyo caso retorno error
        System.out.println("Verificando si el usuario tiene asociada la puerta");
        if (!ArrayUtils.contains(usuario.getLlaves(), llavePublicIdentification)) {
            System.out.println("El usuario no tiene asignada esa llave");
            return new ResponseEntity<String>("ERROR", HttpStatus.OK);
        }
        // Verifico si la llave  tiene esa puerta asignada, en cuyo caso retorno error
        System.out.println("Verificando si el Llave tiene asociada la Puerta");
        if (!ArrayUtils.contains(llave.getPuertas(), puertaPublicIdentification)) {
            System.out.println("La llave indicada no posee esa puerta");
            return new ResponseEntity<String>("ERROR", HttpStatus.OK);
        }


        return new ResponseEntity<String>("OK#" + llave.getUsuario(), HttpStatus.OK);

    }

    //@RequestMapping(method = RequestMethod.GET, value = "/hasaccess/usuarioemail/{usuarioemail:.+}/puertapublicidentification/{puertapublicidentification}/llavepublicidentification/{llavepublicidentification}")
    //public ResponseEntity<String> getUsuarioHasAccess(@Validated @PathVariable("usuarioemail") String usuarioEmail,
    //                                                @Validated @PathVariable("puertapublicidentification") String puertaPublicIdentification,
    //                                                @Validated @PathVariable("llavepublicidentification") String llavePublicIdentification) {
//
    //      System.out.println("getUsuarioHasAccess");
    //  System.out.println("usuarioemail: " + usuarioEmail);
    //  System.out.println("llavepublicidentification:       " + llavePublicIdentification);
    //  System.out.println("puertapublicidentification:      " + puertaPublicIdentification);
    //  logService.save("getUsuarioHasAccess");
    //  logService.save("usuarioemail: " + usuarioEmail);
    //  logService.save("llavepublicidentification:       " + llavePublicIdentification);
    //  logService.save("puertapublicidentification:      " + puertaPublicIdentification);
//
    // Busco al usuario, en caso de no encontrarlo retorno error
    //      Usuario usuario = usuarioService.getByEmail(usuarioEmail);
    // TODO: retornar la excepcion correcta BusinessException
    //  if (null == usuario) {
    //      System.out.println("Usuario no encontrado");
    //      return new ResponseEntity<String>("ERROR", HttpStatus.OK);
    //  }

    // Busco la llave, a traves de su identificacion publica, en caso de no
    // encontrarla retorno error
    //  Llave llave = llaveService.getByPublicIdentification(llavePublicIdentification);
    // TODO: retornar la excepcion correcta BusinessException
    //  if (null == llave) {
    //      System.out.println("Llave no encontrada");
    //      return new ResponseEntity<String>("ERROR", HttpStatus.OK);
    //  }

    // Busco la puerta, a traves de su identificacion publica, en caso de no
    // encontrarla retorno error
    //  Puerta puerta = puertaService.getByPublicIdentification(puertaPublicIdentification);
    //  if (null == puerta) {
    //      System.out.println("Puerta no encontrada");
    //      return new ResponseEntity<String>("ERROR", HttpStatus.OK);
    //  }
    // Verifico si el usuario tiene esa llave asignada, en cuyo caso retorno error
    //  if (!ArrayUtils.contains(usuario.getLlaves(), llavePublicIdentification)) {
    //      System.out.println("El usuario no posee esa llave");
    //      return new ResponseEntity<String>("ERROR", HttpStatus.OK);
    //  }
    // Verifico si la llave  tiene esa puerta asignada, en cuyo caso retorno error
    //  if (!ArrayUtils.contains(llave.getPuertas(), puertaPublicIdentification)) {
    //      System.out.println("La llave indicada no posee esa puerta");
    //      return new ResponseEntity<String>("ERROR", HttpStatus.OK);
    //  }


    //  return new ResponseEntity<String>("OK", HttpStatus.OK);

    //}


}
