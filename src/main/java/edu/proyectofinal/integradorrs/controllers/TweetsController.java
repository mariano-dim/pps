/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.proyectofinal.integradorrs.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.proyectofinal.integradorrs.model.TweetsModel;
import edu.proyectofinal.integradorrs.services.tweets.TweetsService;
import java.util.Collection;

/**
 *
 * @author MarianoAndres
 */
@RestController
@RequestMapping("/api/tweets")
public class TweetsController extends AbstractController<TweetsModel> {

    @Autowired
    private TweetsService tweetsService;
                          

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public ResponseEntity<Collection<TweetsModel>> getAll() {

        System.out.println("getAll");

        Collection<TweetsModel> tweetsModel = tweetsService.getAllTweets();

        return super.collectionResult(tweetsModel);

    }

}
