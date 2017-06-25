package edu.proyectofinal.integradorrs.repositorys;

import edu.proyectofinal.integradorrs.model.TokenEmail;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by mdimaggio on 24/06/17.
 */
@RepositoryRestResource(collectionResourceRel = "tokenEmails", path = "tokenEmails")
public interface TokenEmailRepository extends MongoRepository<TokenEmail, String> {

    /**
     *
     * @param email
     * @return
     */
    @Query("{ 'tokenEmail' : ?0, 'tokenEnabled': ?1 }")
    public TokenEmail findByEmailandEnabled(String email, boolean enabled);

    /**
     *
     * @param email, token
     * @return
     */
    @Query("{ 'tokenEmail' : ?0, 'token': ?1 }")
    public TokenEmail findByEmailandToken(String email, String token);

}


