package view;

import controller.ControllerForView;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Pause extends JPanel implements MouseInputListener {

    private final JPanel contentPane;
    private final Rectangle2D.Double continuegame, menu, exit;

    boolean gamecontinue = false;

    BufferedImage imgbackground = null;
    BufferedImage imgpause = null;
    BufferedImage imgcontinue = null;
    BufferedImage imgmenu = null;
    BufferedImage imgexit = null;
    BufferedImage imgfbcontinue = null;
    BufferedImage imgfbmenu = null;
    BufferedImage imgfbexit = null;
    boolean fbcontinue = false;
    boolean fbmenu = false;
    boolean fbexit = false;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    double width = screenSize.getWidth();
    double height = screenSize.getHeight();

    public Pause(JPanel panel) {

        contentPane = panel;

        this.continuegame = new Rectangle2D.Double(width*0.390, height*0.274, width*0.163, height*0.112);

        this.menu = new Rectangle2D.Double(width*0.416, height*0.474, width*0.163, height*0.112);

        this.exit = new Rectangle2D.Double(width*0.427, height*0.674, width*0.163, height*0.112);

        try{
            this.imgbackground = ImageIO.read(new File("utils/img/pausewindow.png"));
        }
        catch(IOException ioe){
            ioe.printStackTrace();
        }

        try{
            this.imgpause = ImageIO.read(new File("utils/img/pause.png"));
        }
        catch(IOException ioe){
            ioe.printStackTrace();
        }

        try{
            this.imgcontinue = ImageIO.read(new File("utils/img/continue.png"));
        }
        catch(IOException ioe){
            ioe.printStackTrace();
        }

        try{
            this.imgmenu = ImageIO.read(new File("utils/img/menu.png"));
        }
        catch(IOException ioe){
            ioe.printStackTrace();

        }

        try{
            this.imgexit = ImageIO.read(new File("utils/img/exit.png"));
        }
        catch(IOException ioe){
            ioe.printStackTrace();
        }

        try{
            this.imgfbcontinue = ImageIO.read(new File("utils/img/fb_continue.png"));
        }
        catch(IOException ioe){
            ioe.printStackTrace();
        }

        try{
            this.imgfbmenu = ImageIO.read(new File("utils/img/fb_menu.png"));
        }
        catch(IOException ioe){
            ioe.printStackTrace();
        }

        try{
            this.imgfbexit = ImageIO.read(new File("utils/img/fb_exit.png"));
        }
        catch(IOException ioe){
            ioe.printStackTrace();
        }

        setBackground(Color.black);
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(this.imgbackground, 0, 0, (int) (width), (int) height, null);

        Graphics2D gp2d = (Graphics2D)g;

        gp2d.drawImage(this.imgpause,  (int) (width*0.393),(int) (height*0.074), (int) (width*0.267), (int) (height*0.132), null);
        // Insert here our drawing

        Graphics2D gc2d = (Graphics2D)g;

        gc2d.drawImage(this.imgcontinue, (int) (width*0.390), (int) (height*0.274), (int) (width*0.267), (int) (height*0.132), null);
        //insert start button

        Graphics2D gm2d = (Graphics2D)g;

        gm2d.drawImage(this.imgmenu, (int) (width*0.416), (int) (height*0.474), (int) (width*0.203), (int) (height*0.157), null);
        //insert start button

        Graphics2D ge2d = (Graphics2D)g;

        ge2d.drawImage(this.imgexit, (int) (width*0.427), (int) (height*0.674), (int) (width*0.172), (int) (height*0.120), null);
        //insert exit button

        if(fbcontinue) {
            Graphics2D gls2d = (Graphics2D) g;
            gls2d.drawImage(this.imgfbcontinue, (int) (width * 0.390), (int) (height * 0.274), (int) (width * 0.267), (int) (height * 0.132), null);
            repaint();
            //insert continue lux
        }

        if(fbmenu) {
            Graphics2D gls2d = (Graphics2D) g;
            gls2d.drawImage(this.imgfbmenu, (int) (width * 0.416), (int) (height * 0.474), (int) (width * 0.203), (int) (height * 0.157), null);
            //insert menu lux
        }

        if(fbexit) {
            Graphics2D gle2d = (Graphics2D) g;
            gle2d.drawImage(this.imgfbexit, (int) (width * 0.427), (int) (height * 0.674), (int) (width * 0.172), (int) (height * 0.120), null);
            //insert exit lux
        }

    }

    public void mouseClicked(MouseEvent e){
        if(this.continuegame.contains(e.getX(), e.getY()))	{
            this.gamecontinue = true;
            CardLayout cardLayout = (CardLayout) contentPane.getLayout();
            cardLayout.show(contentPane, "game");
            ControllerForView.getInstance().setBooleanMapElement("isRunning", true);
        }
        if(this.menu.contains(e.getX(), e.getY())){
            CardLayout cardLayout = (CardLayout) contentPane.getLayout();
            cardLayout.show(contentPane, "mainmenu");
            AudioManager.getInstance().PlayMainMenuSong();
            AudioManager.getInstance().StopGameSong();
            ControllerForView.getInstance().setBooleanMapElement("resetGame",true);
            ControllerForView.getInstance().clearGame();
            ControllerForView.getInstance().clearGameOver();
        }
        if(this.exit.contains(e.getX(), e.getY()))
            System.exit(0);
    }

    public void mouseEntered(MouseEvent e1){

    }

    public void mouseExited(MouseEvent e1){

    }

    public void mousePressed(MouseEvent e){

    }

    public void mouseReleased(MouseEvent e){

    }

    public void mouseDragged(MouseEvent e){

    }

    public void mouseMoved(MouseEvent e){
        fbcontinue = this.continuegame.contains(e.getX(), e.getY());
        fbmenu = this.menu.contains(e.getX(), e.getY());
        fbexit = this.exit.contains(e.getX(), e.getY());
        this.repaint();
    }

}