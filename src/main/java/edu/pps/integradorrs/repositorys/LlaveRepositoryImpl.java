package edu.pps.integradorrs.repositorys;

import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import com.mongodb.WriteResult;

import edu.pps.integradorrs.model.Llave;
import edu.pps.integradorrs.model.Usuario;

public class LlaveRepositoryImpl implements LlaveRepositoryCustom {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public int addPuerta(String llavepublicIdentification, String puertapublicIdentification) {

        Query query = new Query(Criteria.where("publicIdentification").is(llavepublicIdentification));

        WriteResult result = mongoTemplate.updateFirst(query,
                new Update().push("puertas", puertapublicIdentification),
                Llave.class);
        if (result != null)
            return result.getN();
        else
            return 0;

    }


    @Override
    public int removePuerta(String llavepublicIdentification, String puertapublicIdentification) {

        Query query = new Query(Criteria.where("publicIdentification").is(llavepublicIdentification));

        WriteResult result = mongoTemplate.updateFirst(query,
                new Update().pull("puertas", puertapublicIdentification),
                Llave.class);
        if (result != null)
            return result.getN();
        else
            return 0;

    }

}
