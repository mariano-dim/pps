/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.proyectofinal.integradorrs.comparators;

import edu.proyectofinal.integradorrs.model.Update;


/**
 *
 * @author ecorsaro
 * @param <Update>
 */
public class UpdatesSort implements Comparable<Update> {
    public int compare(Update left, Update right) {
            return left.getCreationDate().toInstant().compareTo(right.getCreationDate().toInstant());
    }       

    @Override
    public int compareTo(Update o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
