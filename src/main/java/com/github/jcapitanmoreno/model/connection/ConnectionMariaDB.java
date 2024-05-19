package com.github.jcapitanmoreno.model.connection;

import com.github.jcapitanmoreno.utils.XMLManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionMariaDB {
    private final static String FILE="connection.xml";
    private static ConnectionMariaDB _instance;
    private static Connection conn;
    /**
     * Establece la conexión con la base de datos MariaDB.
     * Lee las propiedades de conexión desde un archivo XML y utiliza estas propiedades para establecer la conexión.
     * Si la conexión no se puede establecer, se establece a null y se imprime la traza de la excepción.
     */
    private ConnectionMariaDB(){
        ConnectionProperties properties = (ConnectionProperties) XMLManager.readXML(new ConnectionProperties(),FILE);

        try {
            conn = DriverManager.getConnection(properties.getURL(),properties.getUser(),properties.getPassword());
        } catch (SQLException e) {
            e.printStackTrace();
            conn=null;
        }
    }
    /**
     * Devuelve una instancia de conexión a la base de datos MariaDB.
     * Si no existe una instancia de conexión, crea una nueva llamando al método ConnectionMariaDB().
     *
     * @return La instancia de conexión a la base de datos MariaDB.
     */
    public static Connection getConnection(){
        if(_instance==null){
            _instance = new ConnectionMariaDB();
        }
        return conn;
    }
    /**
     * Cierra la conexión a la base de datos si está abierta.
     * Si la conexión no es nula, la cierra. Si ocurre una excepción durante el cierre, se imprime la traza de la excepción.
     */
    public static void closeConnection(){
        if(conn!=null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


}
