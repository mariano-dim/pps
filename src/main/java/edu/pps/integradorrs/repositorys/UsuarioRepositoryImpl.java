package edu.pps.integradorrs.repositorys;

import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import com.mongodb.WriteResult;

import edu.pps.integradorrs.model.Usuario;

public class UsuarioRepositoryImpl implements UsuarioRepositoryCustom {

	@Autowired
	MongoTemplate mongoTemplate;

	@Override
	public int updateUsuario(String email, String newEmail, String nombre) {

		Query query = new Query(Criteria.where("email").is(email));
		Update update = new Update();

		boolean iFlag = false;

		if(!Strings.isNullOrEmpty(newEmail)){
			update.set("email", newEmail) ;
			iFlag = true;
		}
		if(!Strings.isNullOrEmpty(nombre)){
			update.set("nombre", nombre);
			iFlag = true;
		}

        if (iFlag){
			WriteResult result = mongoTemplate.updateFirst(query, update, Usuario.class);
			if (result != null)
				return result.getN();
			else
				return 0;
		} else{
        	return 0;
		}


	}


}
