package com.github.jcapitanmoreno.model.entity;

public class Session {
    private static Session _instance;

    public static Session get_Instance() {
        if (_instance == null) {
            _instance = new Session();
        }
        return _instance;
    }

}
