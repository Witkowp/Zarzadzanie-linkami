/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bazadanych;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.DatabaseMetaData;
import com.mysql.jdbc.Driver;
import com.mysql.jdbc.Statement;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

/**
 *
 * @author Piotr
 */
public class BazaDanych {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            runTest();
        } catch (SQLException ex) {
            for (Throwable t : ex) {
                t.printStackTrace();
            }
        } catch (IOException exception) {
            System.out.println("Problem z IO");
        } catch (URISyntaxException ur) {
            ur.printStackTrace();

        }
    }

    public static void runTest() throws SQLException, IOException, URISyntaxException {
        Scanner sc = new Scanner(System.in);
        String nowyURL;
        String nazwa,sciezka;
        int id;
        System.out.println("Podaj nr id:");
        if (sc.hasNextInt() == true) {
            id = sc.nextInt();
            getFromSQL(id);
        } else {
            System.out.println("Niepoprawne dane");
        }
        String cos = sc.nextLine();

        System.out.println("Wstaw link:");
        nowyURL = sc.nextLine();
        System.out.println("Podaj nazwe:");
        nazwa = sc.nextLine();
        
        System.out.println("Podaj sciezke:");
        sciezka = sc.nextLine();
        insertIntoSQL(nowyURL, nazwa,sciezka);
        System.out.println("Podaj id które chcesz usunąć:");
        int delId=sc.nextInt();
        deleteFromSQL(delId);
    }   

    public static void getFromSQL(int urlid) throws SQLException, IOException, URISyntaxException {
        try (Connection conn = getConnection()) {
            Statement stat = (Statement) conn.createStatement();
            try (ResultSet result = stat.executeQuery("SELECT url,nazwa FROM linki WHERE urlid=" + urlid);) {
                if (result.next()) {
                    System.out.println(result.getString("url"));
                    if (Desktop.isDesktopSupported()) {
                        Desktop.getDesktop().browse(new URI(result.getString("url")));
                    }
                    System.out.println(result.getString("nazwa"));
                }
            }
        }
    }

    public static void insertIntoSQL(String url, String nazwa,String sciezkaDoPliku) throws SQLException {
        try (Connection conn = getConnection()) {
            Statement stat = (Statement) conn.createStatement();
            stat.executeUpdate("INSERT INTO linki VALUES(NULL,'" + url + "' , '" + nazwa + "', '" + sciezkaDoPliku + "')");
        }

    }
    public static void deleteFromSQL(int urlid) throws SQLException{
        try(Connection conn=getConnection()){
            Statement stat= (Statement) conn.createStatement();
            stat.executeUpdate("DELETE FROM linki WHERE urlid="+urlid);
        }
    
    }

    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/listaurl";
        Properties prop = new Properties();
        prop.setProperty("user", "root");
        prop.setProperty("password", "");
        Driver d = new com.mysql.jdbc.Driver();
        Connection con = (Connection) d.connect(url, prop);
        return con;
    }

    public static void test() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/mysql";
        Properties prop = new Properties();
        prop.setProperty("user", "root");
        prop.setProperty("password", "");
        Driver d = new com.mysql.jdbc.Driver();
        Connection con = (Connection) d.connect(url, prop);
        if (con == null) {
            System.out.println("connection failed");
            return;
        }
        DatabaseMetaData dm = (DatabaseMetaData) con.getMetaData();
        String dbversion = dm.getDatabaseProductVersion();
        String dbname = dm.getDatabaseProductName();
        System.out.println("name:" + dbname);
        System.out.println("version:" + dbversion);

    }
}
