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
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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

    public static void runTest() throws SQLException, IOException, URISyntaxException {//metoda testowa 
        Scanner sc = new Scanner(System.in);
        String nowyURL;
        String nazwa,sciezka;
        int id;
        System.out.println("Wypisuje listę:");
        List<Data> links;
        links=getFromSQL();
        links.stream().forEach((data) -> {
            System.out.println("id: "+ data.id+" url: "+ data.adress+" nazwa: "+ data.name+ "folder: "+data.direct);
        });
        System.out.println("Wstaw link:");
        nowyURL = sc.nextLine();
        System.out.println("Podaj nazwe:");
        nazwa = sc.nextLine();
        
        System.out.println("Podaj sciezke:");
        sciezka = sc.nextLine();
        long i=insertIntoSQL(nowyURL, nazwa,sciezka);
        System.out.println("Numer to : " + i);
        System.out.println("Podaj id które chcesz usunąć:");
        int delId=sc.nextInt();
        deleteFromSQL(delId);
    }   

    public static List <Data> getFromSQL() throws SQLException, IOException, URISyntaxException {//pobieranie z bazy danych
        List<Data> links = new ArrayList<>();
        try (Connection conn = getConnection()) {
            Statement stat = (Statement) conn.createStatement();
            try (ResultSet result = stat.executeQuery("SELECT * FROM linki");) {
                while (result.next()) {
                    long id=Integer.parseInt(result.getString("urlId"));
                    String url=result.getString("url");
                    String name=result.getString("nazwa");
                    String folder=result.getString("folder");
                    Data data= new Data(id,url,name,folder);
                    links.add(data);
                }
            }
        }
        return links;
    }

    public static long insertIntoSQL(String url, String nazwa,String sciezkaDoPliku) throws SQLException {//wstawianie do bazy danych
        long numer=0;
        try (Connection conn = getConnection()) {
            Statement stat = (Statement) conn.createStatement();
            stat.executeUpdate("INSERT INTO linki VALUES(NULL,'" + url + "' , '" + nazwa + "', '" + sciezkaDoPliku + "')", Statement.RETURN_GENERATED_KEYS);
            ResultSet generatedKeys = stat.getGeneratedKeys();
            if (generatedKeys.next()) {
            return generatedKeys.getLong(1);
            }else{
                throw new SQLException("Brak id");
            }
            
        }
    }
    public static void deleteFromSQL(int urlid) throws SQLException{//usuwanie z bazy danych
        try(Connection conn=getConnection()){
            Statement stat= (Statement) conn.createStatement();
            stat.executeUpdate("DELETE FROM linki WHERE urlid="+urlid);
        }
    
    }

    public static Connection getConnection() throws SQLException {//ustawienie polaczenia z baza danych
        String url = "jdbc:mysql://45.77.55.209:3306/zarzadzanie";
        Properties prop = new Properties();
        prop.setProperty("user", "piopio");
        prop.setProperty("password", "pw");
        Driver d = new com.mysql.jdbc.Driver();
        Connection con = (Connection) d.connect(url, prop);
        return con;
    }

    public static void test() throws SQLException {//test polaczenia z baza danych która jest lokalna
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
