package org.example.controller.action;

import org.example.controller.factory.ShapeCreation;
import org.example.model.Model;
import org.example.model.MyShape;

import java.awt.*;
import java.awt.geom.Point2D;

public class ActionDraw implements AppAction {
    private final Model model;
    private MyShape shape;
    private final ShapeCreation shapeCreation;
    private Point2D firstPoint;
    private MyShape drawableShape; //отображается

    public ActionDraw(Model model, MyShape shape) {
        this.model = model;
        this.shape = shape;
        shapeCreation = ShapeCreation.getInstance(); //ф
    }

    @Override
    public void mousePressed(Point point){
        firstPoint = point;
        shape = shapeCreation.createShape();
        drawableShape = shape;
        model.addCurrentShape(shape);
        model.update();
    }

    @Override
    public void mouseDragged(Point point){
        shape.setFrame(firstPoint, point);
        drawableShape.setFrame(firstPoint, point);
        model.update();
    }

    @Override
    public void execute() {
        model.addCurrentShape(drawableShape);
        model.update();
    }

    @Override
    public void unexecute() {
        drawableShape = model.getLastShape();
        model.removeLastShape(); //удаляет последнюю
        model.update();
    }

    @Override
    public AppAction cloneAction() {
        ActionDraw actionDraw = new ActionDraw(model, shape);
        actionDraw.shape = shape.clone();
        actionDraw.drawableShape = drawableShape;
        return actionDraw;
    }
}
