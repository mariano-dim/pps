package edu.proyectofinal.integradorrs.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;


@Configuration
public class SpringMongoConfig extends AbstractMongoConfiguration {

    @Override
    @Bean
    public Mongo mongo() {
    	try {
    	Mongo myClient = new MongoClient("54.201.96.80" + ":" + 27017);
    		//ServerAddress mongodb = new ServerAddress("54.201.96.80",27017);
    		//Mongo myClient = new MongoClient(mongodb);
    	
    	
    	
    	boolean auth = myClient.getDB("admin").authenticate("apibackend", "un1caece".toCharArray());
    	if (auth) {

    		/* boludeces de ejemplo
    		DBCollection table = db.getCollection("user");
    		BasicDBObject document = new BasicDBObject();
    		document.put("name", "mkyong");
    		table.insert(document);
			*/
    		System.out.println("DB Login is successful!");
    	} else {
    		System.out.println("DB Login is failed!");
    	}
    	
    	return myClient;
    	} catch (Exception e) { System.out.println("Exception ocurred while connecting to DB:"+e.getMessage());}
		return null;    	
    }
    @Override
    protected String getDatabaseName(){
        return "test";
    }
}

