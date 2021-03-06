package edu.pps.integradorrs.repositorys;

import edu.pps.integradorrs.model.Puerta;
import org.springframework.data.mongodb.repository.MongoRepository;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "puertas", path = "puertas")
public interface PuertaRepository extends MongoRepository<Puerta, String>, PuertaRepositoryCustom {

    /**
     *
     * @param publicIdentification
     * @return
     */
    @Query("{ 'publicIdentification' : ?0}")
    public Puerta findByPublicIdentification(String publicIdentification);

}
