package edu.proyectofinal.integradorrs.repositorys;

import java.util.Date;

import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import com.mongodb.WriteResult;

import edu.proyectofinal.integradorrs.model.Usuario;

public class UsuarioRepositoryImpl implements UsuarioRepositoryCustom {

	@Autowired
	MongoTemplate mongoTemplate;

	@Override
	public int updateUsuario(String email, String newEmail, String nombre, String clave) {

		Query query = new Query(Criteria.where("email").is(email));
		Update update = new Update();

		boolean iFlag = false;

		if(!Strings.isNullOrEmpty(newEmail)){
			update.set("email", newEmail) ;
			iFlag = true;
		}
		if(!Strings.isNullOrEmpty(nombre)){
			update.set("nombre", nombre);
			iFlag = true;
		}
		if(!Strings.isNullOrEmpty(clave)){
			update.set("clave", clave) ;
			iFlag = true;
		}

        if (iFlag){
			WriteResult result = mongoTemplate.updateFirst(query, update, Usuario.class);
			if (result != null)
				return result.getN();
			else
				return 0;
		} else{
        	return 0;
		}


	}

    @Override
    public int updateUsuario(String email, String newEmail, String nombre, String clave, int pond_likes, int pond_comments, int pond_shares) {
        
		Query query = new Query(Criteria.where("email").is(email));
		Update update = new Update();

		boolean iFlag = false;

		if(!Strings.isNullOrEmpty(newEmail)){
			update.set("email", newEmail) ;
			iFlag = true;
		}
		if(!Strings.isNullOrEmpty(nombre)){
			update.set("nombre", nombre);
			iFlag = true;
		}
		if(!Strings.isNullOrEmpty(clave)){
			update.set("clave", clave) ;
			iFlag = true;
		}
		update.set("pond_likes", pond_likes) ;
		update.set("pond_comments", pond_comments) ;
                update.set("pond_shares", pond_shares) ;


        if (iFlag){
			WriteResult result = mongoTemplate.updateFirst(query, update, Usuario.class);
			if (result != null)
				return result.getN();
			else
				return 0;
		} else{
        	return 0;
		}

    }

}
