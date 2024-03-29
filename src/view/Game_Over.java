package view;

import controller.ControllerForView;


import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.LinkedList;

public class Game_Over extends JPanel implements MouseInputListener {

    private static JPanel contentPane;

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    double width = screenSize.getWidth();
    double height = screenSize.getHeight();

    BufferedImage freccia_su,freccia_giu,freccia_su_fb,freccia_giu_fb,underscore;
    BufferedImage _0, _1, _2, _3, _4, _5, _6, _7, _8, _9;
    BufferedImage a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z;
    BufferedImage img, nemici, navicella, imgmenu, imgexit, imgfbmenu, imgfbexit, S_Y_N, freccia, nickname, score, champion;

    private final Rectangle2D.Double freccia_su1, freccia_giu1, freccia_su2, freccia_giu2, freccia_su3, freccia_giu3, menu, exit;


    private boolean fbfrecciasu;
    private boolean fbfrecciagiu;
    private boolean fbmenu;
    private boolean fbexit;
    private int indice1 = 0;
    private int indice2 = 0;
    private int indice3 = 0;

    private final LinkedList<BufferedImage> list = new LinkedList<>();
    private final LinkedList<BufferedImage> list1 = new LinkedList<>();

    public Game_Over(JPanel panel) {

        contentPane = panel;

        setFocusTraversalKeysEnabled(false);
        setFocusable(true);

        this.freccia_su1 = new Rectangle2D.Double((int) (width * 0.255), (int) (height * 0.43), (int) (width * 0.0312), (int) (height * 0.0555));
        this.freccia_giu1 = new Rectangle2D.Double((int) (width * 0.255), (int) (height * 0.60), (int) (width * 0.0312), (int) (height * 0.0555));
        this.freccia_su2 = new Rectangle2D.Double((int) (width * 0.315), (int) (height * 0.43), (int) (width * 0.0312), (int) (height * 0.0555));
        this.freccia_giu2 = new Rectangle2D.Double((int) (width * 0.315), (int) (height * 0.60), (int) (width * 0.0312), (int) (height * 0.0555));
        this.freccia_su3 = new Rectangle2D.Double((int) (width * 0.375), (int) (height * 0.43), (int) (width * 0.0312), (int) (height * 0.0555));
        this.freccia_giu3 = new Rectangle2D.Double((int) (width * 0.375), (int) (height * 0.60), (int) (width * 0.0312), (int) (height * 0.0555));
        this.menu = new Rectangle2D.Double(width * 0.070, height * 0.838, width * 0.180, height * 0.120);
        this.exit = new Rectangle2D.Double(width * 0.750, height * 0.856, width * 0.163, height * 0.112);
        loadImages();
        loadNickName();
        setBackground(Color.black);
        addMouseListener(this);
        addMouseMotionListener(this);
        KeyBoardActions();

    }

    public void loadNickName() {

        BufferedImage[] images = {_0, _1, _2, _3, _4, _5, _6, _7, _8, _9, underscore, a, b, c,
                d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s
                , t, u, v, w, x, y, z,};
        String[] strings = {"0", "1", "2", "3", "4", "5", "6", "7",
                "8", "9", "_",
                "A", "B", "C", "D", "E", "F", "G", "H",
                "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U",
                "V", "W", "X", "Y", "Z",};

        for (int i = 0; i < images.length; i++) {
            try {
                images[i] = ImageIO.read(new File("utils/img/G_O_IMG/" + strings[i] + ".png"));
                list.add(images[i]);
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }

    public void loadImages() {
        BufferedImage[] images = {img, freccia_su, freccia_giu, freccia_su_fb, freccia_giu_fb, imgmenu, imgexit, imgfbmenu, imgfbexit, S_Y_N, freccia,
                nickname, score, champion, nemici, navicella};
        String[] strings = {"game_over", "freccia_su", "freccia_giu",
                "freccia_su_fb", "freccia_giu_fb", "menu_g_o", "exit_g_o", "fb_menu_g_o", "fb_exit_g_o", "S_Y_N", "freccia", "nickname",
                "score", "champion", "nemici", "navicelladx_distrutta"};


        for (int i = 0; i < images.length; i++) {
            try {
                images[i] = ImageIO.read(new File("utils/img/G_O_IMG/" + strings[i] + ".png"));
                list1.add(images[i]);
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }

    public int getGameOverIndex(int i){
        int[] index = {indice1,indice2,indice3};
        return index[i];
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(list1.get(0), 0, 0, (int) (width), (int) height, null);

        Graphics2D g2d = (Graphics2D) g;

        g2d.drawImage(list1.get(14), (int) (width * 0.78), (int) (height * 0.1), (int) (width * 0.125), (int) (height * 0.22), null);
        g2d.drawImage(list1.get(15), (int) (width * 0.1), (int) (height * 0.1), (int) (width * 0.125), (int) (height * 0.22), null);

        drawNickName(g2d, (int) (width * 0.245), (int) (height * 0.5), (int) (width * 0.045), (int) (height * 0.08), indice1);
        drawNickName(g2d, (int) (width * 0.305), (int) (height * 0.5), (int) (width * 0.045), (int) (height * 0.08), indice2);
        drawNickName(g2d, (int) (width * 0.365), (int) (height * 0.5), (int) (width * 0.045), (int) (height * 0.08), indice3);

        if (ControllerForView.getInstance().getMapElement("avanzamento") != 3)
            g2d.drawImage(list1.get(9), (int) (width * 0.01), (int) (height * 0.48), (int) (width * 0.2), (int) (height * 0.12), null);

        if (ControllerForView.getInstance().getMapElement("avanzamento") == 0) {

            drawArrowUp(g2d, (int) (width * 0.255), (int) (height * 0.43), (int) (width * 0.0312), (int) (height * 0.0555));
            drawArrowDown(g2d, (int) (width * 0.255), (int) (height * 0.60), (int) (width * 0.0312), (int) (height * 0.0555));

            if (ControllerForView.getInstance().getBooleanMapElement("freccia_su")) {
                drawArrowLightUp(g2d, (int) (width * 0.255), (int) (height * 0.43), (int) (width * 0.0312), (int) (height * 0.0555));
            }

            if (ControllerForView.getInstance().getBooleanMapElement("freccia_giu")) {
                drawArrowLightDown(g2d, (int) (width * 0.255), (int) (height * 0.60), (int) (width * 0.0312), (int) (height * 0.0555));
            }
        }

        if (ControllerForView.getInstance().getMapElement("avanzamento") == 1) {

            drawArrowUp(g2d, (int) (width * 0.315), (int) (height * 0.43), (int) (width * 0.0312), (int) (height * 0.0555));
            drawArrowDown(g2d, (int) (width * 0.315), (int) (height * 0.60), (int) (width * 0.0312), (int) (height * 0.0555));
            g2d.drawImage(list1.get(10), (int) (width * 0.435), (int) (height * 0.5), (int) (width * 0.045), (int) (height * 0.08), null);

            if (ControllerForView.getInstance().getBooleanMapElement("freccia_su")) {
                drawArrowLightUp(g2d, (int) (width * 0.315), (int) (height * 0.43), (int) (width * 0.0312), (int) (height * 0.0555));
            }

            if (ControllerForView.getInstance().getBooleanMapElement("freccia_giu")) {
                drawArrowLightDown(g2d, (int) (width * 0.315), (int) (height * 0.60), (int) (width * 0.0312), (int) (height * 0.0555));
            }
        }
        if (ControllerForView.getInstance().getMapElement("avanzamento") == 2) {

            drawArrowUp(g2d, (int) (width * 0.375), (int) (height * 0.43), (int) (width * 0.0312), (int) (height * 0.0555));
            drawArrowDown(g2d, (int) (width * 0.375), (int) (height * 0.60), (int) (width * 0.0312), (int) (height * 0.0555));

            g2d.drawImage(list1.get(10), (int) (width * 0.435), (int) (height * 0.5), (int) (width * 0.045), (int) (height * 0.08), null);
            g2d.drawImage(list1.get(10), (int) (width * 0.475), (int) (height * 0.5), (int) (width * 0.045), (int) (height * 0.08), null);

            if (ControllerForView.getInstance().getBooleanMapElement("freccia_su")) {
                drawArrowLightUp(g2d, (int) (width * 0.375), (int) (height * 0.435), (int) (width * 0.0312), (int) (height * 0.0555));
            }

            if (ControllerForView.getInstance().getBooleanMapElement("freccia_giu")) {
                drawArrowLightDown(g2d, (int) (width * 0.375), (int) (height * 0.60), (int) (width * 0.0312), (int) (height * 0.0555));
            }
        }

        if (ControllerForView.getInstance().getMapElement("avanzamento") == 3) {

            g2d.drawImage(list1.get(5), (int) (width * 0.070), (int) (height * 0.838), (int) (width * 0.180), (int) (height * 0.120), null);
            g2d.drawImage(list1.get(6), (int) (width * 0.746), (int) (height * 0.848), (int) (width * 0.172), (int) (height * 0.120), null);

            g2d.drawImage(list1.get(10), (int) (width * 0.435), (int) (height * 0.5), (int) (width * 0.045), (int) (height * 0.08), null);
            g2d.drawImage(list1.get(10), (int) (width * 0.475), (int) (height * 0.5), (int) (width * 0.045), (int) (height * 0.08), null);
            g2d.drawImage(list1.get(10), (int) (width * 0.515), (int) (height * 0.5), (int) (width * 0.045), (int) (height * 0.08), null);
            g2d.drawImage(list1.get(11), (int) (width * 0.25), (int) (height * 0.42), (int) (width * 0.16), (int) (height * 0.05), null);
            g2d.drawImage(list1.get(12), (int) (width * 0.63), (int) (height * 0.42), (int) (width * 0.1), (int) (height * 0.05), null);
            g2d.drawImage(list1.get(13), (int) (width * 0.425), (int) (height * 0.62), (int) (width * 0.16), (int) (height * 0.05), null);

            drawScore(g2d, (int) (width * 0.58), (int) (height * 0.5), (int) (width * 0.045), (int) (height * 0.08), ControllerForView.getInstance().getMapElement("migliaia"));
            drawScore(g2d, (int) (width * 0.63), (int) (height * 0.5), (int) (width * 0.045), (int) (height * 0.08), ControllerForView.getInstance().getMapElement("centinaia"));
            drawScore(g2d, (int) (width * 0.68), (int) (height * 0.5), (int) (width * 0.045), (int) (height * 0.08), ControllerForView.getInstance().getMapElement("decine"));
            drawScore(g2d, (int) (width * 0.73), (int) (height * 0.5), (int) (width * 0.045), (int) (height * 0.08), ControllerForView.getInstance().getMapElement("unita"));

            //nickname and score of the champion
            try {
                drawNickName(g2d, (int) (width * 0.245), (int) (height * 0.7), (int) (width * 0.045), (int) (height * 0.08), ControllerForView.getInstance().readChampion("nickname",0));
                drawNickName(g2d, (int) (width * 0.305), (int) (height * 0.7), (int) (width * 0.045), (int) (height * 0.08),  ControllerForView.getInstance().readChampion("nickname",1));
                drawNickName(g2d, (int) (width * 0.365), (int) (height * 0.7), (int) (width * 0.045), (int) (height * 0.08),  ControllerForView.getInstance().readChampion("nickname",2));
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

            try {
                drawScore(g2d, (int) (width * 0.58), (int) (height * 0.7), (int) (width * 0.045), (int) (height * 0.08), ControllerForView.getInstance().readChampion("Champ_score",0));
                drawScore(g2d, (int) (width * 0.63), (int) (height * 0.7), (int) (width * 0.045), (int) (height * 0.08), ControllerForView.getInstance().readChampion("Champ_score",1));
                drawScore(g2d, (int) (width * 0.68), (int) (height * 0.7), (int) (width * 0.045), (int) (height * 0.08), ControllerForView.getInstance().readChampion("Champ_score",2));
                drawScore(g2d, (int) (width * 0.73), (int) (height * 0.7), (int) (width * 0.045), (int) (height * 0.08), ControllerForView.getInstance().readChampion("Champ_score",3));
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

            if (fbmenu) {
                g2d.drawImage(list1.get(7), (int) (width * 0.070), (int) (height * 0.838), (int) (width * 0.180), (int) (height * 0.120), null);
            }

            if (fbexit) {
                g2d.drawImage(list1.get(8), (int) (width * 0.746), (int) (height * 0.848), (int) (width * 0.172), (int) (height * 0.120), null);
            }
        }

    }

    public void drawNickName(Graphics2D g2d, int x1, int y1, int x2, int y2, int indice) {
        g2d.drawImage(list.get(indice), x1, y1, x2, y2, null);
    }

    public void drawArrowLightUp(Graphics2D g2d, int x1, int y1, int x2, int y2) {
        g2d.drawImage(list1.get(3), x1, y1, x2, y2, null);
    }

    public void drawArrowLightDown(Graphics2D g2d, int x1, int y1, int x2, int y2) {
        g2d.drawImage(list1.get(4), x1, y1, x2, y2, null);
    }

    public void drawArrowUp(Graphics2D g2d, int x1, int y1, int x2, int y2) {
        g2d.drawImage(list1.get(1), x1, y1, x2, y2, null);
    }

    public void drawArrowDown(Graphics2D g2d, int x1, int y1, int x2, int y2) {
        g2d.drawImage(list1.get(2), x1, y1, x2, y2, null);
    }

    public void drawScore(Graphics2D g2d, int x1, int y1, int x2, int y2, int indice) {
        g2d.drawImage(list.get(indice), x1, y1, x2, y2, null);
    }

    public void moveArrowUp() {
        ControllerForView.getInstance().moveArrowUp();

        if (ControllerForView.getInstance().getMapElement("avanzamento") == 0) {
            indice1 = ControllerForView.getInstance().getMapElement("index1");
            repaint();
        }

        if (ControllerForView.getInstance().getMapElement("avanzamento") == 1) {
            indice2 = ControllerForView.getInstance().getMapElement("index1");
            repaint();
        }

        if (ControllerForView.getInstance().getMapElement("avanzamento") == 2) {
            indice3 = ControllerForView.getInstance().getMapElement("index1");
            repaint();
        }
    }

    public void moveArrowDown() {
        ControllerForView.getInstance().moveArrowDown();
        if (ControllerForView.getInstance().getMapElement("avanzamento") == 0) {
            indice1 = ControllerForView.getInstance().getMapElement("index1");
            repaint();
        }

        if (ControllerForView.getInstance().getMapElement("avanzamento") == 1) {
            indice2 = ControllerForView.getInstance().getMapElement("index1");
            repaint();
        }

        if (ControllerForView.getInstance().getMapElement("avanzamento") == 2) {
            indice3 = ControllerForView.getInstance().getMapElement("index1");
            repaint();
        }

    }

    public void KeyBoardActions() {
        setInputMap(WHEN_ANCESTOR_OF_FOCUSED_COMPONENT, getInputMap());
        KeyStroke key0 = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0);
        KeyStroke key1 = KeyStroke.getKeyStroke(KeyEvent.VK_D, 0);
        KeyStroke key2 = KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0);
        KeyStroke key3 = KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0);
        KeyStroke key4 = KeyStroke.getKeyStroke(KeyEvent.VK_W, 0);
        KeyStroke key5 = KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0);
        KeyStroke key6 = KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0);
        KeyStroke key7 = KeyStroke.getKeyStroke(KeyEvent.VK_S, 0);
        KeyStroke key8 = KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0);
        KeyStroke key9 = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
        KeyStroke key10 = KeyStroke.getKeyStroke(KeyEvent.VK_A, 0);
        KeyStroke key11 = KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0);

        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(key0, "pressedGo");
        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(key1, "pressedGo");
        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(key2, "pressedGo");
        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(key3, "pressedUp");
        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(key4, "pressedUp");
        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(key5, "pressedUp");
        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(key6, "pressedDown");
        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(key7, "pressedDown");
        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(key8, "pressedDown");
        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(key9, "pressedBack");
        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(key10, "pressedBack");
        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(key11, "pressedBack");


        getActionMap().put("pressedGo", new AbstractAction() {
            public void actionPerformed(ActionEvent arg0) {
                int avanzamento = ControllerForView.getInstance().getMapElement("avanzamento") + 1;
                if (ControllerForView.getInstance().getMapElement("avanzamento") < 3) {
                    ControllerForView.getInstance().setMapElement("avanzamento", avanzamento);
                }
                if(ControllerForView.getInstance().getMapElement("avanzamento") == 3){
                    ControllerForView.getInstance().updateChampion(indice1, indice2,indice3);
                }
                ControllerForView.getInstance().setBooleanMapElement("freccia_su", false);
                ControllerForView.getInstance().setBooleanMapElement("freccia_giu", false);
                ControllerForView.getInstance().setMapElement("index1", 0);
                repaint();
            }
        });

        getActionMap().put("pressedUp", new AbstractAction() {
            public void actionPerformed(ActionEvent arg0) {
                moveArrowUp();
                ControllerForView.getInstance().setBooleanMapElement("freccia_su", true);
                ControllerForView.getInstance().setBooleanMapElement("freccia_giu", false);
                repaint();
            }
        });

        getActionMap().put("pressedDown", new AbstractAction() {
            public void actionPerformed(ActionEvent arg0) {
                moveArrowDown();
                ControllerForView.getInstance().setBooleanMapElement("freccia_su", false);
                ControllerForView.getInstance().setBooleanMapElement("freccia_giu", true);
                repaint();
            }
        });

        getActionMap().put("pressedBack", new AbstractAction() {
            public void actionPerformed(ActionEvent arg0) {
                if (ControllerForView.getInstance().getMapElement("avanzamento") > 0 && ControllerForView.getInstance().getMapElement("avanzamento") < 3) {
                    int avanzamento = ControllerForView.getInstance().getMapElement("avanzamento")  - 1;
                    ControllerForView.getInstance().setMapElement("avanzamento", avanzamento);
                    ControllerForView.getInstance().setBooleanMapElement("freccia_su", false);
                    ControllerForView.getInstance().setBooleanMapElement("freccia_giu", false);
                    ControllerForView.getInstance().setMapElement("index1", 0);
                    repaint();
                }
            }
        });
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        if (ControllerForView.getInstance().getMapElement("avanzamento") == 0) {
            if (this.freccia_su1.contains(e.getX(), e.getY())) {
                moveArrowUp();
            }

            if (this.freccia_giu1.contains(e.getX(), e.getY())) {
                moveArrowDown();
            }

        }
        if (ControllerForView.getInstance().getMapElement("avanzamento") == 1) {
            if (this.freccia_su2.contains(e.getX(), e.getY())) {
                moveArrowUp();
            }

            if (this.freccia_giu2.contains(e.getX(), e.getY())) {
                moveArrowDown();
            }
        }
        if (ControllerForView.getInstance().getMapElement("avanzamento") == 2) {
            if (this.freccia_su3.contains(e.getX(), e.getY())) {
                moveArrowUp();
            }

            if (this.freccia_giu3.contains(e.getX(), e.getY())) {
                moveArrowDown();
            }
        }

        if (ControllerForView.getInstance().getMapElement("avanzamento") == 3) {

            if (this.menu.contains(e.getX(), e.getY())) {
                CardLayout cardLayout = (CardLayout) contentPane.getLayout();
                cardLayout.show(contentPane, "mainmenu");
                AudioManager.getInstance().StopGameOverSong();
                AudioManager.getInstance().PlayMainMenuSong();
                ControllerForView.getInstance().setBooleanMapElement("gameover", false);
                ControllerForView.getInstance().clearGame();
                ControllerForView.getInstance().clearGameOver();
                indice1 = 0;
                indice2 = 0;
                indice3 = 0;
            }
            if (this.exit.contains(e.getX(), e.getY()))
                System.exit(0);
            this.repaint();
        }

    }

    public void mouseMoved(MouseEvent e) {

        Rectangle2D.Double[] freccia_su = {freccia_su1, freccia_su2, freccia_su3};
        Rectangle2D.Double[] freccia_giu = {freccia_giu1, freccia_giu2, freccia_giu3};

        for (int i = 0; i < 3; i++) {
            if (ControllerForView.getInstance().getMapElement("avanzamento") == i) {
                fbfrecciasu = freccia_su[i].contains(e.getX(), e.getY());
                fbfrecciagiu = freccia_giu[i].contains(e.getX(), e.getY());
                ControllerForView.getInstance().setBooleanMapElement("freccia_su", fbfrecciasu);
                ControllerForView.getInstance().setBooleanMapElement("freccia_giu", fbfrecciagiu);
                this.repaint();

            }
        }

        if (ControllerForView.getInstance().getMapElement("avanzamento") == 3) {
            fbmenu = this.menu.contains(e.getX(), e.getY());
            fbexit = this.exit.contains(e.getX(), e.getY());
            ControllerForView.getInstance().setBooleanMapElement("freccia_su", fbfrecciasu);
            ControllerForView.getInstance().setBooleanMapElement("freccia_giu", fbfrecciagiu);
            this.repaint();
        }
    }

    public void mousePressed(MouseEvent mouseEvent) {}

    public void mouseReleased(MouseEvent mouseEvent) {}

    public void mouseEntered(MouseEvent mouseEvent) {}

    public void mouseExited(MouseEvent mouseEvent) {}

    public void mouseDragged(MouseEvent mouseEvent) {}
}