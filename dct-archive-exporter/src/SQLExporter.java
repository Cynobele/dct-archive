import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter; 
import java.io.IOException; 
import java.nio.file.Files;

public class SQLExporter {
    
    public void copyFile(File from, File to) throws IOException
    {
            Files.copy( from.toPath(), to.toPath() );
    }

    public void duplicateRecords(String exportlocation) throws IOException
    {
        File dirFrom = new File("lib\\Records.sql");
        File dirTo = new File(exportlocation + "\\Records.sql");

        
        copyFile(dirFrom, dirTo);
        
    }

    public void duplicatePublications(String exportlocation) throws IOException
    {
        File dirFrom = new File("lib\\Publications.sql");
        File dirTo = new File(exportlocation + "\\Publications.sql");

        
        copyFile(dirFrom, dirTo);
        
    }


    public void exportRecords(String exportlocation, Object publication_data) throws IOException 
    {
        //Alternative method if own doesn't work
        
        //FileWriter fw = null; 
        //BufferedWriter bw = null; 
        //PrintWriter pw = null;

        //fw = new FileWriter("lib\\Records.sql", true); 
        //bw = new BufferedWriter(fw); 
        //pw = new PrintWriter(bw);


        //while (data.read())
        //{
            //pw.println("(" + data[0] + ", " + data[1] + ", " + data[3]);

        //}

        
        FileWriter fw = new FileWriter(exportlocation + "\\Records.sql", true); //the true will append the new data
        BufferedWriter bw = new BufferedWriter(fw);

        //TO DO
        //while (publication_data.read)
        //{
        //    bw.write("(" + publication_data[0] + ", " + publication_data[1] + ", '" + publication_data[2] + "', '" + publication_data[3] + "', '" + publication_data[4] + "', '" + publication_data[5] + "'),");//appends the string to the file
        //}

        bw.write("");
        bw.write("--Indexes for table 'Records'");
        bw.write("ALTER TABLE 'Records'");
        bw.write("ADD PRIMARY KEY ('PhotoID');");
        bw.write("");
        bw.write("--Constraints for table 'Records'");
        bw.write("ALTER TABLE 'Records'");
        bw.write("ADD CONSTRAINT 'Records_ibfk_1' FOREIGN KEY ('PubID') REFERENCES 'Publications' ('PubID');");

        bw.close();
        fw.close();
       
    }

    public void exportPublications(String exportlocation, Object publication_data) throws IOException 
    {
                
        FileWriter fw = new FileWriter(exportlocation + "\\Publications.sql", true); //the true will append the new data
        BufferedWriter bw = new BufferedWriter(fw);


        //TO DO
        //while (publication_data.read)
        //{
        //    bw.write("(" + publication_data[0] + ", " + publication_data[1] + ", '" + publication_data[2] + "', '" + publication_data[3] + "', '" + publication_data[4] + "', '" + publication_data[5] + "'),");//appends the string to the file
        //}

        bw.write("");
        bw.write("--Indexes for table 'Records'");
        bw.write("ALTER TABLE 'Publications'");
        bw.write("ADD PRIMARY KEY ('PubID');");

        bw.close();
        fw.close();
       
    }
}
