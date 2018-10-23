package com.giantnodes.forum.api.user;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMutation implements GraphQLMutationResolver {

    @Autowired
    private UserDao dao;

    public User create(String username, String email, String password) {
        return dao.create(username, email, password);
    }

    public User delete(String id) {
        return dao.delete(id);
    }

    public User update(UserInput input) {
        return dao.update(input);
    }

}
