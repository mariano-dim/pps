/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.proyectofinal.integradorrs.services.analytics;

import edu.proyectofinal.integradorrs.model.Dashboard;
import edu.proyectofinal.integradorrs.model.FollowersHistory;
import edu.proyectofinal.integradorrs.services.facebook.*;
import edu.proyectofinal.integradorrs.model.Update;
import facebook4j.ResponseList;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author Emi
 */
public interface AnalyticsService {

    
    
     public Collection<Update> getUpdatesReport(String email);
     
      void SaveFollowers();
      
      void SaveUpdatesHistory();

    public Dashboard getDashboard(String email, Date Desde, Date Hasta);
    
    public Collection<FollowersHistory> getFollowersHistory(String Email, String SocialNetwork);
    
}
