package view;

import controller.ControllerForView;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MainMenu extends JPanel implements MouseInputListener{

    private JPanel contentPane;

    private Rectangle2D.Double start;
    private Rectangle2D.Double exit;

    BufferedImage img = null;
    BufferedImage imgstart = null;
    BufferedImage imgexit = null;
    BufferedImage imgfbstart = null;
    BufferedImage imgfbexit = null;
    boolean fbstart = false;
    boolean fbexit = false;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    double width = screenSize.getWidth();
    double height = screenSize.getHeight();


    public MainMenu(JPanel panel) {

        contentPane = panel;

        this.start = new Rectangle2D.Double(width*0.070, height*0.838, width*0.180, height*0.120);

        this.exit = new Rectangle2D.Double(width*0.750, height*0.856, width*0.163, height*0.112);

        try{
            this.img = ImageIO.read(new File("utils/img/sfondo_1.png"));
        }
        catch(IOException ioe){
            ioe.printStackTrace();
        }

        try{
            this.imgstart = ImageIO.read(new File("utils/img/start.png"));
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
            this.imgfbstart = ImageIO.read(new File("utils/img/fb_start.png"));
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

        AudioManager.getInstance().PlayMainMenuSong();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(this.img, 0,0, (int) (width), (int) height, null);
        // Insert here our drawing

        Graphics2D gs2d = (Graphics2D)g;

        gs2d.drawImage(this.imgstart, (int) (width*0.070), (int) (height*0.838), (int) (width*0.180), (int) (height*0.120), null);
        //insert start button

        Graphics2D ge2d = (Graphics2D)g;

        ge2d.drawImage(this.imgexit, (int) (width*0.746), (int) (height*0.848), (int) (width*0.172), (int) (height*0.120), null);
        //insert exit button

        if(fbstart) {
            Graphics2D gls2d = (Graphics2D) g;
            gls2d.drawImage(this.imgfbstart, (int) (width * 0.070), (int) (height * 0.838), (int) (width * 0.180), (int) (height * 0.120), null);
            //insert start lux
        }

        if(fbexit) {
            Graphics2D gle2d = (Graphics2D) g;
            gle2d.drawImage(this.imgfbexit, (int) (width * 0.746), (int) (height * 0.848), (int) (width * 0.172), (int) (height * 0.120), null);
            //insert exit lux
        }


    }

    public void mouseClicked(MouseEvent e){
        if(this.start.contains(e.getX(), e.getY())) {
            handleNewGameEvent();
        }
        if(this.exit.contains(e.getX(), e.getY()))
            System.exit(0);
    }

    public void mouseEntered(MouseEvent e1){
        if(this.start.contains(e1.getX(), e1.getY())) {
            fbstart = true;
            this.repaint();
        }
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
        if(this.start.contains(e.getX(), e.getY())) {
            fbstart = true;
            this.repaint();
        }
        else{
            fbstart = false;
            this.repaint();
        }

        if(this.exit.contains(e.getX(), e.getY())) {
            fbexit = true;
            this.repaint();
        }
        else{
            fbexit = false;
            this.repaint();
        }


    }

    private void handleNewGameEvent() {
        CardLayout cardLayout = (CardLayout) contentPane.getLayout();
        cardLayout.show(contentPane, "tutorial");
        //cardLayout.show(contentPane, "game");
        AudioManager.getInstance().StopMainMenuSong();
        AudioManager.getInstance().PlayGameSong();
    }

}
