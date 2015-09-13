package com.navercorp.pinpoint.test.dao;

import com.navercorp.pinpoint.test.model.User;

/**
 * @author hyungil.jeong
 */
public interface UserMapper {

    public User selectUser(String id);

    public void insertUser(User user);

    public void deleteUser(String id);

    public void updateUser(User user);

}
