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

 /*    void setBullet(int i, double x, double y);

     double getBulletDeltaX(int i);

     double getBulletDeltaY(int i);

     void addBullet(GameObject block);

     void removeBullet(int i);

     double getBulletX(int i);

     double getBulletY(int i);

     int getbSize();

     int geteSize();

     int gete2Size();

     double getEnemyX(int i);

     double getEnemyY(int i);

     double getEnemyDeltaX(int i);

     double getEnemyDeltaY(int i);

     void addEnemy(GameObject block);

     void removeEnemy(int i);

     void setEnemy2(int i, double x, double y);

     double getEnemy2X(int i);

     double getEnemy2Y(int i);

     double getEnemy2DeltaX(int i);

     double getEnemy2DeltaY(int i);

     void addEnemy2(GameObject block);

     void removeEnemy2(int i);

     void setEnemy(int i, double x, double y); */

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

     void setBlockElement(int i, int j, double x, double y);

     GameObject getBlockElement(int i, int j);

     Rectangle getBlockElementRectangle(int i, int j);

     void removeBlockElement(int i, int j);

     GameObject getGameObject(int i);

     void addGameObject(GameObject block);

     void removeGameObject(int i);

     void setGameObjects(int i, double x, double y);

     Rectangle getGameObjectRectangle(int i);

}