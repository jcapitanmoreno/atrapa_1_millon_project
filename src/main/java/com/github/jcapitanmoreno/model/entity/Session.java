package com.github.jcapitanmoreno.model.entity;

public class Session {
    private static Session _instance;
    private static Player playerLoged;

    public static Session get_Instance() {
        if (_instance == null) {
            _instance = new Session();
        }
        return _instance;
    }

    public void login(Player player){
        playerLoged = player;
    }

    public Player getPlayerLoged(){
        return playerLoged;
    }
    public  void logOut(){
        playerLoged = null;
    }

}
