package edu.proyectofinal.integradorrs.controllers;

import edu.proyectofinal.integradorrs.comparators.UpdatesHistorySort;
import edu.proyectofinal.integradorrs.model.Favorite;
import edu.proyectofinal.integradorrs.model.FollowersHistory;
import edu.proyectofinal.integradorrs.model.UnifiedUpdate;
import edu.proyectofinal.integradorrs.model.Update;
import edu.proyectofinal.integradorrs.model.UpdateHistory;
import edu.proyectofinal.integradorrs.repositorys.UpdatesHistoryRepository;
import edu.proyectofinal.integradorrs.services.analytics.AnalyticsService;
import edu.proyectofinal.integradorrs.services.favorites.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.validation.annotation.Validated;
import edu.proyectofinal.integradorrs.services.wall.WallService;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import static org.springframework.data.domain.Sort.Direction.DESC;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@RestController
@RequestMapping("/updates/history")
public class UpdatesHistoryController extends AbstractController<UpdateHistory> {
    
    @Autowired
    private UpdatesHistoryRepository aRepo;

    @RequestMapping(method = RequestMethod.GET, value="/general/{email:.+}")
    public ResponseEntity<Collection<UpdateHistory>> getGeneralHistory(@Validated @PathVariable("email") String email
            , String fechaDesde, String fechaHasta) throws ParseException {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("Muro unificado de publicaciones desde Social Focus");
        System.out.println("Inicio: " + format.format(new Date()));

        Date desde = format.parse(fechaDesde.substring(6, 10) + "-" + fechaDesde.substring(3, 5)+ "-" + fechaDesde.substring(0, 2)+ " " + fechaDesde.substring(11, 16)+":00");
        Date hasta = format.parse(fechaHasta.substring(6, 10) + "-" + fechaHasta.substring(3, 5)+ "-" + fechaHasta.substring(0, 2)+ " " + fechaHasta.substring(11, 16)+":00");
        
        Collection<UpdateHistory> aHistory = aRepo.findByEmail(email);
        aHistory.removeIf(p-> (p.getMeasurementDate().compareTo(desde) <0 || p.getMeasurementDate().compareTo(hasta)>0));
        
        
        System.out.println("Fin: " + format.format(new Date()));
        return super.collectionResult(aHistory);
    }    
    
        @RequestMapping(method = RequestMethod.GET, value="/id/{email:.+}")
    public ResponseEntity<Collection<UpdateHistory>> getIDHistory(@Validated @PathVariable("email") String email
                ,String id, String fechaDesde, String fechaHasta) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("Muro unificado de publicaciones desde Social Focus");
        System.out.println("Inicio: " + dateFormat.format(new Date()));
            Comparator<UpdateHistory> comparator = new Comparator<UpdateHistory>() {
         @Override
         public int compare(UpdateHistory left, UpdateHistory right) {
             return left.getMeasurementDate().toInstant().compareTo(right.getMeasurementDate().toInstant());
         }
     };
        
        Date desde = dateFormat.parse(fechaDesde.substring(6, 10) + "-" + fechaDesde.substring(3, 5)+ "-" + fechaDesde.substring(0, 2)+ " " + fechaDesde.substring(11, 16)+":00");
        Date hasta = dateFormat.parse(fechaHasta.substring(6, 10) + "-" + fechaHasta.substring(3, 5)+ "-" + fechaHasta.substring(0, 2)+ " " + fechaHasta.substring(11, 16)+":00");
        Sort sort = new Sort(Direction.ASC,"measurementDate");
        Collection<UpdateHistory> aHistory = aRepo.findByEmailandID(email, id,sort);
        aHistory.removeIf(p-> (p.getMeasurementDate().compareTo(desde) <0 || p.getMeasurementDate().compareTo(hasta)>0));
        //Collections.sort((List<UpdateHistory>) aHistory);
        
        System.out.println("Fin: " + dateFormat.format(new Date()));
        return super.collectionResult(aHistory);
    }    

 
    
}
