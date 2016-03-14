package org.pw.rafalj.crm.vo.authentication;

/**
 * Created by rjozwiak on 2016-01-12.
 */
public class UserAuthenticationVO {
    String username;
    String password;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "userAuthenticationVO{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
