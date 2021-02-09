package controller;

import model.GameObject;

import java.awt.*;
import java.util.LinkedList;

public interface IControllerForView {

    void openStartWindow();

    void closeStartWindow();

    double spaceShipX();

    double spaceShipY();

    public Rectangle SpaceShipRectangle();

    void ruotaSpaceShip(double x, double y);

    double Angle();

    public LinkedList<GameObject> GameObjectList();

    void createBlockElement();

    public void removeBlockElement(int i,GameObject object,String btype);

    public void collisionDetection0();

    public void collisionDetection1();

    public void collision();

    public void clearGame();

    public void clearGameOver();

    void increaseScore(int i);

    public int getMapElement(String key);

    boolean getBooleanMapElement(String key);

    void setBooleanMapElement(String key, boolean var);

    void setMapElement(String key, int var);

    void MoveSpaceShip();

    void moveArrowUp();

    void moveArrowDown();

    public void moveBlock();

    public Rectangle BlockElementRectangle(int i, int j);

    public GameObject GameObject(int i);

    public GameObject BlockElement(int i, int j);

    public void createGameObject(double x, double y, double dx, double dy, String type);

    public void moveGameObject();

}