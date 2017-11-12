package edu.pps.integradorrs.model;

import java.util.Arrays;
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
    private Llave[] llaves;
    @Field
    private Date creationDate;

    

    public Usuario() {
        super();
    }

    public Usuario(String id, String email, String nombre, Date creationDate) {
        super();
        this.id = id;
        this.email = email;
        this.creationDate = creationDate;
        this.nombre = nombre;
    }

    
    public Usuario(Usuario u) {
        super();
        this.id = u.getId();
        this.email = u.getEmail();
        this.nombre = u.getNombre();
        this.creationDate = u.getCreationDate();
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

    public Llave[] getLlaves() {
        return llaves;
    }

    public void setLlaves(Llave[] llaves) {
        this.llaves = llaves;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", nombre='" + nombre + '\'' +
                ", llaves=" + Arrays.toString(llaves) +
                ", creationDate=" + creationDate +
                '}';
    }
}
