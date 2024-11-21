package org.example.controller.action;

import org.example.controller.factory.ShapeCreation;
import org.example.model.Model;
import org.example.model.MyShape;

import java.awt.*;
import java.awt.geom.Point2D;

public class ActionDraw implements AppAction {
    private Model model;
    private MyShape shape;
    private ShapeCreation shapeCreation;
    private Point2D firstPoint;
    private Point2D secondPoint;

    public MyShape getShape() {
        return shape;
    }

    public ActionDraw(Model model, MyShape shape) {
        this.model = model;
        this.shape = shape;
        //shapeCreation = ShapeCreation.getInstance();
    }
    public  void  stretchShape (Point point){
        firstPoint = point;
        shape.setFrame(firstPoint, secondPoint);
        model.update();

    }

    public void createShape (Point point){
        secondPoint = point;
        shape = shape.clone();
        //shape = ShapeCreation.getInstance().createShape();
        model.createCurrentShape(shape);
        model.update();

    }
    @Override
    public void mouseDragged(Point point){
        firstPoint = point;
        shape.setFrame(firstPoint, firstPoint);
        model.update();
    }

    @Override
    public void mousePressed(Point point){
        secondPoint = point;
        ShapeCreation shapeCreation = ShapeCreation.getInstance();; ///что с этим делать
        shape = shapeCreation.createShape();                        ///и с этим
        model.addCurrentShape(shape);
        model.update();;
    }
}
