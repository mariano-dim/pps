package edu.proyectofinal.integradorrs.repositorys;
import edu.proyectofinal.integradorrs.model.Update;
import java.util.Collection;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "updates", path = "updates")
public interface UpdatesRepository extends MongoRepository<Update, String> {

    
    /**
     *
     * @param email
     * @return
     */
    @Query("{ 'email' : ?0}")
    public Collection<Update> findByEmail(String email);
   
      /**
     *
     * @param id
     * @return
     */
    @Query("{ 'email' : ?0}")
    public Update findByID(String id);
    
     /**
     *
     * @param email,socialnetwork
     * @return
     */
    @Query("{ 'email' : ?0, 'socialnetwork': ?1 }")
    public Collection<Update> findByEmailandSN(String email, String socialnetwork);
    
    /**
     *
     * @param email,socialnetwork, id
     * @return
     */
    @Query("{ 'email' : ?0, 'socialnetwork': ?1 , 'id': ?2 }")
    public Update findByEmailandSNandID(String email, String socialnetwork, String id);

}