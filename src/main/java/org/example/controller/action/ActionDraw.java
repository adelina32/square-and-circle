package org.example.controller.action;

import org.example.model.Model;
import org.example.model.MyShape;

import java.awt.*;
import java.awt.geom.Point2D;

public class ActionDraw implements AppAction {
    private Model model;
    private MyShape shape;
    private Point2D firstPoint;
    private Point2D secondPoint;

    public MyShape getShape() {
        return shape;
    }

    public ActionDraw(Model model, MyShape shape) {
        this.model = model;
        this.shape = shape;
    }
    public  void  stretchShape (Point point){
        firstPoint =  point;
        shape.setFrame(firstPoint, secondPoint);
        model.update();

    }

    public void createShape (Point point){
        secondPoint =  point;
        shape = shape.clone();
        model.createCurrentShape(shape);
        model.update();

    }
    @Override
    public void  mouseDragged(Point point){
        firstPoint = point;

    }
    @Override
    public void mousePressed(Point point){
        secondPoint = point;

    }

}
