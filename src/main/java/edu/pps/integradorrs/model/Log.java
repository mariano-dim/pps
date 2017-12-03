package edu.pps.integradorrs.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.Date;

@Document(collection = "log")
public class Log {

    @Id
    private String id;
    @Field
    private String message;
    @Field
    private Date creationDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Log(String id, String message, Date creationDate) {
        this.id = id;
        this.message = message;
        this.creationDate = creationDate;
    }

    public Log(String message, Date creationDate) {
        this.message = message;
        this.creationDate = creationDate;
    }

    public Log() {
    }

    @Override
    public String toString() {
        return "Log{" +
                "id='" + id + '\'' +
                ", message='" + message + '\'' +
                ", creationDate=" + creationDate +
                '}';
    }
}
