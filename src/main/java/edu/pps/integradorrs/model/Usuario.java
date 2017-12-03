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
    private String[] llaves;
    @Field
    private Date creationDate;
    @Field
    private String isBlocked;

    public String getIsBlocked() {
        return isBlocked;
    }

    public void setIsBlocked(String isBlocked) {
        this.isBlocked = isBlocked;
    }

    public Usuario() {
        super();
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

    public String[] getLlaves() {
        return llaves;
    }

    public void setLlaves(String[] llaves) {
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
