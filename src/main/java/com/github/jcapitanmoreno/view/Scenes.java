package com.github.jcapitanmoreno.view;

public enum Scenes {
    ADMLOGIN("view/AdmLoginV.fxml"),

    USERSNAME("view/UsersNameV.fxml");


    private String url;

    Scenes(String url) {
        this.url = url;
    }

    public String getURL() {
        return url;
    }
}
