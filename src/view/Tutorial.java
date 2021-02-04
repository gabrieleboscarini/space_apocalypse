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

    private JPanel contentPane;

    BufferedImage img = null;
    BufferedImage ship = null;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    double width = screenSize.getWidth();
    double height = screenSize.getHeight();

    public Tutorial(JPanel panel) {

        contentPane = panel;
        setFocusable(true);
        setBackground(Color.black);
        loadFiles();
        startGame();
        addMouseListener(this);

    }

    public void loadFiles(){
        try {
            this.img = ImageIO.read(new File("utils/img/tutorial.png"));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        try {
            this.ship = ImageIO.read(new File("utils/img/navicelladx.png"));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(img, 0, 0, (int) width, (int) height, null);
        g.drawImage(ship,(int)ControllerForView.getInstance().spaceShipX(), (int)ControllerForView.getInstance().spaceShipY(),(int) (width*0.104), (int) (height*0.108),null);

    }

    public void startGame(){
        setInputMap(WHEN_ANCESTOR_OF_FOCUSED_COMPONENT, getInputMap());
        KeyStroke key = KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0);
        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(key, "pressed");
        getActionMap().put("pressed", new AbstractAction(){
            public void actionPerformed(ActionEvent arg0) {
                CardLayout cardLayout = (CardLayout) contentPane.getLayout();
                cardLayout.show(contentPane, "game");
                ControllerForView.getInstance().setBooleanMapElement("isRunning", true);
            }
        });
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        CardLayout cardLayout = (CardLayout) contentPane.getLayout();
        cardLayout.show(contentPane, "game");
        ControllerForView.getInstance().setBooleanMapElement("isRunning", true);
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