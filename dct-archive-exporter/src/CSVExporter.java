import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CSVExporter {
    
    //SUMMARY
    //table_data is the array imported from database
    //file_path is the location selected by the user
    //table is the selected table to export
    public void WriteFile(Object[][] table_data, String file_path, ExporterFrame.RADIO_BUTTON table){
        String file_name = "/DCTArchiveExport.csv";
        File file = new File(file_path + file_name);
        
        if(!file.exists()){
            try{
                file.createNewFile();
                try{
                    FileWriter writer = new FileWriter(file);

                    //write headings based on selected table
                    if(table == ExporterFrame.RADIO_BUTTON.PHOTO){
                        writer.write("id , publication id, date, reference, location, caption");
                    }
                    else if(table == ExporterFrame.RADIO_BUTTON.PUBLICATION){
                        writer.write("publication id, shortcode, name");
                    }

                    //loop through 2D array and write values to file
                    for(int i=0; i<table_data.length; i++){
                        writer.write(System.lineSeparator()); //new line
                        
                        for(int j=0; j<table_data[i].length; j++){
                            writer.write(table_data[i][j].toString());
                            writer.write(",");
                        }
                    }
                    writer.flush();
                    writer.close();
                }
                catch(IOException e){
                    System.out.println(e.getMessage());
                }
            } 
            catch(IOException e){
                System.out.println(e.getMessage());
            }
        }
    }
}
