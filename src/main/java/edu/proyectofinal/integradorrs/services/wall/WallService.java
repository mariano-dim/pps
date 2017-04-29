/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.proyectofinal.integradorrs.services.wall;

import edu.proyectofinal.integradorrs.model.UnifiedUpdate;
import edu.proyectofinal.integradorrs.model.Update;
import java.util.Collection;

/**
 *
 * @author Emi
 */
public interface WallService {

     public Collection<UnifiedUpdate> getUnifiedWall(String email);    
}
