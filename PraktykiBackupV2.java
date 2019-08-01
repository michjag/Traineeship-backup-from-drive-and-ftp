package praktykibackupv2;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class PraktykiBackupV2 
{
    public static void main(String[] args)
    {
        try 
        {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } 
        catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException ex)
        {
            System.out.println(ex.getMessage());
        } 
        
        new Window().setVisible(true);
    }
    
}
