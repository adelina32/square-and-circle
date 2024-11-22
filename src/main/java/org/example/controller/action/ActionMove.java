package org.example.controller.action;
import org.example.model.Model;
import org.example.model.MyShape;
import java.awt.*;
import java.awt.geom.Point2D;

public class ActionMove implements AppAction {
    private Model model;
    private MyShape shape;
    private Point firstPoint;
    private Point secondPoint;

    public ActionMove(Model model) {
        this.model = model;
    }

    public void mousePressed(Point point) {
        shape = model.getShapeList()
                .stream()
                .filter(myShape -> myShape.getShape().contains(point))
                .findFirst()
                .orElse(null);
    }

    public void mouseDragged(Point point) {

        if (shape == null){
            return;
        }

        double deltaX = secondPoint.getX() - firstPoint.getX();
        double deltaY = secondPoint.getY() - firstPoint.getY();

        Point2D newShapeFirstPoint = new Point2D.Double();
        newShapeFirstPoint.setLocation(shape.getShape().getMaxX() + deltaX,
                shape.getShape().getMaxY() + deltaY);

        Point2D newShapeSecondPoint = new Point2D.Double();
        newShapeSecondPoint.setLocation(shape.getShape().getMinX() + deltaX,
                shape.getShape().getMinY() + deltaY);

        shape.getShape().setFrameFromDiagonal(newShapeFirstPoint, newShapeSecondPoint);
        firstPoint = secondPoint;
        model.update();
    }
}
