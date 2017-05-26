/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.linkmanager;

/**
 *
 * @author Cukier
 */
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.DatabaseMetaData;
import com.mysql.jdbc.Driver;
import com.mysql.jdbc.Statement;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

/**
 *
 * @author Piotr
 */
public class BazaDanych {



    public static List <Data> getFromSQL() throws SQLException, IOException, URISyntaxException {//pobieranie z bazy danych
        List<Data> links = new LinkedList<>();
        try (Connection conn = getConnection()) {
            Statement stat = (Statement) conn.createStatement();
            try (ResultSet result = stat.executeQuery("SELECT * FROM linki");) {
                while (result.next()) {
                    long id=Integer.parseInt(result.getString("urlId"));
                    String url=result.getString("url");
                    String name=result.getString("nazwa");
                    String folder=result.getString("folder");
                    Data data= new Data(id,name,url,folder);
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
    public static void deleteFromSQL(long urlid) throws SQLException{//usuwanie z bazy danych
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


}
