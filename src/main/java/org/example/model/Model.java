package org.example.model;

import lombok.Getter;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

@Getter
public class Model extends Observable { //

    private final List<MyShape> shapeList = new ArrayList<>(); //добавляемые и удаляемые



    public void addCurrentShape(MyShape myShape){
        shapeList.add(myShape);
    }

    public void draw(Graphics2D g) {
        for (MyShape shape : shapeList){
            shape.draw(g);
        }
    }

    public void removeLastShape() {
        int size = shapeList.size();
        shapeList.remove(size - 1);
    }

    public MyShape getLastShape() {
        int size = shapeList.size();
        return shapeList.isEmpty() ? null : shapeList.get(size - 1);
    }

    public void setMyShape(MyShape sampleShape) {
    }

    public void update()
    {
        this.setChanged();
        this.notifyObservers(); //увед
    }
}
