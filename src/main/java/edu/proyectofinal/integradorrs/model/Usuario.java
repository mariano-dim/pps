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

    }

    
    public Usuario(Usuario u) {
        super();
        this.id = u.getId();
        this.email = u.getEmail();
        this.clave = u.getClave();
        this.creationDate = u.getCreationDate();
        this.nombre = u.getNombre();
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
       
        return str.toString();
    }
}
