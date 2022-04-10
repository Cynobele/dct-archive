import java.io.File;
import java.io.IOException;
import java.util.Locale;

import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.*;

public class XLSXExporter {
    
    public void WriteFile(Object[][] table_data, String file_path, ExporterFrame.RADIO_BUTTON table){
        String file_name = "/" + table.toString() +"_DCTArchiveExport.xlsx";
        File file = new File(file_path + file_name);
        String[] photo_headings = {"id", "publication id", "date", "reference", "location", "caption"};
        String[] pub_headings = {"publication id", "shortcode", "name"};
        
        try{    //attempt to create the doc
            WorkbookSettings wbs = new WorkbookSettings();
            wbs.setLocale(new Locale("en", "EN"));
            WritableWorkbook workbook = Workbook.createWorkbook(file, wbs);
            workbook.createSheet(table.toString(), 0); //create a blank sheet
            WritableSheet sheet1 = workbook.getSheet(0); //make the sheet writable
            Label label;
            
            //write the correct headings
            if(table == ExporterFrame.RADIO_BUTTON.PHOTO){
                for(int i=0; i<6; i++){
                    label = new Label(i, 0, photo_headings[i]);
                    sheet1.addCell(label);
                }
                workbook.createSheet(table.toString()+"1", 1);
                WritableSheet sheet2 = workbook.getSheet(1);//create a 2nd worksheet as 1 is too small


                //TODO : fix write exception
                //When exporting the Photo table as XLSX, a write exception is thrown
                //The maximum number of rows permitted on a worksheet been exceeded
                //I am only writing 50k rows to one sheet, and the rest to the other
                // but somehow still receiving the error...

                for(int i=0; i<50000; i++){ //maximum number of rows on a sheet is 65536
                    for(int j=0; j<table_data[i].length; j++){
                        label = new Label(j, i+1, table_data[i][j].toString());
                        sheet1.addCell(label);
                    }
                }
                for(int i=50000; i<table_data.length; i++){
                    for(int j=0; j<table_data[i].length; j++){
                        label = new Label(j, i+1, table_data[i][j].toString());
                        sheet2.addCell(label);
                    }
                }
            }else if (table == ExporterFrame.RADIO_BUTTON.PUBLICATION){
                for(int i=0; i<3; i++){
                    label = new Label(i, 0, pub_headings[i]);
                    sheet1.addCell(label);
                }

                for(int i=0; i<table_data.length; i++){
                    for(int j=0; j<table_data[i].length; j++){                 //i=1, since i=0 would be the heading row
                        label = new Label(j, i+1, table_data[i][j].toString()); //i-1 to make up for this
                        sheet1.addCell(label);
                    }
                }
            }
            
            workbook.write();
            System.out.println("workbook exported successfully");
            workbook.close();

        }catch(IOException e){
            System.out.println("XLSX IOException: " +e.getMessage());
        }catch(WriteException we){
            System.out.println("XLSX WriteException: " +we.getMessage());
        }
    }
}
