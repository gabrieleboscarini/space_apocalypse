package model;

import java.awt.*;

import static java.lang.Math.sqrt;

public class GameObject{


    private double x;
    private double y;
    private double deltaX;
    private double deltaY;
    private double scaleSX;
    private double scaleDX;
    private double dX;
    private double dY;
    private final String type;


    public GameObject(double currentX, double currentY, double destinationX, double destinationY, String type){

        this.x = currentX;
        this.y = currentY;
        this.dX = destinationX;
        this.dY = destinationY;
        this.deltaX = dX - x;
        this.deltaY = dY - y;
        double mag = sqrt( deltaX*deltaX + deltaY*deltaY );
        deltaX /= mag;
        deltaY/= mag;
        this.type = type;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setDestinationX(double x){
        this.dX = x;
    }

    public void setDestinationY(double y){
        this.dY = y;
    }

    public double getDeltaX(){
        return deltaX;
    }

    public double getDeltaY(){
        return deltaY;
    }

    public double getScaleSX(){
        return scaleSX;
    }

    public void setScaleSX(double sx){
        this.scaleSX = sx;
    }

    public double getScaleDX(){
        return scaleDX;
    }

    public void setScaleDX(double dx){
        this.scaleDX = dx;
    }

    public String getType(){
        return type;
    }

    public Rectangle ObjectRectangle(int x, int y, int width, int weight){
        return new Rectangle(x, y, width, weight);
    }

}