package view;

import controller.ControllerForView;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;


public class Game extends JPanel implements MouseMotionListener,MouseListener,KeyListener,MouseWheelListener {

    //private static final Game instance = null;
    private final JPanel contentPane;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    double width = screenSize.getWidth();
    double height = screenSize.getHeight();

    int XCenter = (int) (width * 0.0625 / 2);
    int YCenter = (int) (height * 0.111 / 2);

    public int orientation = 0;

    BufferedImage imgbackground,player1,player1_torretta1,player1_torretta2,player1_torretta3,
            player1_torretta4,bullet1, bullet2, bullet3, bullet4 , capsul1, capsul2, capsul3 ,
            capsul4, enemy,broke1,broke2,broke3,broke4,enemybroken,enemy2,enemybroken2,enemy2bullet;

    HashMap<String,BufferedImage> imageMap = new HashMap<>();

    public Game(JPanel panel) {

        contentPane = panel;
        setFocusable(true);
        addMouseMotionListener(this);
        addMouseWheelListener(this);
        addMouseListener(this);
        setBackground(Color.black);
        addKeyListener(this);
        loadImages();
        KeyActions();
    }

    public void loadImages() {

        String[] strings = {"sfondo", "Navicelladx", "Torretta_proiettile_0","Torretta_proiettile_1",
                "Torretta_proiettile_2","Torretta_proiettile_3","Proiettile_orange","Proiettile_blue","Proiettile_pink",
                "Proiettile_green","Ast_1","Ast_2","Ast_3","Ast_4","nemico_1","Ast_orange_Rotto","Ast_blue_Rotto",
                "Ast_pink_Rotto","Ast_green_Rotto" , "nemico_1_morto", "nemico_2", "nemico_2_morto", "nemico_2_bullet"};

        BufferedImage[] images = {imgbackground, player1,player1_torretta1,player1_torretta2,player1_torretta3,
                player1_torretta4,bullet1,bullet2,bullet3,bullet4,capsul1,capsul2,capsul3,capsul4,enemy,
                broke1,broke2,broke3,broke4,enemybroken,enemy2,enemybroken2,enemy2bullet};

        for (int i = 0; i < images.length; i++) {
            try {
                images[i] = ImageIO.read(new File("utils/img/SP_SH/" + strings[i] + ".png"));
                imageMap.put(strings[i],images[i]);
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image image = toolkit.getImage("utils/img/target.png");
        Cursor customCursor = toolkit.createCustomCursor(image , new Point(0,0), "utils/img/target.png");
        setCursor(customCursor);
    }

    @Override
    public void paintComponent(Graphics g){

        super.paintComponent(g);

        g.drawImage(imageMap.get("sfondo"), 0, 0, (int) (width), (int) height, null);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        drawSpaceShip(g2d);
        drawSpaceShipTorretta(g2d);
        drawAsteroid(g2d);
        drawGameObject(g2d);
    }

    public void drawSpaceShip(Graphics2D g2d){

        AffineTransform oldAT = g2d.getTransform();
        g2d.translate(XCenter+ ControllerForView.getInstance().spaceShipX(), YCenter+ ControllerForView.getInstance().spaceShipY());
        g2d.rotate(Math.toRadians(orientation));
        g2d.translate(-XCenter,-YCenter);
        g2d.drawImage(imageMap.get("Navicelladx"),0,0,XCenter*2, YCenter*2,null);
        g2d.setTransform(oldAT);
    }

    public void drawSpaceShipTorretta(Graphics2D g2d){

        AffineTransform oldAT = g2d.getTransform();
        g2d.translate(XCenter+ ControllerForView.getInstance().spaceShipX(), YCenter+ ControllerForView.getInstance().spaceShipY());
        g2d.rotate(ControllerForView.getInstance().Angle());
        g2d.translate(-XCenter,-YCenter);

        for(int i=0; i<4; i++){
            if(ControllerForView.getInstance().getMapElement("BulletType") == i){
                g2d.drawImage(imageMap.get("Torretta_proiettile_" +i), 0, 0, XCenter * 2, YCenter * 2, null);
            }
        }
        g2d.setTransform(oldAT);
    }

    public void drawAsteroid(Graphics2D g2d){
        for(int i=0; i<ControllerForView.getInstance().BlockList().size(); i++){
            for(int j=0; j<ControllerForView.getInstance().Block(i).size(); j++){

                AffineTransform t2 = new AffineTransform();
                t2.translate(ControllerForView.getInstance().BlockElement(i,j).getX(), ControllerForView.getInstance().BlockElement(i,j).getY());
                t2.scale(width*0.000345, height*0.00062);

                if(ControllerForView.getInstance().BlockElement(i, j).getType().equals("asteroid"))

                    switch (ControllerForView.getInstance().BlockElement(i, j).getColor()) {
                        case "orange":
                            g2d.drawImage(imageMap.get("Ast_1"), t2, null);
                            break;
                        case "blue":
                            g2d.drawImage(imageMap.get("Ast_2"), t2, null);
                            break;
                        case "pink":
                            g2d.drawImage(imageMap.get("Ast_3"), t2, null);
                            break;
                        case "green":
                            g2d.drawImage(imageMap.get("Ast_4"), t2, null);
                            break;
                    }
            }
        }
    }

    public void drawGameObject(Graphics2D g2d){

        String[] color = {"orange","blue","pink","green"};

        for(int i=0; i<ControllerForView.getInstance().GameObjectList().size(); i++){

            if(ControllerForView.getInstance().GameObject(i).getType().equals("bullet") ||
                    ControllerForView.getInstance().GameObject(i).getType().equals("broken")) {

                for (String s : color) {
                    if (ControllerForView.getInstance().GameObject(i).getType().equals("bullet") &&
                            ControllerForView.getInstance().GameObject(i).getColor().equals(s)) {
                        AffineTransform t = new AffineTransform();
                        t.translate(ControllerForView.getInstance().GameObject(i).getX(), ControllerForView.getInstance().GameObject(i).getY());
                        t.scale(width * 0.0001, height * 0.00018);
                        g2d.drawImage(imageMap.get("Proiettile_" + s), t, null);
                        break;
                    }else
                    if (ControllerForView.getInstance().GameObject(i).getType().equals("broken") &&
                            ControllerForView.getInstance().GameObject(i).getColor().equals(s)) {
                        AffineTransform t = new AffineTransform();
                        t.translate(ControllerForView.getInstance().GameObject(i).getX(), ControllerForView.getInstance().GameObject(i).getY());
                        t.scale(width * 0.000345, height * 0.00062);
                        g2d.drawImage(imageMap.get("Ast_" + s + "_Rotto"), t, null);
                        break;
                    }
                }
            }else
            switch (ControllerForView.getInstance().GameObject(i).getType()) {
                case "enemy": {
                    AffineTransform t = new AffineTransform();
                    t.translate(ControllerForView.getInstance().GameObject(i).getX(), ControllerForView.getInstance().GameObject(i).getY());
                    t.scale(width * 0.00015, height * 0.0003);
                    g2d.drawImage(imageMap.get("nemico_1"), t, null);
                    break;
                }
                case "enemy_broken" : {
                    AffineTransform t = new AffineTransform();
                    t.translate(ControllerForView.getInstance().GameObject(i).getX(), ControllerForView.getInstance().GameObject(i).getY());
                    t.scale(width * 0.00015, height * 0.0003);
                    g2d.drawImage(imageMap.get("nemico_1_morto"), t, null);
                    break;
                }
                case "enemy_2_broken" : {
                    AffineTransform t = new AffineTransform();
                    t.translate(ControllerForView.getInstance().GameObject(i).getX(), ControllerForView.getInstance().GameObject(i).getY());
                    t.scale(width * 0.00015, height * 0.0003);
                    g2d.drawImage(imageMap.get("nemico_2_morto"), t, null);
                    break;
                }
                case "enemy2" : {
                    AffineTransform t = new AffineTransform();
                    t.translate(ControllerForView.getInstance().GameObject(i).getX(), ControllerForView.getInstance().GameObject(i).getY());
                    t.scale(width * 0.00015, height * 0.0003);
                    g2d.drawImage(imageMap.get("nemico_2"), t, null);
                    break;
                }
                case "enemy_2_bullet" : {
                    AffineTransform t = new AffineTransform();
                    t.translate(ControllerForView.getInstance().GameObject(i).getX(), ControllerForView.getInstance().GameObject(i).getY());
                    t.scale(width * 0.00026, height * 0.00046);
                    g2d.drawImage(imageMap.get("nemico_2_bullet"), t, null);
                    break;
                }
            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

        double dx = e.getX() - ControllerForView.getInstance().spaceShipX()-(width*0.0625 /2); //coordinate del mouse
        double dy = e.getY() - ControllerForView.getInstance().spaceShipY()-(height*0.111 /2);
        ControllerForView.getInstance().ruotaSpaceShip(dx, dy);
    }

    @Override
    public void mousePressed(MouseEvent e) {

        String[] color = {"orange","blue","pink","green"};
        int index = ControllerForView.getInstance().getMapElement("BulletType");

        if(e.getButton() == MouseEvent.BUTTON1) {
            ControllerForView.getInstance().setBooleanMapElement("shooting", true);
            ControllerForView.getInstance().createGameObject( ControllerForView.getInstance().spaceShipX()+(width*0.04/2),
                    ControllerForView.getInstance().spaceShipY()+(height*0.068/2), e.getX(),
                    e.getY(),"bullet", color[index],0);
            AudioManager.getInstance().PlayShoot();
        }
    }

    public void mouseWheelMoved(MouseWheelEvent e){

        int bulletType = ControllerForView.getInstance().getMapElement("BulletType");

        if(e.getWheelRotation() < 0) {
            if (bulletType < 3) {
                bulletType++;
                ControllerForView.getInstance().setMapElement("BulletType", bulletType);
            }else{
                ControllerForView.getInstance().setMapElement("BulletType", 0);
            }
        }
        else if(e.getWheelRotation() > 0){
            if (bulletType > 0) {
                bulletType--;
                ControllerForView.getInstance().setMapElement("BulletType", bulletType);
            }else{
                ControllerForView.getInstance().setMapElement("BulletType", 3);
            }
        }

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()== KeyEvent.VK_ESCAPE){
            CardLayout cardLayout = (CardLayout) contentPane.getLayout();
            cardLayout.show(contentPane, "pause");
            ControllerForView.getInstance().setBooleanMapElement("isRunning", false);
        }

    }

    public void KeyActions(){
        setInputMap(WHEN_ANCESTOR_OF_FOCUSED_COMPONENT, getInputMap());
        KeyStroke key = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
        KeyStroke key1 = KeyStroke.getKeyStroke(KeyEvent.VK_W, 0);
        KeyStroke key2 = KeyStroke.getKeyStroke(KeyEvent.VK_A, 0);
        KeyStroke key3 = KeyStroke.getKeyStroke(KeyEvent.VK_D, 0);
        KeyStroke key4 = KeyStroke.getKeyStroke(KeyEvent.VK_S, 0);

        KeyStroke key5 = KeyStroke.getKeyStroke(KeyEvent.VK_1, 0);
        KeyStroke key6 = KeyStroke.getKeyStroke(KeyEvent.VK_2, 0);
        KeyStroke key7 = KeyStroke.getKeyStroke(KeyEvent.VK_3, 0);
        KeyStroke key8 = KeyStroke.getKeyStroke(KeyEvent.VK_4, 0);

        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(key, "pressedesc");
        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(key1, "pressedW");
        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(key2, "pressedA");
        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(key3, "pressedD");
        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(key4, "pressedS");

        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(key5, "Bullet_0");
        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(key6, "Bullet_1");
        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(key7, "Bullet_2");
        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(key8, "Bullet_3");

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
                orientation = 270;
                repaint();
            }
        });

        getActionMap().put("pressedA", new AbstractAction(){
            public void actionPerformed(ActionEvent arg0) {
                ControllerForView.getInstance().setBooleanMapElement("Move",true);
                ControllerForView.getInstance().setBooleanMapElement("MoveLeft",true);
                ControllerForView.getInstance().setBooleanMapElement("MoveRight",false);
                ControllerForView.getInstance().setBooleanMapElement("MoveDown",false);
                ControllerForView.getInstance().setBooleanMapElement("MoveUp",false);
                orientation = 180;
                repaint();
            }
        });

        getActionMap().put("pressedD", new AbstractAction(){
            public void actionPerformed(ActionEvent arg0) {
                ControllerForView.getInstance().setBooleanMapElement("Move",true);
                ControllerForView.getInstance().setBooleanMapElement("MoveRight",true);
                ControllerForView.getInstance().setBooleanMapElement("MoveDown",false);
                ControllerForView.getInstance().setBooleanMapElement("MoveLeft",false);
                ControllerForView.getInstance().setBooleanMapElement("MoveUp",false);
                orientation = 0;
                repaint();
            }
        });

        getActionMap().put("pressedS", new AbstractAction(){
            public void actionPerformed(ActionEvent arg0) {
                ControllerForView.getInstance().setBooleanMapElement("Move",true);
                ControllerForView.getInstance().setBooleanMapElement("MoveDown",true);
                ControllerForView.getInstance().setBooleanMapElement("MoveRight",false);
                ControllerForView.getInstance().setBooleanMapElement("MoveLeft",false);
                ControllerForView.getInstance().setBooleanMapElement("MoveUp",false);
                orientation = 90;
                repaint();
            }
        });

        getActionMap().put("Bullet_0", new AbstractAction(){
            public void actionPerformed(ActionEvent arg0) {
                ControllerForView.getInstance().setMapElement("BulletType", 0);
                repaint();
            }
        });

        getActionMap().put("Bullet_1", new AbstractAction(){
            public void actionPerformed(ActionEvent arg0) {
                ControllerForView.getInstance().setMapElement("BulletType", 1);
                repaint();
            }
        });

        getActionMap().put("Bullet_2", new AbstractAction(){
            public void actionPerformed(ActionEvent arg0) {
                ControllerForView.getInstance().setMapElement("BulletType", 2);
                repaint();
            }
        });

        getActionMap().put("Bullet_3", new AbstractAction(){
            public void actionPerformed(ActionEvent arg0) {
                ControllerForView.getInstance().setMapElement("BulletType", 3);
                repaint();
            }
        });

    }

    @Override
    public void mouseClicked(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
    @Override
    public void mouseDragged(MouseEvent e) {}
    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) {}
}
