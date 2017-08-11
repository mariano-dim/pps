/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.proyectofinal.integradorrs.comparators;

import edu.proyectofinal.integradorrs.model.Update;
import edu.proyectofinal.integradorrs.model.UpdateHistory;


/**
 *
 * @author ecorsaro
 * @param <UpdateHistory>
 */
public class UpdatesHistorySort implements Comparable<UpdateHistory> {
    public int compare(UpdateHistory left, UpdateHistory right) {
            return left.getMeasurementDate().toInstant().compareTo(right.getMeasurementDate().toInstant());
    }    

    @Override
    public int compareTo(UpdateHistory o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
