import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLSXExporter {
    
    //SUMMARY
    //table_data is the array imported from database
    //file_path is the location selected by the user
    //table is the selected table to export
    public void WriteFile(Object[][] table_data, String file_path, ExporterFrame.RADIO_BUTTON table){
        String file_name = "/" + table.toString() +"_DCTArchiveExport.xlsx";
        File file = new File(file_path + file_name);
        String[] photo_headings = {"id", "publication id", "date", "reference", "location", "caption"};
        String[] pub_headings = {"publication id", "shortcode", "name"};
        
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet(table.toString());

        if(table == ExporterFrame.RADIO_BUTTON.PHOTO){//write headings
            Row row = sheet.createRow(0);
            for(int i=0; i<photo_headings.length; i++){
                Cell cell = row.createCell(i);
                cell.setCellValue(photo_headings[i]);
            }
        }else{ //heading for publication table
            Row row = sheet.createRow(0);
            for(int i=0; i<pub_headings.length; i++){
                Cell cell = row.createCell(i);
                cell.setCellValue(pub_headings[i]);
            }
        }

        try{ //attempt to export the table_data to xlsx file
            
            for(int i=0; i<table_data.length; i++){
                Row row = sheet.createRow(i+1); //i+1 since i=0 is headings row
                for(int j=0; j<table_data[i].length; j++){
                    Cell cell = row.createCell(j);
                    cell.setCellValue(table_data[i][j].toString());
                }
            }

            FileOutputStream output_stream = new FileOutputStream(file); //write to file
            workbook.write(output_stream);
            workbook.close();

        }catch(IOException e){
            System.out.println("XSLX Write exception:"+e.getMessage());
        }
    }
}
