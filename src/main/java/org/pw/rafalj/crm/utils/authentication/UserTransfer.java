package org.pw.rafalj.crm.utils.authentication;

import java.util.Map;

/**
 * Created by Rav on 2016-01-12.
 */
public class UserTransfer {

    private final String name;

    private final Map<String, Boolean> roles;

    private final Map<String, String> appInfo;


    public UserTransfer(String userName, Map<String, Boolean> roles, Map<String, String> appInfo)
    {
        this.name = userName;
        this.roles = roles;
        this.appInfo = appInfo;
    }

    public Map<String, String> getAppInfo() {
        return appInfo;
    }

    public String getName()
    {
        return this.name;
    }


    public Map<String, Boolean> getRoles()
    {
        return this.roles;
    }
}
