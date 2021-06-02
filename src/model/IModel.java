package model;

import java.awt.*;
import java.util.LinkedList;

public interface IModel {

     double getScreenWidth();

     double getScreenHeight();

     double getSpaceShipX();

     double getSpaceShipY();

     Rectangle getSpaceShipRectangle();

     void setAngle(double x);

     double getAngle();

     void initGame();

     void clearGame();

     void clearGameOver();

     int getMapElement(String key);

     void setMapElement(String key, Integer x);

     boolean getBooleanMapElement(String key);

     void setBooleanMapElement(String key, boolean var);

     void setSpaceShip(double x, double y /*double destX, double destY*/);

     LinkedList<GameObject> getGameObjectList();

     LinkedList<LinkedList<GameObject>> getblocklist();

     LinkedList<GameObject> getBlock(int i);

     void addBlock(LinkedList<GameObject> block);

     void removeBlock(int i);

     void setBlockElement(int i, int j, double x, double y);

     GameObject getBlockElement(int i, int j);

     Rectangle getBlockElementRectangle(int i, int j);

     void removeBlockElement(int i, int j);

     GameObject getGameObject(int i);

     void addGameObject(GameObject block);

     void removeGameObject(int i);

     void setGameObjects(int i, double x, double y, int TTL);

     Rectangle getGameObjectRectangle(int i);

}