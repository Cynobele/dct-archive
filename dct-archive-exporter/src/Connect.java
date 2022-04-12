import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//SUMMARY
//Connects to the sqlite database to import the data

public class Connect {

    public Object[][] getPhotoData(){
        
        try { //attempt connection
        
            Connection conn = connect(); //establish db connection
            String photo_sql = "SELECT photo.*, pub.shortcode FROM photo JOIN publication AS pub ON (photo.pub_id = pub.id)";

            Statement row_statement = conn.createStatement(); //get the number of rows
            ResultSet row_result = row_statement.executeQuery("SELECT COUNT(*) AS row_count FROM photo");
            row_result.next();
            int row_count = row_result.getInt("row_count");
            row_result.close();

            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(photo_sql); //query the photo table
            ResultSetMetaData rsmd = rs.getMetaData();
            int column_count = rsmd.getColumnCount();

            Object[][] data_arr = new Object[row_count][column_count - 1];
            List<Photo> photo_list = new ArrayList<Photo>();

            while(rs.next()){
                Photo photo = new Photo(); //create a new obj for each row
                
                photo.setPhotoId(rs.getInt(1));     //populate object
                photo.setPubId(rs.getInt(2));
                Date date_parse = new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString(3));
                photo.setDate(date_parse);
                photo.setReference(rs.getString(4));
                photo.setLocation(rs.getString(5));
                photo.setCaption(rs.getString(6));
                photo.setShortcode(rs.getString(7));

                photo_list.add(photo); // adds ^this object to the list   
            }

            for(int i = 0; i<photo_list.size(); i++){
                Photo reader = photo_list.get(i); //access each obj in the list

                data_arr[i][0] = reader.getPhotoId(); //use getters to populate 2D array
                data_arr[i][1] = reader.getShortcode();
                data_arr[i][2] = reader.getDate();
                data_arr[i][3] = reader.getReference();
                data_arr[i][4] = reader.getLocation();
                data_arr[i][5] = reader.getCaption();
            }

            return data_arr;
        } catch (Exception e){  //will show the error that caused the Try{} to fail
            System.out.println(e.getMessage());
        }
        return null; //should be unreachable
    }

    public Object[][] getPublicationData(){ //publication table variant of the getPhotoData method

        try{
            Connection conn = connect(); //establish db connection
            String publi_sql = "SELECT * FROM publication";

            Statement row_statement = conn.createStatement(); //get the number of rows
            ResultSet row_result = row_statement.executeQuery("SELECT COUNT(*) AS row_count FROM publication");
            row_result.next();
            int row_count = row_result.getInt("row_count");
            row_result.close();

            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(publi_sql); //query the publication table
            ResultSetMetaData rsmd = rs.getMetaData();
            int column_count = rsmd.getColumnCount();

            Object[][] data_arr = new Object[row_count][column_count];
            List<Publication> publi_list = new ArrayList<Publication>();

            while(rs.next()){
                Publication pub = new Publication();

                pub.setPubli_id(rs.getInt(1));    //populate object
                pub.setShortcode(rs.getString(2));
                pub.setName(rs.getString(3));

                publi_list.add(pub); //add this object to list 
            }

            for(int i=0; i<publi_list.size(); i++){
                Publication reader = publi_list.get(i); //access each obj in the list

                data_arr[i][0] = reader.getPubli_id();
                data_arr[i][1] = reader.getShortcode();
                data_arr[i][2] = reader.getName();
            }
            return data_arr;
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    private static Connection connect(){

        String path = "jdbc:sqlite:lib/photolib.db"; //explicit relative filepath
        Connection conn = null;
        
        try{
            conn = DriverManager.getConnection(path);
            System.out.println("Connected to SQLite successfully!");
        }catch (SQLException e) {
            System.out.println("An error occurred when connecting to SQLite database...");
            System.out.println(e.getMessage());
        }
        return conn;
    }

}
