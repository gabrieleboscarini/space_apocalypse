package view;

import controller.ControllerForView;
import model.Model;

import javax.imageio.ImageIO;
import javax.jws.WebParam;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class Game extends JPanel implements MouseMotionListener,MouseListener,KeyListener{

    private final static int MARGIN = 10;
    private static Game instance = null;
    private JPanel contentPane;

    BufferedImage imgbackground = null;
    BufferedImage player1 = null;
    BufferedImage bullet = null;
    BufferedImage enemy = null;
    BufferedImage enemy2 = null;
    BufferedImage enemyBullet = null;
    BufferedImage explosion = null;

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    double width = screenSize.getWidth();
    double height = screenSize.getHeight();

    int XCenter = (int) (width*0.104/2);
    int YCenter = (int) (height*0.108/2);

    public Game(JPanel panel) {

        contentPane = panel;
        setFocusable(true);
        addMouseMotionListener(this);
        addMouseListener(this);
        setBackground(Color.black);
        addKeyListener(this);
        loadFiles();
        KeyActions();
    }

    public void loadFiles(){
        try {
            this.imgbackground = ImageIO.read(new File("utils/img/sfondo_gameplay.png"));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        try {
            this.player1 = ImageIO.read(new File("utils/img/navicelladx.png"));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        try {
            this.bullet = ImageIO.read(new File("utils/img/bullet.png"));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        try {
            this.enemy = ImageIO.read(new File("utils/img/nemico_1.png"));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        try {
            this.enemy2 = ImageIO.read(new File("utils/img/nemico_2.png"));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        try {
            this.enemyBullet = ImageIO.read(new File("utils/img/nemico_2_bullet.png"));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        try {
            this.explosion = ImageIO.read(new File("utils/img/esplosione.png"));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image image = toolkit.getImage("utils/img/target.png");
        Cursor customCursor = toolkit.createCustomCursor(image , new Point(0,0), "utils/img/target.png");
        setCursor(customCursor);
    }

    @Override
    public void paintComponent(Graphics g){

        super.paintComponent(g);

        g.drawImage(this.imgbackground, 0, 0, (int) (width), (int) height, null);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        drawSpaceShip(g2d);
        drawBlockElement(g2d);
        drawGameObject(g2d);
    }

    public void drawSpaceShip(Graphics2D g2d){

        AffineTransform oldAT = g2d.getTransform();
        g2d.translate(XCenter+ ControllerForView.getInstance().spaceShipX(), YCenter+ ControllerForView.getInstance().spaceShipY());
        g2d.rotate(ControllerForView.getInstance().Angle());
        g2d.translate(-XCenter,-YCenter);
        g2d.drawImage(player1,0,0,XCenter*2, YCenter*2,null);
        g2d.setTransform(oldAT);
    }

    public void drawBlockElement(Graphics2D g2d){
           for(int i=0; i<Model.getInstance().getblocklist().size(); i++){
               for(int j=0; j<Model.getInstance().getBlock(i).size(); j++){

                   AffineTransform t2 = new AffineTransform();
                   t2.translate(Model.getInstance().getBlockElement(i,j).getX(), Model.getInstance().getBlockElement(i,j).getY());
                   t2.scale(width*0.00015, height*0.0003);

                   if(Model.getInstance().getBlockElement(i,j).getType().equals("enemy"))
                   g2d.drawImage(enemy, t2, null);
                   if(Model.getInstance().getBlockElement(i,j).getType().equals("enemy2"))
                       g2d.drawImage(enemy2, t2, null);
               }

           }

    }

    public void drawGameObject(Graphics2D g2d){

        for(int i=0; i<ControllerForView.getInstance().GameObjectList().size(); i++){
            if(ControllerForView.getInstance().GameObject(i).getType().equals("bullet")){
                AffineTransform t = new AffineTransform();
                t.translate(ControllerForView.getInstance().GameObject(i).getX(),ControllerForView.getInstance().GameObject(i).getY());
                t.scale(width*0.00002,height*0.000037 );
                g2d.drawImage(bullet, t, null);
            }
            if(ControllerForView.getInstance().GameObject(i).getType().equals("explosion")){
                AffineTransform t = new AffineTransform();
                t.translate(ControllerForView.getInstance().GameObject(i).getX(), ControllerForView.getInstance().GameObject(i).getY());
                t.scale(width*ControllerForView.getInstance().GameObject(i).getScaleSX(), height * ControllerForView.getInstance().GameObject(i).getScaleDX());
                g2d.drawImage(explosion, t, null);
            }
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {}

    @Override
    public void mouseMoved(MouseEvent e) {

        double dx = e.getX() - ControllerForView.getInstance().spaceShipX()-(width*0.104 /2);
        double dy = e.getY() - ControllerForView.getInstance().spaceShipY()-(height*0.108 /2);
        ControllerForView.getInstance().ruotaSpaceShip(dx, dy);
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {

        if(e.getButton() == MouseEvent.BUTTON1) {
            ControllerForView.getInstance().setBooleanMapElement("shooting", true);
            ControllerForView.getInstance().createBullet( ControllerForView.getInstance().spaceShipX()+(width*0.104/2),  ControllerForView.getInstance().spaceShipY()+(height*0.108/2), e.getX(), e.getY());
            AudioManager.getInstance().PlayShoot();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    public Game getInstance() {
        if (instance == null)
            instance = new Game(this);
        return instance;
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()== KeyEvent.VK_ESCAPE){
            CardLayout cardLayout = (CardLayout) contentPane.getLayout();
            cardLayout.show(contentPane, "pause");
            ControllerForView.getInstance().setBooleanMapElement("isRunning", false);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    public void KeyActions(){
        setInputMap(WHEN_ANCESTOR_OF_FOCUSED_COMPONENT, getInputMap());
        KeyStroke key = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
        KeyStroke key1 = KeyStroke.getKeyStroke(KeyEvent.VK_W, 0);
        KeyStroke key2 = KeyStroke.getKeyStroke(KeyEvent.VK_A, 0);
        KeyStroke key3 = KeyStroke.getKeyStroke(KeyEvent.VK_D, 0);
        KeyStroke key4 = KeyStroke.getKeyStroke(KeyEvent.VK_S, 0);
        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(key, "pressedesc");
        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(key1, "pressedW");
        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(key2, "pressedA");
        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(key3, "pressedD");
        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(key4, "pressedS");
        getActionMap().put("pressedesc", new AbstractAction(){
            public void actionPerformed(ActionEvent arg0) {
                CardLayout cardLayout = (CardLayout) contentPane.getLayout();
                cardLayout.show(contentPane, "pause");
                // ControllerForView.getInstance().setRunning(false);
                ControllerForView.getInstance().setBooleanMapElement("isRunning", false);
            }
        });

        getActionMap().put("pressedW", new AbstractAction(){
            public void actionPerformed(ActionEvent arg0) {
                ControllerForView.getInstance().setBooleanMapElement("Move",true);
                ControllerForView.getInstance().setBooleanMapElement("MoveUp",true);
                ControllerForView.getInstance().setBooleanMapElement("MoveRight",false);
                ControllerForView.getInstance().setBooleanMapElement("MoveLeft",false);
                ControllerForView.getInstance().setBooleanMapElement("MoveDown",false);
            }
        });

        getActionMap().put("pressedA", new AbstractAction(){
            public void actionPerformed(ActionEvent arg0) {
                ControllerForView.getInstance().setBooleanMapElement("Move",true);
                ControllerForView.getInstance().setBooleanMapElement("MoveLeft",true);
                ControllerForView.getInstance().setBooleanMapElement("MoveRight",false);
                ControllerForView.getInstance().setBooleanMapElement("MoveDown",false);
                ControllerForView.getInstance().setBooleanMapElement("MoveUp",false);
            }
        });

        getActionMap().put("pressedD", new AbstractAction(){
            public void actionPerformed(ActionEvent arg0) {
                ControllerForView.getInstance().setBooleanMapElement("Move",true);
                ControllerForView.getInstance().setBooleanMapElement("MoveRight",true);
                ControllerForView.getInstance().setBooleanMapElement("MoveDown",false);
                ControllerForView.getInstance().setBooleanMapElement("MoveLeft",false);
                ControllerForView.getInstance().setBooleanMapElement("MoveUp",false);
            }
        });

        getActionMap().put("pressedS", new AbstractAction(){
            public void actionPerformed(ActionEvent arg0) {
                ControllerForView.getInstance().setBooleanMapElement("Move",true);
                ControllerForView.getInstance().setBooleanMapElement("MoveDown",true);
                ControllerForView.getInstance().setBooleanMapElement("MoveRight",false);
                ControllerForView.getInstance().setBooleanMapElement("MoveLeft",false);
                ControllerForView.getInstance().setBooleanMapElement("MoveUp",false);
            }
        });
    }
}