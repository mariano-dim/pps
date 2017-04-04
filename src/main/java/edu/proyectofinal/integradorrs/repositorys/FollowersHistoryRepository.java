package edu.proyectofinal.integradorrs.repositorys;
import edu.proyectofinal.integradorrs.model.FollowersHistory;
import edu.proyectofinal.integradorrs.model.Update;
import java.util.Collection;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "followershistory", path = "followershistory")
public interface FollowersHistoryRepository extends MongoRepository<FollowersHistory, String> {

    
    /**
     *
     * @param email
     * @return
     */
    @Query("{ 'email' : ?0}")
    public Collection<FollowersHistory> findByEmail(String email);
   
    
     /**
     *
     * @param email,socialnetwork
     * @return
     */
    @Query("{ 'email' : ?0, 'socialnetwork': ?1 }")
    public Collection<FollowersHistory> findByEmailandSN(String email, String socialnetwork);

}