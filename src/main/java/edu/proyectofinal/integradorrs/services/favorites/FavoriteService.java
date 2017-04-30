package edu.proyectofinal.integradorrs.services.favorites;

import edu.proyectofinal.integradorrs.model.Favorite;
import java.util.Collection;

public interface FavoriteService {

	Collection<Favorite> getAllFavorite();
        
	Collection<Favorite> getByEmail(String email);
        
        Favorite getById(String email, String id);
      
        Boolean IsFavorite(String email, String id_update);
        
	Favorite save(Favorite f);
        
        Favorite delete(String email, String id_update);
}