package com.giantnodes.forum.model;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class User {

    private final String id;
    private final String username;
    private final String email;
    private String password;
    private String avatar;

    public User( String username, String email, String password) {
        this.id = new ObjectId().toString();
        this.username = username;
        this.email = email;
        this.password = password;
        this.avatar = null;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
