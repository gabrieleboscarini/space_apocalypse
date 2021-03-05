package model;

import java.awt.*;
import java.util.HashMap;
import java.util.LinkedList;

public class Model implements IModel {

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    double width = screenSize.getWidth();
    double height = screenSize.getHeight();

    private static Model instance = null;
    private double angle;

    GameObject spaceShip, TempObject, TempBlockElement;
    LinkedList<GameObject> TempBlock;

    private final LinkedList<GameObject> gameObjects = new LinkedList<>();
    private final LinkedList<LinkedList<GameObject>> blockList = new LinkedList<>();

    private boolean isRunning, shooting, isExploding,resetGame, gameover,MoveUp,MoveDown,MoveRight,MoveLeft,Move,freccia_su,freccia_giu;
    private int BulletType, score,unita,decine,centinaia,migliaia,index1,avanzamento;

    private final HashMap<String ,Integer> indexmap = new HashMap<>();
    private final HashMap<String,Boolean> booleanMap =  new HashMap<>();

    public Model(){
        this.initGame();
    }

    public void initGame(){
        spaceShip = new GameObject((int) ((width/2)-(width*0.0625/2)),(int) ((height/2)-(height*0.111/2)), 0,0,"spaceship");
        loadBooleanVar();
        loadIndexVar();
        setMapElement("BulletType", 1);
    }

    public void loadBooleanVar(){
        Boolean[] var = {isRunning, shooting, isExploding,resetGame, gameover,MoveUp,
                MoveDown,MoveRight,MoveLeft,Move,freccia_su,freccia_giu};
        String[] keys = {"isRunning","shooting","isExploding","resetGame",
                "gameover","MoveUp","MoveDown","MoveRight","MoveLeft","Move","freccia_su","freccia_giu"};
        for(int i=0; i<var.length; i++){
            booleanMap.put(keys[i],var[i]);
        }
    }

    public void loadIndexVar(){
        Integer[] var = {BulletType,score,unita,decine,centinaia,migliaia,index1,avanzamento};
        String[] keys = {"BulletType","score","unita","decine","centinaia","migliaia","index1","avanzamento"};
        for(int i=0; i<var.length; i++){
            indexmap.put(keys[i],var[i]);
        }
    }

    public double getScreenWidth(){
        return width;
    }

    public double getScreenHeight(){
        return height;
    }

    public int getMapElement(String key){
        return indexmap.get(key);
    }

    public void setMapElement(String key, Integer x){
        indexmap.replace(key,x);
    }

    public boolean getBooleanMapElement(String key){
        return booleanMap.get(key);
    }

    public void setBooleanMapElement(String key, boolean var){
        booleanMap.replace(key,var);
    }

    public LinkedList<GameObject> getGameObjectList(){
        return gameObjects;
    }

    public LinkedList<LinkedList<GameObject>> getblocklist(){
        return blockList;
    }

    public LinkedList<GameObject> getBlock(int i){
        TempBlock = blockList.get(i);
        return TempBlock;
    }

    public void addBlock(LinkedList<GameObject> block){
        blockList.add(block);
    }

    public void removeBlockElement(int i,int j){
        TempBlock = blockList.get(i);
        TempBlock.remove(j);
    }

    public void setBlockElement(int i,int j, double x, double y ){
        TempBlock = blockList.get(i);
        TempBlockElement = TempBlock.get(j);
        TempBlockElement.setX(x);
        TempBlockElement.setY(y);
    }

    public GameObject getBlockElement(int i,int j){
        TempBlock = blockList.get(i);
        TempBlockElement = TempBlock.get(j);
       return TempBlockElement;
    }

    public Rectangle getBlockElementRectangle(int i,int j){
        TempBlock = blockList.get(i);
        TempBlockElement = TempBlock.get(j);
        return TempBlockElement.ObjectRectangle(
                (int)TempBlockElement.getX()+(int) ((width*0.0417)/4),
                (int) TempBlockElement.getY()+(int) ((height*0.074)/4), (int) ((width*0.0417)/2), (int) ((height*0.074)/2));
    }

    public GameObject getGameObject(int i){
        TempObject = gameObjects.get(i);
        return TempObject;
    }

    public void addGameObject(GameObject block){
        gameObjects.add(block);
    }

    public void removeGameObject(int i){
        gameObjects.remove(i);
    }

    public void setGameObjects(int i,double x, double y){
        TempObject = gameObjects.get(i);
        if(TempObject.getType().equals("explosion")){
            TempObject.setScaleSX(x);
            TempObject.setScaleDX(y);
        }
        else{
            TempObject.setX(x);
            TempObject.setY(y);
        }
    }

    public Rectangle getGameObjectRectangle(int i){
        TempObject = gameObjects.get(i);
        Rectangle rect=null;
            if (TempObject.getType().equals("bullet1") || TempObject.getType().equals("bullet2") || TempObject.getType().equals("bullet3") || TempObject.getType().equals("bullet4")) {
                rect = TempObject.ObjectRectangle((int) TempObject.getX() + (int) ((width * 0.01894) / 4),
                        (int) TempObject.getY() + (int) ((height * 0.031191) / 4), (int) ((width * 0.01894) / 2),
                        (int) ((height * 0.031191) / 2));
            }
            if (TempObject.getType().equals("enemy")) {
                rect = TempObject.ObjectRectangle((int) TempObject.getX() + (int) ((width * 0.06) / 4),
                        (int) TempObject.getY() + (int) ((height * 0.066) / 4), (int) ((width * 0.06) / 2), (int) ((height * 0.066) / 2));
            }
            return rect;
    }

    public double getSpaceShipX(){
        return spaceShip.getX();
    }

    public double getSpaceShipY(){
        return spaceShip.getY();
    }

    public void setSpaceShip(double x, double y){
        spaceShip.setX(x);
        spaceShip.setY(y);
    }

    public Rectangle getSpaceShipRectangle(){
        return spaceShip.ObjectRectangle(
                (int)spaceShip.getX()+ (int) ((width*0.0625)/4),
                (int)spaceShip.getY()+ (int) ((height*0.111)/4), (int) ((width*0.0625)/2), (int) ((height*0.111)/2));
    }

    public double getAngle(){
        return angle;
    }

    public void setAngle(double x){
        angle = x;
    }

    public void clearGame(){
        gameObjects.clear();
        blockList.clear();
        setSpaceShip(((width/2)-(width*0.0625/2)), ((height/2)-(height*0.111/2)));
        setMapElement("score", 0);
    }

    public void clearGameOver(){
        setMapElement("unita", 0);
        setMapElement("decine", 0);
        setMapElement("centinaia", 0);
        setMapElement("migliaia", 0);
        setMapElement("avanzamento",0);
        setMapElement("index1",0);
    }

    public static IModel getInstance() {
        if (instance == null)
            instance = new Model();
        return instance;
    }
}