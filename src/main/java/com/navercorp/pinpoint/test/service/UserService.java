package com.navercorp.pinpoint.test.service;

import com.navercorp.pinpoint.test.model.User;

/**
 * @author hyungil.jeong
 */
public interface UserService {

    User get(String id);

    void insert(String id, String nickName);

    void delete(String id);

    void update(String id, String nickName);

}
