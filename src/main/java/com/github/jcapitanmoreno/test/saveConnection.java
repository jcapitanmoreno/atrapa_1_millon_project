package com.github.jcapitanmoreno.test;

import com.github.jcapitanmoreno.model.connection.ConnectionProperties;
import com.github.jcapitanmoreno.utils.XMLManager;

public class saveConnection {
    public static void main(String[] args) {
        ConnectionProperties c = new ConnectionProperties("localhost","3306","atrapa_1_millon","root","");
        XMLManager.writeXML(c,"connection.xml");
    }
}
