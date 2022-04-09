/* SUMMARY
    -Handles the UI (buttons, panels etc) for the ExporterFrame
    -Is called by App to display
*/
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import java.awt.Dimension;
import java.io.File;
import javax.swing.JFileChooser;

public class ExporterFrame{

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
        JRadioButton xls_button = new JRadioButton("XLS"); //.xls format
        JRadioButton txt_button = new JRadioButton("TXT"); //.txt format
        file_type_panel.add(csv_button); file_type_panel.add(xls_button); file_type_panel.add(txt_button);

        JRadioButton photo_lib_button = new JRadioButton("Photo lib");
        JRadioButton publi_lib_button = new JRadioButton("Publications");
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
                System.out.println("getting photo data...");
                Object[][] photo_data = connect.getPhotoData();
                
                for(int i=0; i<photo_data.length; i++){
                    for(int j=0; j<6; j++){
                        System.out.println(photo_data[i][j]);
                    }
                    System.out.println("\n");
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
}