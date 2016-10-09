/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.proyectofinal.integradorrs.services.tweets.impl;

import edu.proyectofinal.integradorrs.model.TweetsModel;
import edu.proyectofinal.integradorrs.repositorys.TweetsRepository;
import edu.proyectofinal.integradorrs.services.tweets.TweetsService;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 *
 * @author MarianoAndres
 */
@Service
public class TweetsServiceImpl implements TweetsService {

    @Autowired
    private TweetsRepository repository;

    @Override
    public Collection<TweetsModel> getAllTweets() {
        Collection<TweetsModel> result = (Collection<TweetsModel>) repository.findAll();
        return result;

    }

}
