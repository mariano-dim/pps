package edu.proyectofinal.integradorrs.model;

import java.util.Date;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.index.Indexed;

@Document(collection = "usuarios")
public class Usuario {

    @Id
    private String id;
    @Field
    @Indexed(unique = true)
    private String email;
    @Field
    private String nombre;
    @Field
    private String clave;
    @Field
    private int pond_likes;
    @Field
    private int pond_comments;
    @Field
    private int pond_shares;
    @Field
    private Date creationDate;
    
    

    public Usuario() {
        super();
    }

    public Usuario(String id, String email, String clave, Date creationDate, String nombre) {
        super();
        this.id = id;
        this.email = email;
        this.clave = clave;
        this.creationDate = creationDate;
        this.nombre = nombre;
        this.pond_likes = 1;
        this.pond_comments = 3;
        this.pond_shares = 5;

    }

    
    public Usuario(Usuario u) {
        super();
        this.id = u.getId();
        this.email = u.getEmail();
        this.clave = u.getClave();
        this.creationDate = u.getCreationDate();
        this.nombre = u.getNombre();
        this.pond_likes = 1;
        this.pond_comments = 3;
        this.pond_shares = 5;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    
    public int getPond_likes() {
        return pond_likes;
    }

    public void setPond_likes(int pond_likes) {
        this.pond_likes = pond_likes;
    }

    public int getPond_comments() {
        return pond_comments;
    }

    public void setPond_comments(int pond_comments) {
        this.pond_comments = pond_comments;
    }

    public int getPond_shares() {
        return pond_shares;
    }

    public void setPond_shares(int pond_shares) {
        this.pond_shares = pond_shares;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

//    @Override
//    @JsonIgnore
//    public boolean isAnErrorResponse() {
//        return false;
//    }
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(" id:- ").append(this.getId());
        str.append(" email:- ").append(this.getEmail());
        str.append(" creationDate:- ").append(this.getCreationDate());
        str.append(" nombre:- ").append(this.getNombre() );
        str.append(" pond_likes:- ").append(String.valueOf(this.getPond_likes()) );
        str.append(" pond_comments:- ").append(String.valueOf(this.getPond_comments()) );
        str.append(" pond_shares:- ").append(String.valueOf(this.getPond_shares()) );
       
        return str.toString();
    }
}
