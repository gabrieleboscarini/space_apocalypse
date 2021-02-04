package view;

import controller.Main;

import java.applet.AudioClip;

public class View implements IView{

    private static View instance = null;

    protected MainGUI startWindow = null;

    public void openStartWindow() {

        MainGUI.getInstance().start();
    }

    public void closeStartWindow() {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                if (startWindow != null)
                    startWindow.setVisible(false);
            }
        });
    }

    public static IView getInstance() {
        if (instance == null)
            instance = new View();
        return instance;
    }

}