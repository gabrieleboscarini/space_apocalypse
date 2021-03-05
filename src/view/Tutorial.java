package view;

import controller.ControllerForView;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Tutorial extends JPanel implements MouseListener {

    private final JPanel contentPane;

    BufferedImage st = null;
    BufferedImage info = null;
    BufferedImage t1 = null;
    BufferedImage t2 = null;
    BufferedImage t3 = null;
    BufferedImage t4 = null;
    BufferedImage ship = null;
    BufferedImage player1_torretta1 = null;

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    double width = screenSize.getWidth();
    double height = screenSize.getHeight();

    public Tutorial(JPanel panel) {

        setFocusTraversalKeysEnabled(false);
        setFocusable(true);

        contentPane = panel;
        setBackground(Color.black);
        loadFiles();
        addMouseListener(this);
        KeyBoardActions();
    }

    public void loadFiles(){
        try {
            this.st = ImageIO.read(new File("utils/img/sfondo.png"));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        try {
            this.info = ImageIO.read(new File("utils/img/info.png"));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        try {
            this.t1 = ImageIO.read(new File("utils/img/tutorial_1.png"));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        try {
            this.t2 = ImageIO.read(new File("utils/img/tutorial_2.png"));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        try {
            this.t3 = ImageIO.read(new File("utils/img/tutorial_3.png"));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        try {
            this.t4 = ImageIO.read(new File("utils/img/tutorial_4.png"));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        try {
            this.ship = ImageIO.read(new File("utils/img/SP_SH/navicelladx.png"));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        try {
            this.player1_torretta1 = ImageIO.read(new File("utils/img/SP_SH/Torretta_proiettile_1.png"));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(st, 0, 0, (int) width, (int) height, null);
        g.drawImage(info, 0, 0, (int) width, (int) height, null);

        if(ControllerForView.getInstance().getMapElement("avanzamento") == 0){
            g.drawImage(this.t1, 0, 0, (int) width, (int) height, null);
            repaint();
        }
        if(ControllerForView.getInstance().getMapElement("avanzamento") == 1){
            g.drawImage(this.t2, 0, 0, (int) width, (int) height, null);
            repaint();
        }

        if(ControllerForView.getInstance().getMapElement("avanzamento") == 2) {
            g.drawImage(t3, 0, 0, (int) width, (int) height, null);
            g.drawImage(info, 0, 0, (int) width, (int) height, null);
            repaint();
        }

        if(ControllerForView.getInstance().getMapElement("avanzamento") == 3){
            g.drawImage(t4, 0, 0, (int) width, (int) height, null);
            g.drawImage(ship,(int)ControllerForView.getInstance().spaceShipX(), (int)ControllerForView.getInstance().spaceShipY(),
                    (int) (width*0.0625), (int) (height*0.111),null);
            g.drawImage(player1_torretta1,(int)ControllerForView.getInstance().spaceShipX(), (int)ControllerForView.getInstance().spaceShipY(),
                    (int) (width*0.0625), (int) (height*0.111),null);
            repaint();
        }

    }

    public void startGame(){
        ControllerForView.getInstance().setMapElement("avanzamento", 0);
        CardLayout cardLayout = (CardLayout) contentPane.getLayout();
        cardLayout.show(contentPane, "game");
        ControllerForView.getInstance().setBooleanMapElement("isRunning", true);
    }

    public void KeyBoardActions(){
        setInputMap(WHEN_ANCESTOR_OF_FOCUSED_COMPONENT, getInputMap());
        KeyStroke key = KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0);
        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(key, "pressed");
        getActionMap().put("pressed", new AbstractAction(){
            public void actionPerformed(ActionEvent arg0) {
                startGame();
            }
        });
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        int av = ControllerForView.getInstance().getMapElement("avanzamento") + 1;
        if (ControllerForView.getInstance().getMapElement("avanzamento") == 3)
            startGame();
        else
            ControllerForView.getInstance().setMapElement("avanzamento", av);
        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

}
