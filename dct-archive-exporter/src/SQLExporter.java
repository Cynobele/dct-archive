import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter; 
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;


public class SQLExporter {
    
    public void copyFile(File from, File to)
    {
        try {
            Files.copy( from.toPath(), to.toPath() );
            
        }
        catch(IOException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void duplicateRecords(String exportlocation)
    {
        
        File dirFrom = new File("dct-archive-exporter\\lib\\Records.sql");
        File dirTo = new File(exportlocation + "\\Records.sql");
            
            copyFile(dirFrom, dirTo);
        
    }

    public void duplicatePublications(String exportlocation)
    {
        File dirFrom = new File("dct-archive-exporter\\lib\\Publications.sql");
        File dirTo = new File(exportlocation + "\\Publications.sql");

        copyFile(dirFrom, dirTo);
    }



    public void exportRecords(String exportlocation, Object[][] table_data)
    {
       
        try (FileWriter fw = new FileWriter(exportlocation + File.separator + "Records.sql", true);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter pw = new PrintWriter(bw))
        {
        
        for (int i=0; i<table_data.length; i++)
        {
            if (i == 0)
            {
                pw.println(System.lineSeparator());
            }

            String caption = table_data[i][5].toString();
            caption = caption.replace("\"","\\\"");
            caption = caption.replace("\'","\\\'");

            if (i != (table_data.length - 1))
            {
                pw.println("(" + table_data[i][0] + ", \"" + table_data[i][1] + "\", \"" + table_data[i][2] + "\", \"" + table_data[i][3] + "\", \"" + table_data[i][4] + "\", \"" + caption + "\"),");//appends the string to the file
            }
            else
            {
                pw.println("(" + table_data[i][0] + ", \"" + table_data[i][1] + "\", \"" + table_data[i][2] + "\", \"" + table_data[i][3] + "\", \"" + table_data[i][4] + "\", \"" + caption + "\");");//appends the string to the file
            }
        }

        pw.println(System.lineSeparator());
        pw.println("ALTER TABLE `Records`");
        pw.println("ADD PRIMARY KEY (`PhotoID`);");
        
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
                
        try (FileWriter fw = new FileWriter(exportlocation + File.separator + "Publications.sql", true);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter pw = new PrintWriter(bw))
        {
        

        for (int i=0; i<table_data.length; i++)
        {
            if (i == 0)
            {
                pw.println(System.lineSeparator());
            }

            String caption = table_data[i][2].toString();
            caption = caption.replace("\"","\\\"");
            caption = caption.replace("\'","\\\'");

            if (i != (table_data.length - 1))
            {
                pw.println("(" + table_data[i][0] + ", \"" + table_data[i][1] + "\", \"" + table_data[i][2] + "\"),");//appends the string to the file
            }
            else
            {
                pw.println("(" + table_data[i][0] + ", \"" + table_data[i][1] + "\", \"" + table_data[i][2] + "\");");//appends the string to the file
            }
        }

        pw.println(System.lineSeparator());
        pw.println("ALTER TABLE `Publications`");
        pw.println("ADD PRIMARY KEY (`PubID`);");

        bw.close();
        fw.close();
        }
        catch(IOException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
