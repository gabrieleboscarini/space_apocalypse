package controller;

import model.GameObject;

import java.awt.*;
import java.io.IOException;
import java.util.LinkedList;

public interface IControllerForView {

    void openStartWindow();

    double spaceShipX();

    double spaceShipY();

    Rectangle SpaceShipRectangle();

    void ruotaSpaceShip(double x, double y);

    double Angle();

    LinkedList<GameObject> GameObjectList();

    LinkedList<LinkedList<GameObject>> BlockList();

    LinkedList<GameObject> Block(int i);

    void createBlockElement();

    void removeBlockElement(int i, int h, GameObject object, String bcolor);

    void collisionDetection0();

    void collisionDetection1();

    void collision();

    void clearGame();

    void clearGameOver();

    void UpdateScore(int i);

    int getMapElement(String key);

    boolean getBooleanMapElement(String key);

    void setBooleanMapElement(String key, boolean var);

    void setMapElement(String key, int var);

    void enemyFire();

    void MoveSpaceShip();

    void moveArrowUp();

    void moveArrowDown();

    void moveBlock();

    Rectangle BlockElementRectangle(int i, int j);

    GameObject GameObject(int i);

    GameObject BlockElement(int i, int j);

    void createGameObject(double x, double y, double dx, double dy, String type, String color, int TTL);

    void moveGameObject();

    void Savepunteggio(int indice0, int indice1, int indice2, int unita, int decine, int centinaia, int migliaia) throws IOException;

    int readChampion(String type, int i)  throws IOException;

    void updateChampion(int index1, int index2, int index3);

    Point casualSpawn();

}