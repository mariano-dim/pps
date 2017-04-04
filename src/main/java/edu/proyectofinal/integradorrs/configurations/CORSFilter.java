/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.proyectofinal.integradorrs.configurations;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;

/**
 * 
 *  Filtro para Cross Origin: autoriza las conexiones desde otro dominio, en este laboratorio sería el web ser: http://localhost:5000
 * 
 * */
@Component
public class CORSFilter implements Filter {

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest request = (HttpServletRequest) req;
        /*
        //dominios de origen permitidos, agregar o quitar segun corresponda
        String[] domOK = {"localhost:5000","localhost:8080", "localhost", "socialfocus.com.ar"};
        
        //obtiene encabezado URL del origen
        String origHeader = request.getHeader("host");
        System.out.println(origHeader);
        //Valida que exista entre los dominios permitidos 
        for (String domain : domOK ){
            if (origHeader.endsWith(domain)){
                response.setHeader("Access-Control-Allow-Origin", origHeader);
                break;
            }else{
                System.out.println("No esta");
            }
        }
        */
        response.setHeader("Access-Control-Allow-Origin", "*" );
        //response.setHeader("Access-Control-Allow-Origin", "http://localhost:5000" );
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Origin, Authorization, X-Requested-With, Content-Type, Accept");
        chain.doFilter(req, res);
    }

    public void init(FilterConfig filterConfig) {}

    public void destroy() {}

}