package com.giantnodes.forum.api.user;

import com.giantnodes.forum.utility.Mergeable;
import org.joda.time.DateTime;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.util.DigestUtils;

import java.lang.reflect.Field;

@Document(collection = "users")
public class User implements Mergeable<User, UserInput> {

    @Id
    private String id;

    private String username;
    private String email;
    private String password;
    private String avatar;

    @CreatedDate
    private DateTime createdAt;

    @LastModifiedDate
    private DateTime modifiedAt;

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = BCrypt.hashpw(password, BCrypt.gensalt(9));
        this.avatar = "https://gravatar.com/avatar/" + DigestUtils.md5DigestAsHex(email.getBytes()) + "?d=identicon";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public DateTime getCreatedAt() {
        return createdAt;
    }

    public DateTime getModifiedAt() {
        return modifiedAt;
    }

    @Override
    public User merge(UserInput target) {
        try {
            for (Field field : target.getClass().getDeclaredFields()) {
                Field f = getClass().getDeclaredField(field.getName());
                field.setAccessible(true);


                if (field.get(target) != null) {
                    if (f.get(this) != field.get(target)) {
                        f.set(this, field.get(target));
                    }
                }

                field.setAccessible(false);
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return this;
    }

}
