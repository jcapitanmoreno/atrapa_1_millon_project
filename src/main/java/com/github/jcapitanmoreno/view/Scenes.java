package com.github.jcapitanmoreno.view;

public enum Scenes {
    ADMLOGIN("view/AdmLoginV.fxml"),

    USERSNAME("view/UsersNameV.fxml"),
    USERMENU("view/UserMenuV.fxml"),
    ROOT("view/layout.fxml");


    private String url;

    Scenes(String url) {
        this.url = url;
    }

    public String getURL() {
        return url;
    }
}