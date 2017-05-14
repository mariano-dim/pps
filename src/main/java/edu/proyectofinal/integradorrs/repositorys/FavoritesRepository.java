package edu.proyectofinal.integradorrs.repositorys;
import edu.proyectofinal.integradorrs.model.Favorite;
import edu.proyectofinal.integradorrs.model.Update;
import java.util.Collection;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "favorite", path = "favorite")
public interface FavoritesRepository extends MongoRepository<Favorite, String> {

    
    /**
     *
     * @param email
     * @return
     */
    @Query("{ 'email' : ?0}")
    public Collection<Favorite> findByEmail(String email);
   
      /**
     *
     * @param email
     * @param id_update
     * @return
     */
    @Query("{ 'email' : ?0, 'id_update': ?1 }")
    public Favorite findByID(String email, String id_update);
    
     /**
     *
     * @param email,socialnetwork
     * @return
     */
    @Query("{ 'email' : ?0, 'socialnetwork': ?1 }")
    public Collection<Favorite> findByEmailandSN(String email, String socialnetwork);
    
    /**
     *
     * @param email,socialnetwork, id
     * @return
     */
    @Query("{ 'email' : ?0, 'socialnetwork': ?1 , 'id_update': ?2 }")
    public Favorite findByEmailandSNandID(String email, String socialnetwork, String id_update);

}