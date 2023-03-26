package test_window;



public class Controller implements java.awt.event.ActionListener
{
    /** Singleton implementation : **/
    private static volatile Controller cInstance;

    /** Controller class constructor: **/
    protected Controller() 
    {
        /** TODO **/
    }
    /** **/
    public static Controller getInstance()
    {
        if (null == cInstance) {
            synchronized (Controller.class) {
                if (null == cInstance) {
                    cInstance = new Controller();
                }
            }
        }
        return cInstance;
    }
    /** **/
    public void actionPerformed(java.awt.event.ActionEvent evt) 
    {
        if (evt.getActionCommand().equals("OpenSettingsDialog"))
        {
            OpenSettingsDialog();
        }
        else
        {
            System.out.println(evt.getActionCommand());
        }
    }
    /** **/
    private void OpenSettingsDialog() 
    {                                           
        System.out.println("OpenSettingsDialog");
    }    
}
