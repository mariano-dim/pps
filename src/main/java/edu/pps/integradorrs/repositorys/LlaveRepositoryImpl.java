package edu.pps.integradorrs.repositorys;

import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import com.mongodb.WriteResult;

import edu.pps.integradorrs.model.Llave;

public class LlaveRepositoryImpl implements LlaveRepositoryCustom {

    @Autowired
    MongoTemplate mongoTemplate;


}
