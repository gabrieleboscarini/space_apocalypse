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

    public LinkedList<LinkedList<GameObject>> BlockList(){
        return Model.getInstance().getblocklist();
    }

    public LinkedList<GameObject> Block(int i){
        return Model.getInstance().getBlock(i);
    }

    public GameObject BlockElement(int i, int j){
        return Model.getInstance().getBlockElement(i,j);
    }

    public Rectangle SpaceShipRectangle(){
        return Model.getInstance().getSpaceShipRectangle();
    }

    public Rectangle BlockElementRectangle(int i, int j){return Model.getInstance().getBlockElementRectangle(i,j);}

    public void createGameObject(double x, double y, double dx, double dy, String type, String color, int TTL){
        Model.getInstance().addGameObject(new GameObject(x,y,dx,dy,type,color,TTL));
    }

    public Point casualSpawn(){
        double x = 0;
        double y = 0;

        int lato = (int)(Math.random() * 4) + 1;  //scelta randomica del lato dello spawn

        if(lato == 0){  //spawn dal lato sinistro
            x = -(width * 0.0625);
            y = (Math.random() * (Model.getInstance().getScreenHeight()+1));  //scelta randomica del punto del lato da dove spawna
        }

        if(lato == 1){
            y = -(height * 0.111);
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
        return new Point((int) x, (int) y);
    }

    public void createBlockElement(){

        Point start = casualSpawn();

        int n = (int) (Math.random() * 4) + 1;
        int m = (int) (Math.random() * 4) + 1;
        Point[][] matrix = new Point[n][m];

        int distancex;
        int distancey = 0;

        String[] color = {"green", "orange", "pink", "blue"};
        Random rd = new Random();

        LinkedList<GameObject> block = new LinkedList<>();

            for (int i = 0; i < n; i++) {
                distancey += (width * 0.03);
                distancex = 0;
                for (int j = 0; j < m; j++) {
                    matrix[i][j] = new Point((int) start.getX() + distancex, (int) start.getY() + distancey);
                    if (rd.nextBoolean()) {
                        int casuale = rd.nextInt(color.length);
                        block.add(new GameObject(matrix[i][j].getX(), matrix[i][j].getY(), spaceShipX(), spaceShipY(), "asteroid", color[casuale], 0));
                    }
                    distancex += (width * 0.03);
                }
            }
            Model.getInstance().addBlock(block);
    }

    public void removeBlockElement(int i,int h, GameObject object,String bcolor) {  //rimuove tutti gli asteroidi dello stesso colore all'interno del blocco

        double x1 = object.getX();
        double y1 = object.getY();
        double dx1 = object.getDestinationX();
        double dy1 = object.getDestinationY();

        if(Model.getInstance().getBlockElement(i,h).getType().equals("asteroid")) {
            if (object.getColor().equals(bcolor)) {
                for (int j = 0; j < Model.getInstance().getBlock(i).size(); j++) {

                    double x = BlockElement(i, j).getX();
                    double y = BlockElement(i, j).getY();
                    double dx = BlockElement(i, j).getDestinationX();
                    double dy = BlockElement(i, j).getDestinationY();

                    if (BlockElement(i, j).getColor().equals(object.getColor())) {
                        Model.getInstance().removeBlockElement(i, j);
                        createGameObject(x, y, dx, dy, "broken", object.getColor(), 100);
                        j--;
                        UpdateScore(5);
                    }
                }
            } else {
                createGameObject(x1, y1, spaceShipX(), spaceShipY(), "enemy", null, 0);
                Model.getInstance().removeBlockElement(i, h);
                createGameObject(x1, y1, dx1, dy1, "broken", object.getColor(), 100);
                AudioManager.getInstance().PlaySummon();
                UpdateScore(1);
            }
            if(Model.getInstance().getBlock(i).size()==0)
                Model.getInstance().removeBlock(i);
        }
    }

    public void enemyFire(){
        for(int i=0; i<Model.getInstance().getGameObjectList().size(); i++) {
            if (GameObject(i).getType().equals("enemy2")) {
                double x = GameObject(i).getX();
                double y = GameObject(i).getY();
                createGameObject(x, y, spaceShipX(), spaceShipY(), "enemy_2_bullet", null, 100);
                AudioManager.getInstance().PlayCocomero();
            }
        }
    }

    public void moveBlock(){
        for(int i=0; i<Model.getInstance().getblocklist().size(); i++){
            for(int j=0; j<Model.getInstance().getBlock(i).size(); j++){
                Model.getInstance().setBlockElement(i,j,BlockElement(i,j).getX() + BlockElement(i,0).getDeltaX()*(width*0.003),
                        BlockElement(i,j).getY() + BlockElement(i,0).getDeltaY()*(width*0.003)
                );
                if(BlockElement(i,j).getX()<-(width/5) || BlockElement(i,j).getX()>(width*1.2) || BlockElement(i,j).getY()<-(height/5) || BlockElement(i,j).getY()>(height*1.2) ){
                    Model.getInstance().removeBlock(i);
                    break;
                }
            }
        }
    }

    public void moveGameObject(){
        for(int i =0; i< GameObjectList().size(); i++){

            String type = GameObject(i).getType();

            if (GameObject(i).getX() < -(width*0.5) || GameObject(i).getX() > (width*1.5) || GameObject(i).getY()
                    < -(height*0.5) || GameObject(i).getY() > (height*1.5) ||
                    GameObject(i).getTTL()<0) {
                Model.getInstance().removeGameObject(i);
            }else if(type.equals("bullet")){
                Model.getInstance().setGameObjects(i, GameObject(i).getX() + GameObject(i).getDeltaX()*(width*0.015),
                        GameObject(i).getY() + GameObject(i).getDeltaY()*(width*0.015),0);
            }else if (GameObject(i).getType().equals("enemy")) {
                Model.getInstance().setGameObjects(i, GameObject(i).getX() + GameObject(i).getDeltaX() * (width*0.006),
                        GameObject(i).getY() + GameObject(i).getDeltaY() * (width*0.006),GameObject(i).getTTL() - 10);
            } else if (GameObject(i).getType().equals("enemy_broken")) {
                Model.getInstance().setGameObjects(i, GameObject(i).getX() - GameObject(i).getDeltaX() * (width*0.006/3),
                        GameObject(i).getY() - GameObject(i).getDeltaY() * (width*0.006/3),GameObject(i).getTTL() - 20);
            }else if (GameObject(i).getType().equals("enemy2")) {
                Model.getInstance().setGameObjects(i, GameObject(i).getX() + GameObject(i).getDeltaX() * (width*0.006/1.5),
                        GameObject(i).getY() + GameObject(i).getDeltaY() * (width*0.006/1.5),GameObject(i).getTTL() - 10);
            }else if(type.equals("enemy_2_bullet")){
                Model.getInstance().setGameObjects(i, GameObject(i).getX() + GameObject(i).getDeltaX()*(width*0.008),
                        GameObject(i).getY() + GameObject(i).getDeltaY()*(width*0.008),0);
            }else if (GameObject(i).getType().equals("enemy_2_broken")) {
                    Model.getInstance().setGameObjects(i, GameObject(i).getX() - GameObject(i).getDeltaX() * (width*0.006/5),
                            GameObject(i).getY() - GameObject(i).getDeltaY() * (width*0.006/5),GameObject(i).getTTL() - 10);
            }else if(GameObject(i).getType().equals("broken")){
                Model.getInstance().setGameObjects(i, GameObject(i).getX() - GameObject(i).getDeltaX()*(width*0.003),
                        GameObject(i).getY() - GameObject(i).getDeltaY()*(width*0.003), GameObject(i).getTTL() - 10);
            }
        }
    }

    public void spaceShipHit(){
        setBooleanMapElement("isRunning", false);
        AudioManager.getInstance().StopGameSong();
        AudioManager.getInstance().PlayGameOverSe();
        AudioManager.getInstance().PlayGameOverSong();
        setBooleanMapElement("gameover", true);
        setBooleanMapElement("resetGame", true);
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

            String type = GameObject(k).getType();

            if(type.equals("enemy") || type.equals("enemy_2_bullet") || type.equals("enemy2")) {
                if (checkCollision(Model.getInstance().getGameObjectRectangle(k), SpaceShipRectangle()))
                    spaceShipHit();

                for(int h =0; h< GameObjectList().size(); h++) {

                    double x = GameObject(k).getX();
                    double y = GameObject(k).getY();
                    double dx = GameObject(k).getDestinationX();
                    double dy = GameObject(k).getDestinationY();

                    if (h != k && GameObject(h).getType().equals("bullet") && !type.equals("enemy_2_bullet"))
                        if (checkCollision(Model.getInstance().getGameObjectRectangle(k), Model.getInstance().getGameObjectRectangle(h))) {
                            Model.getInstance().removeGameObject(h);
                            if(h<k){
                                k--;
                            }
                            Model.getInstance().removeGameObject(k);
                            if(type.equals("enemy")) {
                                createGameObject(x, y, dx, dy, "enemy_broken", null, 100);
                                AudioManager.getInstance().PlayScreamMonster();
                                break;
                            }
                            if(type.equals("enemy2")){
                                createGameObject(x, y, dx, dy, "enemy_2_broken", null, 100);
                                AudioManager.getInstance().PlayScream();
                                UpdateScore(7);
                                break;
                            }

                        }
                }
            }
        }
    }

    public void collisionDetection1(){
        outerloop:
        for (int i = 0; i < Model.getInstance().getblocklist().size() ; i++) {
            for (int j = 0; j < Model.getInstance().getBlock(i).size(); j++) {
                if (checkCollision(SpaceShipRectangle(), BlockElementRectangle(i,j))) {
                    spaceShipHit();
                    break outerloop;
                }else for (int k = 0; k < GameObjectList().size(); k++) {
                    if(GameObject(k).getType().equals("bullet")){
                        if (checkCollision(Model.getInstance().getGameObjectRectangle(k), BlockElementRectangle(i, j))) {
                            AudioManager.getInstance().PlayExploding();
                            removeBlockElement(i, j, BlockElement(i, j), GameObject(k).getColor());
                            Model.getInstance().removeGameObject(k);
                            break outerloop;
                        }
                    }
                }
            }
        }
    }

    public void collision(){
        collisionDetection0();
        collisionDetection1();
    }

    public void UpdateScore(int i){

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
            if(!(Model.getInstance().getSpaceShipY() < -(height*0.111)))
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
            if(!(Model.getInstance().getSpaceShipX() < -(width*0.0625)))
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

    public void updateChampion(int index1, int index2, int index3){
        int puntcor = getMapElement("score");
        try {
            if (puntcor > readChampion("champ", 0))
                Savepunteggio(index1,
                        index2, index3,
                        getMapElement("unita"),
                        getMapElement("decine") , getMapElement("centinaia"),
                        getMapElement("migliaia"));

        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public static IControllerForView getInstance() {
        if (instance == null)
            instance = new ControllerForView();
        return instance;
    }
}