package edu.pps.integradorrs.repositorys;

import edu.pps.integradorrs.model.Llave;
import org.springframework.data.mongodb.repository.MongoRepository;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "llaves", path = "llaves")
public interface LlaveRepository extends MongoRepository<Llave, String>, LlaveRepositoryCustom {

    /**
     *
     * @param email
     * @return
     */
    //@Query("{ 'email' : ?0}")
    //public Usuario findByEmail(String email);

}
