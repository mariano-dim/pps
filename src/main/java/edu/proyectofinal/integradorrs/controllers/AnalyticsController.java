package edu.proyectofinal.integradorrs.controllers;

import edu.proyectofinal.integradorrs.model.Dashboard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.validation.annotation.Validated;
import edu.proyectofinal.integradorrs.model.Update;
import edu.proyectofinal.integradorrs.services.analytics.AnalyticsService;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@RestController
@RequestMapping("/analytics")
public class AnalyticsController extends AbstractController<Dashboard> {
    
    @Autowired
    private AnalyticsService analyticsService;
    
   /*  @RequestMapping(method = RequestMethod.GET, value="/informe/email/{email:.+}")
    public ResponseEntity<Collection<Update>> getAll(@Validated @PathVariable("email") String email) {

        System.out.println("Informe de publicaciones desde Social Focus");

        Collection<Update> updates = analyticsService.getUpdatesReport(email);

        return super.collectionResult(updates);

    }*/

    @RequestMapping(method = RequestMethod.GET, value="/dashboard/{email:.+}")
    public ResponseEntity<Dashboard> getDashBoard(@Validated @PathVariable("email") String email
            , String fechaDesde, String fechaHasta) throws ParseException { 
        
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("Dashboard de actividad Social Focus");
        Dashboard aDB = new Dashboard();
        Date desde = format.parse(fechaDesde.substring(6, 10) + "-" + fechaDesde.substring(3, 5)+ "-" + fechaDesde.substring(0, 2)+ " " + fechaDesde.substring(11, 16)+":00");
        Date hasta = format.parse(fechaHasta.substring(6, 10) + "-" + fechaHasta.substring(3, 5)+ "-" + fechaHasta.substring(0, 2)+ " " + fechaHasta.substring(11, 16)+":00");
        try{
            aDB = analyticsService.getDashboard(email, desde , hasta);
        }catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        
        return super.Result(aDB);
    }
    
    public static java.util.Date toDate(java.sql.Timestamp timestamp) {
        long milliseconds = timestamp.getTime() + (timestamp.getNanos() / 1000000);
        return new java.util.Date(milliseconds);
    }
    
    @RequestMapping(method = RequestMethod.POST, value="/informe/historico/seguidores")
    public void save() {
        
        analyticsService.SaveFollowers();
    }
    
    @RequestMapping(method = RequestMethod.POST, value="/informe/historico/posteos")
    public void saveUpdatesHistory() {
        
        analyticsService.SaveUpdatesHistory();
    }
}
