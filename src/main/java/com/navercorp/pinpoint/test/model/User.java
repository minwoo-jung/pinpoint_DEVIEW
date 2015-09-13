package com.navercorp.pinpoint.test.model;

/**
 * @author hyungil.jeong
 */
public class User {
    
    public static final User MOCK_USER = new User("deview_1", "Deview User");
    
    private String id;
    private String nickName;
    
    public User() {}
    
    public User(String id, String nickName) {
        this.id = id;
        this.nickName = nickName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
