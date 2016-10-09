/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.proyectofinal.integradorrs.services.tweets;

import edu.proyectofinal.integradorrs.model.TweetsModel;
import java.util.Collection;

/**
 *
 * @author MarianoAndres
 */
public interface TweetsService {
    
    	Collection<TweetsModel> getAllTweets();
    
}
