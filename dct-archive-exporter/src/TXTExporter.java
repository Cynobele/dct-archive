import java.io.BufferedWriter;
import java.io.FileWriter; 
import java.io.IOException;
import java.io.PrintWriter;


public class TXTExporter {
    
    public void exportRecords(String exportlocation, Object[][] table_data)
    {
        try (FileWriter fw = new FileWriter(exportlocation + "\\DCTArchive_Records.txt", true);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter pw = new PrintWriter(bw))
        {
        
            pw.println("DC Thomson Archive Data - Photo Records");
            pw.println(System.lineSeparator());
            pw.println("Photo ID        Publication ID                  Date                      Reference            Location                                             Caption");

            for (int i=0; i<table_data.length; i++)
            {

                    pw.println("" + table_data[i][0] + "               " + table_data[i][1] + "                 \"" + table_data[i][2] + "\"           \"" + table_data[i][3] + "\"           \"" + table_data[i][4] + "\"           \"" + table_data[i][5] + "\"");//appends the string to the file

                pw.println(System.lineSeparator());
            }
        
            bw.close();
            fw.close();
        }
        catch(IOException e)
        {
            System.out.println(e.getMessage());
        }
    
    }
    


    public void exportPublications(String exportlocation, Object[][] table_data)
    {
        try (FileWriter fw = new FileWriter(exportlocation + "\\DCTArchive_Publications.txt", true);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter pw = new PrintWriter(bw))
        {
        
            pw.println("DC Thomson Archive Data - Publications");
            pw.println(System.lineSeparator());
            pw.println("Publication ID              Shortcode           Name");

            for (int i=0; i<table_data.length; i++)
            {

                    pw.println("" + table_data[i][0] + "                             \"" + table_data[i][1] + "\"             \"" + table_data[i][2] + "\"");//appends the string to the file

                    pw.println(System.lineSeparator());
            }
        
            bw.close();
            fw.close();
        }
        catch(IOException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
