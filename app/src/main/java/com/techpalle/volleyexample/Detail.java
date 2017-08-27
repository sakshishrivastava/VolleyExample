package com.techpalle.volleyexample;

/**
 * Created by sakshi1 on 25-08-2017.
 */

public class Detail {

    public Detail(String name, String email, String home,String mobile){
           this.name = name;
        this.email=email;
        this.home=home;
        this.mobile=mobile;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    private String name,email,home,mobile;
}
