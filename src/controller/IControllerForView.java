package controller;

import model.GameObject;

import java.awt.*;
import java.io.IOException;
import java.util.LinkedList;

public interface IControllerForView {

    void openStartWindow();

    //void closeStartWindow();

    double spaceShipX();

    double spaceShipY();

    Rectangle SpaceShipRectangle();

    void ruotaSpaceShip(double x, double y);

    double Angle();

    LinkedList<GameObject> GameObjectList();

    void createBlockElement();

    void removeBlockElement(int i, GameObject object, String btype);

    void collisionDetection0();

    void collisionDetection1();

    void collision();

    void clearGame();

    void clearGameOver();

    void increaseScore(int i);

    int getMapElement(String key);

    boolean getBooleanMapElement(String key);

    void setBooleanMapElement(String key, boolean var);

    void setMapElement(String key, int var);

    void MoveSpaceShip();

    void moveArrowUp();

    void moveArrowDown();

    void moveBlock();

    Rectangle BlockElementRectangle(int i, int j);

    GameObject GameObject(int i);

    GameObject BlockElement(int i, int j);

    void createGameObject(double x, double y, double dx, double dy, String type);

    void moveGameObject();

    public void Savepunteggio(int indice0, int indice1, int indice2, int unita, int decine, int centinaia, int migliaia) throws IOException;

    public int readChampion(String type, int i)  throws IOException;

}