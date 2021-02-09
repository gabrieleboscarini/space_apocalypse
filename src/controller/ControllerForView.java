package controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import model.GameObject;
import view.AudioManager;
import view.Game;
import view.View;
import model.Model;
import java.awt.*;
import java.util.LinkedList;
import java.util.Random;


public class ControllerForView implements IControllerForView {

    private static ControllerForView instance = null;

    public void openStartWindow() {
        View.getInstance().openStartWindow();
    }

    public void closeStartWindow() {
        View.getInstance().closeStartWindow();
    }

    public int getMapElement(String key){
        return Model.getInstance().getMapElement(key);
    }

    public void setMapElement(String key, int var){
        Model.getInstance().setMapElement(key, var);
    }

    public boolean getBooleanMapElement(String key){
        return Model.getInstance().getBooleanMapElement(key);
    }

    public void setBooleanMapElement(String key, boolean var){
        Model.getInstance().setBooleanMapElement(key, var);
    }

    public void clearGame(){
        Model.getInstance().clearGame();
    }

    public void clearGameOver(){
        Model.getInstance().clearGameOver();
    }

    public double spaceShipX(){
        return Model.getInstance().getSpaceShipX();
    }

    public double spaceShipY(){
        return Model.getInstance().getSpaceShipY();
    }

    public double Angle(){
        return Model.getInstance().getAngle();
    }

    public void ruotaSpaceShip(double x, double y){
        Model.getInstance().setAngle( Math.atan2(y,x));
    }

    public LinkedList<GameObject> GameObjectList(){
        return Model.getInstance().getGameObjectList();
    }

    public GameObject GameObject(int i){
        return Model.getInstance().getGameObject(i);
    }

    public GameObject BlockElement(int i, int j){
        return Model.getInstance().getBlockElement(i,j);
    }

    public Rectangle SpaceShipRectangle(){
        return Model.getInstance().getSpaceShipRectangle();
    }

    public Rectangle BlockElementRectangle(int i, int j){return Model.getInstance().getBlockElementRectangle(i,j);}

    public void createGameObject(double x, double y, double dx, double dy, String type){
        Model.getInstance().addGameObject(new GameObject(x,y,dx,dy,type));
    }

    public void createBlockElement(){

        double x = 0;
        double y = 0;

        int lato = (int)(Math.random() * 4) + 1;  //scelta randomica del lato dello spawn

        if(lato == 0){  //spawn dal lato sinistro
            x = 0;
            y = (Math.random() * (Model.getInstance().getScreenHeight()+1));  //scelta randomica del punto del lato da dove spawna
        }

        if(lato == 1){
            y = 0;
            x = (Math.random() * (Model.getInstance().getScreenWidth() +1));
        }

        if(lato == 2){
            x = Model.getInstance().getScreenWidth();
            y = (Math.random() * (Model.getInstance().getScreenHeight() +1));
        }

        if(lato == 3){
            x = (Math.random() * (Model.getInstance().getScreenWidth() +1));
            y = Model.getInstance().getScreenHeight();
        }

        int n = (int)(Math.random() * 4) + 1;
        int m = (int)(Math.random() * 4) + 1;
        int distancex;
        int distancey = 0;

        String [] type = {"capsul1", "capsul2", "capsul3", "capsul4"};
        Random rd = new Random();

        Point[][] matrix = new Point[n][m];
        Point start = new Point((int)x,(int)y);

        LinkedList<GameObject> block = new LinkedList<>();

        for(int i=0; i< n ; i++){
            distancey += 50;
            distancex = 0;
            for(int j=0 ; j<m; j++){
                matrix[i][j] = new Point((int)start.getX()+distancex, (int)start.getY()+distancey);
                if(rd.nextBoolean()) {
                    int casuale=rd.nextInt(type.length);
                    block.add(new GameObject(matrix[i][j].getX(), matrix[i][j].getY(),spaceShipX(),spaceShipY(), type[casuale]));
                }
                distancex +=50;
            }
        }

        Model.getInstance().addBlock(block);
    }

    public void removeBlockElement(int i,GameObject object,String btype) {
        if (object.getType().equals("capsul1") && btype.equals("bullet1") ||
                object.getType().equals("capsul2") && btype.equals("bullet2") ||
                object.getType().equals("capsul3") && btype.equals("bullet3") ||
                object.getType().equals("capsul4") && btype.equals("bullet4")){
            for(int j =0; j<Model.getInstance().getBlock(i).size(); j++){
                if(BlockElement(i,j).getType().equals(object.getType())){
                    Model.getInstance().removeBlockElement(i,j);
                    j--;
                }
            }
        }else{
            createGameObject(object.getX(),object.getY(),spaceShipX(),spaceShipY(), "enemy");
        }
    }

    public void moveBlock(){
        for(int i=0; i<Model.getInstance().getblocklist().size(); i++){
            for(int j=0; j<Model.getInstance().getBlock(i).size(); j++){
                Model.getInstance().setBlockElement(i,j,BlockElement(i,j).getX() + BlockElement(i,0).getDeltaX()*5,
                        BlockElement(i,j).getY() + BlockElement(i,0).getDeltaY()*5
                );
                if(BlockElement(i,j).getX()<0 || BlockElement(i,j).getX()>1900 || BlockElement(i,j).getY()<0 || BlockElement(i,j).getY()>1300 ){
                    Model.getInstance().removeBlockElement(i,j);
                }
            }
        }
    }

    public void moveGameObject(){
        for(int i =0; i< GameObjectList().size(); i++){

            String type = GameObject(i).getType();

            if (GameObject(i).getX() < 0 || GameObject(i).getX() > 1900 || GameObject(i).getY() < 0 || GameObject(i).getY() > 1300) {
                Model.getInstance().removeGameObject(i);
            } else
            if(type.equals("bullet1") || type.equals("bullet2") || type.equals("bullet3") || type.equals("bullet4")){
                Model.getInstance().setGameObjects(i, GameObject(i).getX() + GameObject(i).getDeltaX()*20,
                        GameObject(i).getY() + GameObject(i).getDeltaY()*20);
            }else
                if (GameObject(i).getType().equals("explosion")) {
                    Model.getInstance().setGameObjects(i, GameObject(i).getScaleDX(),
                            GameObject(i).getScaleSX() + 0.00005);
                    if (GameObject(i).getScaleSX() > 0.0002) {
                        Model.getInstance().removeGameObject(i);
                    }

                } else
                    if (GameObject(i).getType().equals("enemy")) {
                        Model.getInstance().setGameObjects(i, GameObject(i).getX() + GameObject(i).getDeltaX() * 7,
                                GameObject(i).getY() + GameObject(i).getDeltaY() * 7);
                    }
        }
    }

    public boolean checkCollision(Rectangle r, Rectangle r1){
        boolean check = false;
        if(r.intersects(r1)){
            check = true;
        }

        return check;
    }

    public void collisionDetection0(){
        for(int k=0; k< GameObjectList().size(); k++){
            for(int h =0; h< GameObjectList().size(); h++) {
                if (h != k && !GameObject(h).getType().equals("explosion") && !GameObject(k).getType().equals("explosion"))
                    if (checkCollision(Model.getInstance().getGameObjectRectangle(k), Model.getInstance().getGameObjectRectangle(h))) {
                        Model.getInstance().removeGameObject(k);
                        AudioManager.getInstance().PlayExploding();
                        double explosionX = GameObject(k).getX();
                        double explosionY = GameObject(k).getY();
                        createGameObject(explosionX, explosionY, 0, 0, "explosion");
                        break;
                    }
            }
        }
    }

    public void collisionDetection1(){
        outerloop:
        for (int i = 0; i < Model.getInstance().getblocklist().size() ; i++) {
            for (int j = 0; j < Model.getInstance().getBlock(i).size(); j++) {
                if (checkCollision(SpaceShipRectangle(), BlockElementRectangle(i,j))) {
                    setBooleanMapElement("isRunning", false);
                    AudioManager.getInstance().StopGameSong();
                    AudioManager.getInstance().PlayCocomero();
                    AudioManager.getInstance().PlayGameOverSe();
                    AudioManager.getInstance().PlayGameOverSong();
                    setBooleanMapElement("gameover", true);
                    setBooleanMapElement("resetGame", true);
                    break outerloop;
                } else
                    for (int k = 0; k < GameObjectList().size(); k++) {
                        if(!GameObject(k).getType().equals("explosion"))
                            if (checkCollision(Model.getInstance().getGameObjectRectangle(k), BlockElementRectangle(i, j)) &&
                                    !GameObject(k).getType().equals("enemy")) {
                                AudioManager.getInstance().PlayExploding();
                                removeBlockElement(i, BlockElement(i, j), GameObject(k).getType());
                                Model.getInstance().removeGameObject(k);
                                increaseScore(3);
                                break;
                            } else if(checkCollision(Model.getInstance().getGameObjectRectangle(k),SpaceShipRectangle()) && GameObject(k).getType().equals("enemy")){
                                setBooleanMapElement("isRunning", false);
                                AudioManager.getInstance().StopGameSong();
                                AudioManager.getInstance().PlayCocomero();
                                AudioManager.getInstance().PlayGameOverSe();
                                AudioManager.getInstance().PlayGameOverSong();
                                setBooleanMapElement("gameover", true);
                                setBooleanMapElement("resetGame", true);
                                break outerloop;
                            }
                    }
            }
        }
    }

    public void collision(){
        collisionDetection0();
        collisionDetection1();
    }

    public void increaseScore(int i){

        int unita = getMapElement("unita");
        int decine = getMapElement("decine");
        int centinaia = getMapElement("centinaia");
        int migliaia = getMapElement("migliaia");

        setMapElement("score", getMapElement("score")+i);
        setMapElement("unita",unita +=i);
        if(unita>9) {
            setMapElement("unita", unita -10);
            //decine++;
            setMapElement("decine", decine +=1);
            if(decine>9){
                setMapElement("decine",0);
                //centinaia++;
                setMapElement("centinaia", centinaia +=1);
                if(centinaia>9){
                    setMapElement("centinaia",0);
                    migliaia++;
                    setMapElement("migliaia", migliaia);
                }
            }
        }
    }

    public void MoveSpaceShip(){
        if(getBooleanMapElement("MoveUp")){
            if(!(Model.getInstance().getSpaceShipY() < 0))
                Model.getInstance().setSpaceShip(spaceShipX(), (spaceShipY() -10));
        }
        if(getBooleanMapElement("MoveDown")){
            if(!(Model.getInstance().getSpaceShipY() > 950))
                Model.getInstance().setSpaceShip(spaceShipX(), (spaceShipY() +10));
        }
        if(getBooleanMapElement("MoveRight")){
            if(!(Model.getInstance().getSpaceShipX() > 1800))
                Model.getInstance().setSpaceShip(spaceShipX() +10, spaceShipY());
        }
        if(getBooleanMapElement("MoveLeft")){
            if(!(Model.getInstance().getSpaceShipX() < 0 ))
                Model.getInstance().setSpaceShip(spaceShipX() -10, spaceShipY());
        }

    }

    public void moveArrowUp(){
        int index1 = getMapElement("index1");

        setBooleanMapElement("freccia_su",true);
        setBooleanMapElement("freccia_giu",false);

        if(index1<36)
            index1++;
        setMapElement("index1", index1);
        if(index1==36)
            index1 =0;
        setMapElement("index1", index1);
    }

    public void moveArrowDown(){

        int index1 = getMapElement("index1");

        setBooleanMapElement("freccia_su",false);
        setBooleanMapElement("freccia_giu",true);

        if(index1>0)
            index1--;
        setMapElement("index1", index1);
        if(index1==0)
            index1 =36;
        setMapElement("index1", index1);

    }

    public static IControllerForView getInstance() {
        if (instance == null)
            instance = new ControllerForView();
        return instance;
    }


}