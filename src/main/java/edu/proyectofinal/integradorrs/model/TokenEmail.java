package edu.proyectofinal.integradorrs.model;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;


/**
 * Created by mdimaggio on 24/06/17.
 */
@Document(collection = "tokenEmails")
public class TokenEmail {

    @Id
    private String id;

    @Field
    private String tokenEmail;

    @Field
    private String token;

    @Field
    private boolean tokenEnabled;

    @Field
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date tokenExpiration;


    public TokenEmail() {
        super();
    }

    public TokenEmail(String id, String tokenEmail, String token, Date tokenExpiration, boolean tokenEnabled) {
        super();
        this.id = id;
        this.tokenEmail = tokenEmail;
        this.token = token;
        this.tokenExpiration = tokenExpiration;
        this.tokenEnabled = tokenEnabled;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(" id:- ").append(this.getId());
        str.append(" tokenEmail:- ").append(this.tokenEmail);
        str.append(" token:- ").append(this.token);
        str.append(" tokenExpiration:- ").append(this.tokenExpiration);
        str.append(" tokenEnabled:- ").append(this.tokenEnabled);

        return str.toString();
    }

    public boolean isTokenEnabled() {
        return tokenEnabled;
    }

    public void setTokenEnabled(boolean tokenEnabled) {
        this.tokenEnabled = tokenEnabled;
    }

    public String getId() {
        return id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTokenEmail() {
        return tokenEmail;
    }

    public void setTokenEmail(String tokenEmail) {
        this.tokenEmail = tokenEmail;
    }

    public Date getTokenExpiration() {
        return tokenExpiration;
    }

    public void setTokenExpiration(Date tokenExpiration) {
        this.tokenExpiration = tokenExpiration;
    }

}



