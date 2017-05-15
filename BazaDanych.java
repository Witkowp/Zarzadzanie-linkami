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
        String nazwa;
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

        insertIntoSQL(nowyURL, nazwa);
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
