package edu.proyectofinal.integradorrs.repositorys;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import com.mongodb.WriteResult;

import edu.proyectofinal.integradorrs.model.Usuario;

public class UsuarioRepositoryImpl implements UsuarioRepositoryCustom {

	@Autowired
	MongoTemplate mongoTemplate;

	@Override
	public int updateUsuario(String email, String clave, String nombre) {

		Query query = new Query(Criteria.where("email").is(email));
		Update update = new Update();
		update.set("clave", clave);

		WriteResult result = mongoTemplate.updateFirst(query, update, Usuario.class);

		if (result != null)
			return result.getN();
		else
			return 0;

	}

}
