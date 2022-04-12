import java.io.*;


public class TXTExporter {
    
    public void exportRecords(String exportlocation, Object[][] table_data)
    {
        try (FileWriter fw = new FileWriter(exportlocation + File.separator + "DCTArchive_Records.txt", true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter pw = new PrintWriter(bw))
        {

            pw.println("PhotoID, Publication, Date, Reference, Location, Caption");

            for (int i=0; i<table_data.length; i++)
            {

                    pw.println(table_data[i][0] + ", " + table_data[i][1] + ", " + table_data[i][2] + ", " + table_data[i][3] + ", " + table_data[i][4] + ", " + table_data[i][5]);//appends the string to the file

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
        try (FileWriter fw = new FileWriter(exportlocation + File.separator + "DCTArchive_Publications.txt", true);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter pw = new PrintWriter(bw))
        {
    
            pw.println("PublicationID, Shortcode, Name");

            for (int i=0; i<table_data.length; i++)
            {

                    pw.println(table_data[i][0] + ", " + table_data[i][1] + ", " + table_data[i][2]);//appends the string to the file

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
