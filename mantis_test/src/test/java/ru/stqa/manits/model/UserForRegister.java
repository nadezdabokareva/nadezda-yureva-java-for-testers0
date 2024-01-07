package ru.stqa.manits.model;

public class UserForRegister {
    private String token;
    private String username;
    private String realname;
    private String email;
    private String access_level;
    private String enabled;


    public UserForRegister(String token, String username, String realname, String email, String access_level, String enabled) {
        this.token = token;
        this.username = username;
        this.realname = realname;
        this.email = email;
        this.access_level = access_level;
        this.enabled = enabled;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAccess_level() {
        return access_level;
    }

    public void setAccess_level(String access_level) {
        this.access_level = access_level;
    }

    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }
}
