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
