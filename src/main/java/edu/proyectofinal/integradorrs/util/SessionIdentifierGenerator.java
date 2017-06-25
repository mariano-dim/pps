package edu.proyectofinal.integradorrs.util;

import com.fasterxml.uuid.Generators;
import com.fasterxml.uuid.NoArgGenerator;

import java.util.Random;
import java.util.UUID;
/**
 * Created by mdimaggio on 24/06/17.
 */
public class SessionIdentifierGenerator {

    public SessionIdentifierGenerator(){}

    public String nextSessionId() {
        NoArgGenerator randomBasedGenerator = Generators.randomBasedGenerator();
        UUID generatedUUID = randomBasedGenerator.generate();
        return generatedUUID.toString();
    }
}
