import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//SUMMARY
//Connects to the sqlite database to import the data

public class Connect {
    
    public static void connect(){

        String path = "jdbc:sqlite:./../lib/photolib.db"; //explicit relative filepath
        Connection conn = null;

        try { //attempt connection
            conn = DriverManager.getConnection(path);
            System.out.println("Connected to SQLite successfully!");
        } 
        catch (SQLException e) {
            System.out.println("An error occurred when connect to SQLite database...");
            System.out.println(e.getMessage());
        }
    }

    //TODO : add method for extracting data
}
