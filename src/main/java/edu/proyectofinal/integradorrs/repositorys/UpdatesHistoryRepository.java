package edu.proyectofinal.integradorrs.repositorys;
import edu.proyectofinal.integradorrs.model.UpdateHistory;
import java.util.Collection;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "updateshistory", path = "updateshistory")
public interface UpdatesHistoryRepository extends MongoRepository<UpdateHistory, String> {

    
    /**
     *
     * @param email
     * @return
     */
    @Query("{ 'email' : ?0}")
    public Collection<UpdateHistory> findByEmail(String email);
   
      /**
     *
     * @param id
     * @return
     */
    @Query("{ 'email' : ?0}")
    public UpdateHistory findByID(String id);
    
     /**
     *
     * @param email,socialnetwork
     * @return
     */
    @Query("{ 'email' : ?0, 'socialnetwork': ?1 }")
    public Collection<UpdateHistory> findByEmailandSN(String email, String socialnetwork);
    
    /**
     *
     * @param email,id 
     * @param sort 
     * @return
     */
    @Query("{ 'email' : ?0, 'post': ?1 }")
    public Collection<UpdateHistory> findByEmailandID(String email, String post,org.springframework.data.domain.Sort sort);
    
    /**
     *
     * @param email,socialnetwork, id
     * @return
     */
    @Query("{ 'email' : ?0, 'socialnetwork': ?1 , 'id': ?2 }")
    public UpdateHistory findByEmailandSNandID(String email, String socialnetwork, String id);

}