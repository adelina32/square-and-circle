package org.example.model;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class Model extends Observable {
    private MyShape currentShape;
    private List<MyShape> shapeList = new ArrayList<>();

    public void  createCurrentShape(MyShape shape){
        currentShape = shape;
        shapeList.add(shape);
    }

    public void setMyShape(MyShape myShape) {
        this.currentShape = myShape;
    }

    public void changeShape(Point2D x, Point2D y) {
        currentShape.setFrame(x, y);
        this.setChanged();
        this.notifyObservers();
    }

    public void draw(Graphics2D g) {
        for (MyShape shape : shapeList){
            shape.draw(g);
        }
        currentShape.draw(g);
    }
    public void update()
    {
        this.setChanged();
        this.notifyObservers();
    }
}
