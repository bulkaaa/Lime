package com.modern.codes.lime;

import com.modern.codes.lime.model.User;
import com.modern.codes.lime.pojo.UserPOJO;

public class ModelParser {
    public UserPOJO userDBtoPOJO(User user) {
        UserPOJO usr = new UserPOJO();
        usr.setId(user.getId());
        usr.setName(user.getName());
        usr.setSurname(user.getSurname());
        usr.setJobs(user.getJobs());
        usr.setJoinedAt(user.getJoinedAt());
        return usr;
    }
    public User userPOJOToDB(UserPOJO user) {
        User usr = new User();
        usr.setId(user.getId());
        usr.setName(user.getName());
        usr.setSurname(user.getSurname());
        usr.setJobs(user.getJobs());
        usr.setJoinedAt(user.getJoinedAt());
        return usr;
    }
}
