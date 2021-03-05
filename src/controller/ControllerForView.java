package controller;

import model.GameObject;
import view.AudioManager;
import view.View;
import model.Model;
import java.awt.*;
import java.io.*;
import java.util.LinkedList;
import java.util.Random;


public class ControllerForView implements IControllerForView {

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    double width = screenSize.getWidth();
    double height = screenSize.getHeight();

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
            distancey += (width*0.03);
            distancex = 0;
            for(int j=0 ; j<m; j++){
                matrix[i][j] = new Point((int)start.getX()+distancex, (int)start.getY()+distancey);
                if(rd.nextBoolean()) {
                    int casuale=rd.nextInt(type.length);
                    block.add(new GameObject(matrix[i][j].getX(), matrix[i][j].getY(),spaceShipX(),spaceShipY(), type[casuale]));
                }
                distancex +=(width*0.03);
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
            AudioManager.getInstance().PlaySummon();

        }
    }

    public void moveBlock(){
        for(int i=0; i<Model.getInstance().getblocklist().size(); i++){
            for(int j=0; j<Model.getInstance().getBlock(i).size(); j++){
                Model.getInstance().setBlockElement(i,j,BlockElement(i,j).getX() + BlockElement(i,0).getDeltaX()*(width*0.003),
                        BlockElement(i,j).getY() + BlockElement(i,0).getDeltaY()*(width*0.003)
                );
                if(BlockElement(i,j).getX()<0 || BlockElement(i,j).getX()>(width*1.2) || BlockElement(i,j).getY()<0 || BlockElement(i,j).getY()>(height*1.2) ){
                    Model.getInstance().removeBlockElement(i,j);
                }
            }
        }
    }

    public void moveGameObject(){
        for(int i =0; i< GameObjectList().size(); i++){

            String type = GameObject(i).getType();

            if (GameObject(i).getX() < -200 || GameObject(i).getX() > (width*1.2) || GameObject(i).getY() < -200 || GameObject(i).getY() > (height*1.2)) {
                Model.getInstance().removeGameObject(i);
            } else
            if(type.equals("bullet1") || type.equals("bullet2") || type.equals("bullet3") || type.equals("bullet4")){
                Model.getInstance().setGameObjects(i, GameObject(i).getX() + GameObject(i).getDeltaX()*(width*0.015),
                        GameObject(i).getY() + GameObject(i).getDeltaY()*(width*0.015));
            }else
            if (GameObject(i).getType().equals("explosion")) {
                Model.getInstance().setGameObjects(i, GameObject(i).getScaleDX(),
                        GameObject(i).getScaleSX() + (width*0.000000026));
                if (GameObject(i).getScaleSX() > (width*0.000000104)) {
                    Model.getInstance().removeGameObject(i);
                }

            } else
            if (GameObject(i).getType().equals("enemy")) {
                Model.getInstance().setGameObjects(i, GameObject(i).getX() + GameObject(i).getDeltaX() * (width*0.006),
                        GameObject(i).getY() + GameObject(i).getDeltaY() * (width*0.006));
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
                        Model.getInstance().removeGameObject(h);
                        Model.getInstance().removeGameObject(k);
                        AudioManager.getInstance().PlayScreamMonster();
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
                                break outerloop;
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
        double speed = width*0.005;
        if(getBooleanMapElement("MoveUp")){
            if(!(Model.getInstance().getSpaceShipY() < 0))
                Model.getInstance().setSpaceShip(spaceShipX(), (spaceShipY() -speed));
            else
                Model.getInstance().setSpaceShip(spaceShipX(), height);
        }
        if(getBooleanMapElement("MoveDown")){
            if(!(Model.getInstance().getSpaceShipY() > height))
                Model.getInstance().setSpaceShip(spaceShipX(), (spaceShipY() +speed));
            else
                Model.getInstance().setSpaceShip(spaceShipX(), 0);
        }
        if(getBooleanMapElement("MoveRight")){
            if(!(Model.getInstance().getSpaceShipX() > width))
                Model.getInstance().setSpaceShip(spaceShipX() +speed, spaceShipY());
            else
                Model.getInstance().setSpaceShip(0, spaceShipY());
        }
        if(getBooleanMapElement("MoveLeft")){
            if(!(Model.getInstance().getSpaceShipX() < 0))
                Model.getInstance().setSpaceShip(spaceShipX() -speed, spaceShipY());
            else
                Model.getInstance().setSpaceShip(width, spaceShipY());
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

    public void Savepunteggio(int indice0, int indice1, int indice2, int unita, int decine, int centinaia, int migliaia) throws IOException {
        File save = new File("champion.txt");
        FileWriter fw = new FileWriter(save);
        String s = indice0 + "." + indice1 + "." + indice2 + "." + migliaia + centinaia + decine + unita + ".";
        fw.write(s);
        fw.flush();
        fw.close();
    }

    public int readChampion(String type, int i)  throws IOException{
        int indice=0;
        BufferedReader reader = new BufferedReader(new FileReader("champion.txt"));
        String rs = reader.readLine();
        String[] splits = rs.split("\\.");
        if(type.equals("nickname")){
            String ind = splits[i];
            indice = Integer.parseInt(ind);
        }
        if(type.equals("champ")){
            String pc = splits[3];
            indice = Integer.parseInt(pc);
        }
        if(type.equals("Champ_score")){
            String score = splits[3];
            char c = score.charAt(i);
            indice = Character.getNumericValue(c);
        }
        return indice;
    }

    public static IControllerForView getInstance() {
        if (instance == null)
            instance = new ControllerForView();
        return instance;
    }
}