package view;

public class View implements IView{

    private static View instance = null;


    public void openStartWindow() {

        MainGUI.getInstance().start();
    }

    public static IView getInstance() {
        if (instance == null)
            instance = new View();
        return instance;
    }

}