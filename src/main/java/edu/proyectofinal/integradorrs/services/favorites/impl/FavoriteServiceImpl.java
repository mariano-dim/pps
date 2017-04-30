/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.proyectofinal.integradorrs.services.favorites.impl;

import edu.proyectofinal.integradorrs.model.Favorite;
import edu.proyectofinal.integradorrs.repositorys.FavoritesRepository;
import edu.proyectofinal.integradorrs.services.favorites.FavoriteService;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ecorsaro
 */
@Service
public class FavoriteServiceImpl implements FavoriteService{

    @Autowired
    private FavoritesRepository favoritesrepository;
    
    @Override
    public Collection<Favorite> getAllFavorite() {
        return favoritesrepository.findAll();
    }

    @Override
    public Collection<Favorite> getByEmail(String email) {
        return favoritesrepository.findByEmail(email);
    }
    
    @Override
    public Favorite getById(String email, String id) {
        return favoritesrepository.findByID(email, id);
    }

    @Override
    public Boolean IsFavorite(String email, String id_update) {
        return (favoritesrepository.findByID(email, id_update) != null);
    }

    @Override
    public Favorite save(Favorite f) {
        favoritesrepository.save(f);
        return f;
    }

    @Override
    public Favorite delete(String email, String id_update) {
        Favorite f = favoritesrepository.findByID(email,id_update);
        favoritesrepository.delete(f);
        return f;
    }
}
