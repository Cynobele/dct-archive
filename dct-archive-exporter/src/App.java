import javax.swing.JFrame;
import java.awt.*;

// DCT Archive project | Data exporter

public class App {
    public static void main(String[] args) throws Exception {
        
        boolean _running = true;        // true while app is alive, set to false to exit
        boolean _window_active = false; // true when the window activates

        Dimension screen_size = Toolkit.getDefaultToolkit().getScreenSize(); //get screen res in pixels
        int screen_width = (int)screen_size.getWidth();     //cast to width and height ints
        int screen_height = (int)screen_size.getHeight();

        JFrame main_frame = new JFrame("DCT Archive Exporter");    //Instantiate main window
        main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Make program exit on window close
        main_frame.setBounds((screen_width/2)-250, (screen_height/2)-120, 500, 175);//Set window size and pos

        ExporterFrame exp_frame = new ExporterFrame();
        while(_running){

            if(!_window_active) // only run function if the window isnt already open
            {                   // or the window will refresh and reset options/buttons
                exp_frame.DisplayContent(main_frame);
                _window_active = true;
            }
        }
    }
}
