package edu.pps.integradorrs.repositorys;

import edu.pps.integradorrs.model.Log;
import org.springframework.data.mongodb.repository.MongoRepository;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "logs", path = "logs")
public interface LogRepository extends MongoRepository<Log, String> {



}
