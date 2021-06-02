package view;

import controller.ControllerForView;
import model.GameLoop;
import model.GameObject;
import model.GameTimer;
import model.Model;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class MainGUI extends JFrame {

    private final JPanel contentPane;
    private final Game game;

    private final GameLoop gameloop;
    private GameTimer timer;
    private GameTimer timer2;
    private GameTimer timer3;
    private static MainGUI instance = null;

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    double width = screenSize.getWidth();
    double height = screenSize.getHeight();

    JLabel score = new JLabel("Score: 0");


    public MainGUI() {

        super("menu...");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(screenSize);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);

        contentPane = new JPanel();

        contentPane.setLayout(new CardLayout());

        MainMenu mainmenu = new MainMenu(contentPane);
        Tutorial tutorial = new Tutorial(contentPane);
        game = new Game(contentPane);
        Pause pause = new Pause(contentPane);
        Game_Over gameover = new Game_Over(contentPane);


        contentPane.add(mainmenu, "mainmenu");
        contentPane.add(tutorial, "tutorial");
        contentPane.add(game, "game");
        contentPane.add(pause, "pause");
        contentPane.add(gameover, "game_over");

        setContentPane(contentPane);

        score.setOpaque(false);
        score.setFont(new Font("times", 15, (int) (width * 0.015)));
        game.add(score);

        pack();
        setLocationByPlatform(true);
        setVisible(true);

        gameloop = new GameLoop(this);
        TimerManager();
    }

    public void updateGame() {

        if (ControllerForView.getInstance().getBooleanMapElement("isRunning")) {

            ControllerForView.getInstance().collision();
            ControllerForView.getInstance().moveGameObject();
            ControllerForView.getInstance().moveBlock();
            playGameOver();
            game.repaint();
            timer.tick(3);
            timer2.tick(3);
            timer3.tick(100);
            score.setText("Size gameObjectList: " + ControllerForView.getInstance().GameObjectList().size() + "Size BlockList: " + Model.getInstance().getblocklist().size() + "    " +
                    "   Score: " + ControllerForView.getInstance().getMapElement("score"));


            if (ControllerForView.getInstance().getBooleanMapElement("Move")) {
                ControllerForView.getInstance().MoveSpaceShip();
            }
        }

        if (ControllerForView.getInstance().getBooleanMapElement("resetGame")) {
            timer.resetGame();
            timer2.resetGame();
            timer3.resetGame();
            ControllerForView.getInstance().setBooleanMapElement("resetGame", false);
        }
    }

    public void start() {
        gameloop.start();
    }

    public void TimerManager() {

        timer = new GameTimer(70) {
            @Override
            public void action() {
                ControllerForView.getInstance().createBlockElement();
            }
        };

        timer2 = new GameTimer(300) {
            @Override
            public void action() {

                double x = ControllerForView.getInstance().casualSpawn().getX();
                double y = ControllerForView.getInstance().casualSpawn().getY();
                ControllerForView.getInstance().createGameObject(x,y , (Math.random() * (width +1)), (Math.random() * (height +1)), "enemy2",null,0);
            }
        };

        timer3 = new GameTimer(200) {
            @Override
            public void action() {
                ControllerForView.getInstance().enemyFire();
            }
        };
    }

    public void playGameOver() {
        if (ControllerForView.getInstance().getBooleanMapElement("gameover")) {
            CardLayout cardLayout = (CardLayout) contentPane.getLayout();
            cardLayout.show(contentPane, "game_over");
        }
    }

    public static MainGUI getInstance() {
        if (instance == null)
            instance = new MainGUI();
        return instance;
    }

}