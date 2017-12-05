package edu.pps.integradorrs.services.log;

public interface LogService {


    void send(String codigoMensaje,String message);

    void save(String mensaje);
}

