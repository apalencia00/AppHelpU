/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.apphelpu.Data;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author usuario
 */
public class Database {
    
     // PROPERTIES
    private  Connection con;
    public static ResultSet rs;
    private String driver;
    private String url;
    private String user = "";
    private String password = "";
    public static PreparedStatement pst = null;
    public static ResultSet         rst = null;
    
    public  boolean hayConexion() {
        return con != null;
    }
    

    public Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public void conectar(String us, String pass) throws SQLException, IOException {
        setDriver("org.postgresql.Driver");
        setUrl("jdbc:postgresql://10.35.10.21:5432/helpdeskasp");
     
        setUser(us);
        setPassword(pass);
        if (!hayConexion()) {
            if (driver == null) {
                throw new SQLException("No se ha definido el driver de conexion");
            }
            if (url == null) {
                throw new SQLException("No se ha especificado la url para la conexion");
            }
            try {
                Class.forName(driver).newInstance();
            } catch (Exception e) {
                throw new IOException("Error! No se ha logrado cargar el driver especificado");
            }
            con = DriverManager.getConnection(url, user, password);
        }
    }
    
    public static void main(String [] args) throws SQLException, IOException{
        
        Database conne = new Database();
        conne.conectar("apalencia", "asd.123*-");
        
        String token = "";
        
        PreparedStatement pst = conne.getCon().prepareStatement("SELECT * FROM helpdesk_opciones.gnr_sesion_redis WHERE gnr_sesion_redis_min <= 10");     
        ResultSet rs = pst.executeQuery();
        
        while (rs.next()) {
            
            token = rs.getString("gnr_sesion_redis_uuid");
            System.out.println(""+token);
        }
        
        rs.close();
        pst.close();
        conne.getCon().close();
                   
        
//        Config config = new Config();
//        // use single Redis server
//        config.useSingleServer().setAddress("redis://10.35.10.233:6379");
//        RedissonClient redisson = Redisson.create(config);
//        
//        RList<String> list = redisson.getList("myList");
//        list.add("1");
//        list.add(token);
////        list.add("3");
//        boolean contains = list.contains("1");
//        System.out.println("List size: " + list.size());
//        System.out.println("Is list contains value '1': " + contains);
//        for (String element : list) {
//            System.out.println("List element: " + element);
//        }
//
////         RMapCache<String, String> map = redisson.getMapCache("anyMap");
////         map.put("token", token , 10, TimeUnit.MINUTES, 10, TimeUnit.SECONDS);
////         for ( int i = 0; i<= map.size()+1; i++ ) {
////            System.out.println("List element: " + map.get(i).toString());
////          }
////
//
//        redisson.shutdown();
//        System.out.println("Servidor Redis Desconectado");
    }
        
    
    
}
