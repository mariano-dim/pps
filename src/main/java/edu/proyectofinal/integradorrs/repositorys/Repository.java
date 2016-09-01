package edu.proyectofinal.integradorrs.repositorys;

import edu.proyectofinal.integradorrs.model.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Collection;
/**
 * Created by maraino on 25/03/16.
 */
public interface Repository extends MongoRepository<Transaction, String> {

    public Transaction findByMerchant(String merchant);
    public Collection<Transaction> findByAmount(String amount);
}
