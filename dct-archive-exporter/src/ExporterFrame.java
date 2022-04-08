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

public class ExporterFrame{

    //SUMMARY
    //main function for this class
    //instantiates all items on the main_frame
    public void DisplayContent(JFrame main_frame){

        Dimension panel_dimension = new Dimension(400, 50);

        //content_view_panel holds the other 2 sub panels
        JPanel content_view_panel = new JPanel();
        content_view_panel.setLayout(new BoxLayout(content_view_panel, BoxLayout.Y_AXIS));
        content_view_panel.setSize(main_frame.WIDTH, main_frame.HEIGHT); //fill the frame


        //radio_button_panel holds the radio buttons that select the desired file type
        JPanel radio_button_panel = new JPanel();
        //radio_button_panel.setLayout(new BoxLayout(radio_button_panel, BoxLayout.X_AXIS));
        radio_button_panel.setMaximumSize(panel_dimension);
        radio_button_panel.setBorder(BorderFactory.createTitledBorder("Export file type"));


        //location_panel holds the selector allowing an export location to be selected
        //displayed at the bottom of the content_view_panel
        JPanel location_panel = new JPanel();
        location_panel.setLayout(new BoxLayout(location_panel, BoxLayout.X_AXIS));
        location_panel.setMaximumSize(panel_dimension);
        location_panel.setBorder(BorderFactory.createTitledBorder("Export to..."));

        JRadioButton csv_button = new JRadioButton("CSV"); //.csv format
        JRadioButton xls_button = new JRadioButton("XLS"); //.xls format
        JRadioButton txt_button = new JRadioButton("TXT"); //.txt format
        radio_button_panel.add(csv_button); radio_button_panel.add(xls_button); radio_button_panel.add(txt_button);

        JButton browse_button = new JButton("Browse"); //selects an export location
        JTextField export_location = new JTextField();
        location_panel.add(browse_button); location_panel.add(export_location);

        JButton confirm_button = new JButton("Export");

        //add sub panels to the content_view_panel
        content_view_panel.add(radio_button_panel);
        content_view_panel.add(location_panel);
        content_view_panel.add(confirm_button);
        
        //add content_view_panel to main_frame, and toggle visibility. this displays everything above
        main_frame.add(content_view_panel);
        main_frame.setVisible(true);
    }
}