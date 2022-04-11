import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import java.awt.Dimension;
import java.io.File;
import java.util.Enumeration;
import javax.swing.JFileChooser;

/* SUMMARY
    -Handles the UI (buttons, panels etc) for the ExporterFrame
    -Is called by App to display
*/
public class ExporterFrame{

    //for detecting which of the radio buttons is selected on each panel
    public enum RADIO_BUTTON{NONE, CSV,XLSX,TXT,SQL, PHOTO, PUBLICATION}

    //SUMMARY
    //main function for this class
    //instantiates all items on the main_frame
    public void DisplayContent(JFrame main_frame){

        //region - Instantiate and display content
        Dimension panel_dimension = new Dimension(400, 50);

        //content_view_panel holds the other 2 sub panels
        JPanel content_view_panel = new JPanel();
        content_view_panel.setLayout(new BoxLayout(content_view_panel, BoxLayout.Y_AXIS));
        content_view_panel.setSize(main_frame.WIDTH, main_frame.HEIGHT); //fill the frame


        //file_type_panel holds the radio buttons that select the desired file type
        JPanel file_type_panel = new JPanel();
        file_type_panel.setMaximumSize(panel_dimension);
        file_type_panel.setBorder(BorderFactory.createTitledBorder("Export file type"));


        //table_select_panel holds radio buttons to select which of the db tables to export
        JPanel table_select_panel = new JPanel();
        table_select_panel.setMaximumSize(panel_dimension);
        table_select_panel.setBorder(BorderFactory.createTitledBorder("Select table to export"));


        //location_panel holds the selector allowing an export location to be selected
        //displayed at the bottom of the content_view_panel
        JPanel location_panel = new JPanel();
        location_panel.setLayout(new BoxLayout(location_panel, BoxLayout.X_AXIS));
        location_panel.setMaximumSize(panel_dimension);
        location_panel.setBorder(BorderFactory.createTitledBorder("Export to..."));

        JRadioButton csv_button = new JRadioButton("CSV"); //.csv format
        JRadioButton xlsx_button = new JRadioButton("XLS"); //.xls format
        JRadioButton txt_button = new JRadioButton("TXT"); //.txt format
        JRadioButton sql_button = new JRadioButton("SQL"); //.sql format
        ButtonGroup type_group = new ButtonGroup();     //group buttons together
        type_group.add(csv_button); type_group.add(xlsx_button); type_group.add(txt_button);  type_group.add(sql_button);
        file_type_panel.add(csv_button); file_type_panel.add(xlsx_button); file_type_panel.add(txt_button);  file_type_panel.add(sql_button);

        JRadioButton photo_lib_button = new JRadioButton("Photo lib");
        JRadioButton publi_lib_button = new JRadioButton("Publications");
        ButtonGroup table_group = new ButtonGroup();
        table_group.add(photo_lib_button);table_group.add(publi_lib_button);
        table_select_panel.add(photo_lib_button); table_select_panel.add(publi_lib_button);

        JButton browse_button = new JButton("Browse"); //selects an export location
        JTextField export_location = new JTextField();
        location_panel.add(browse_button); location_panel.add(export_location);

        //confirms selections made and exports the file
        JButton confirm_button = new JButton("Export");

        //add sub panels to the content_view_panel
        content_view_panel.add(file_type_panel);
        content_view_panel.add(table_select_panel);
        content_view_panel.add(location_panel);
        content_view_panel.add(confirm_button);
        
        //add content_view_panel to main_frame, and toggle visibility. this displays everything above
        main_frame.add(content_view_panel);
        main_frame.setVisible(true);
        //endregion

        confirm_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {

                Connect connect = new Connect();
                
                System.out.println("Getting table data...");
                RADIO_BUTTON type_selection = GetSelectedRadio(type_group);
                RADIO_BUTTON table_selection = GetSelectedRadio(table_group);
                Boolean type_ready = false, table_ready = false; //true when the program has all data it needs to export
                Object[][] data = null;

                JOptionPane alert = new JOptionPane(); //popup window for user messages
                
                if(type_selection != RADIO_BUTTON.NONE && type_selection != null){
                    type_ready = true;
                    //JOptionPane.showMessageDialog(alert, type_selection+" selected!", "Success", JOptionPane.INFORMATION_MESSAGE);
                }
                else {
                    JOptionPane.showMessageDialog(alert, "You must select a file type!", "Error", JOptionPane.ERROR_MESSAGE);
                    type_ready = false;
                }

                switch(table_selection){ //import the correct table
                    case NONE:
                        JOptionPane.showMessageDialog(alert, "You must select a table!", "Error", JOptionPane.ERROR_MESSAGE);
                        table_ready = false;
                    break;

                    case PHOTO:
                        data = connect.getPhotoData(); //2D array holding photo table
                        table_ready = true;
                    break;
                    
                    case PUBLICATION:
                        data = connect.getPublicationData(); //2D array holding publication table
                        table_ready = true;
                    break;
                    
                    default:
                        table_ready = false;
                        break;
                }

                if(table_ready && type_ready){ //BOTH table and file_type have been selected
                    switch(type_selection){
                        case CSV:
                            CSVExporter csve = new CSVExporter();
                            csve.WriteFile(data, export_location.getText(), table_selection);
                            break;

                        case XLSX:
                            XLSXExporter xslxe = new XLSXExporter();
                            xslxe.WriteFile(data, export_location.getText(), table_selection);
                            break;

                        case SQL:
                            SQLExporter sqle = new SQLExporter();
                            if (table_selection == ExporterFrame.RADIO_BUTTON.PHOTO)
                            {
                                sqle.duplicateRecords(export_location.getText());
                                sqle.exportRecords(export_location.getText(), data);
                            }
                            else
                            {
                                sqle.duplicatePublications(export_location.getText());
                                sqle.exportPublications(export_location.getText(), data);
                            }
                            break;

                        case TXT:
                            TXTExporter txte = new TXTExporter();
                            if (table_selection == ExporterFrame.RADIO_BUTTON.PHOTO)
                            {
                                txte.exportRecords(export_location.getText(), data);
                            }
                            else 
                            {
                                txte.exportPublications(export_location.getText(), data);
                            }
                            break;

                        default:
                            break;
                        
                    }
                }
            }
        });

        browse_button.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent e){
                JFileChooser j = new JFileChooser();    //set up filechooser

                j.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);  //set to only save directories, not files 
                j.setCurrentDirectory(new File(System.getProperty("user.home")));   //set starting directory to user home

                int result = j.showSaveDialog(main_frame);  //show dialog

                if (result == JFileChooser.APPROVE_OPTION)  //once user hits save
                {
                    File file = j.getSelectedFile();    //get the directory they chose

                    export_location.setText(file.toString());   //add directory to text box
                }
            }    
        });
    }

    //SUMMARY
    //Takes the radio button group that you want to check as parameter
    //Returns an enum value representing which radio button in the group was selected
    public RADIO_BUTTON GetSelectedRadio(ButtonGroup x){
        Enumeration<AbstractButton> group = x.getElements();
        RADIO_BUTTON selected;
        String token;

        while(group.hasMoreElements()){ //cycle through the group to check each button
            
            JRadioButton temp = (JRadioButton)group.nextElement();
            if(temp.isSelected()){
                token = temp.getText(); //Get the text value from the button for switch case
                switch(token){

                    case "CSV":
                        selected = RADIO_BUTTON.CSV;
                    break;

                    case "XLS":
                        selected = RADIO_BUTTON.XLSX;
                    break;
                    
                    case "TXT":
                        selected = RADIO_BUTTON.TXT;
                    break;

                    case "SQL":
                        selected = RADIO_BUTTON.SQL;
                    break;

                    case "Photo lib":
                        selected = RADIO_BUTTON.PHOTO;
                    break;

                    case "Publications":
                        selected = RADIO_BUTTON.PUBLICATION;
                    break;

                    default:
                        selected = RADIO_BUTTON.NONE; //no selection has been made in this group
                }
                return selected;
            }
        }
        return RADIO_BUTTON.NONE;
    }
}