package org.example.controller.action;
import org.example.model.Model;
import org.example.model.MyShape;
import java.awt.*;
import java.awt.geom.Point2D;

public class ActionMove implements AppAction {
    private final Model model;
    private MyShape shape;
    private Point firstPoint;

    public ActionMove(Model model) {
        this.model = model;
    }

    public void mousePressed(Point point) {
        firstPoint = point;
        shape = model.getShapeList()
                .stream()
                .filter(myShape -> myShape.getShape().contains(point))
                .findFirst()
                .orElse(null);
        model.update();
    }

    public void mouseDragged(Point point) {
        if (shape == null){
            return;
        }

        double deltaX = point.getX() - firstPoint.getX();
        double deltaY = point.getY() - firstPoint.getY();

        Point2D newShapeFirstPoint = new Point2D.Double();
        newShapeFirstPoint.setLocation(shape.getShape().getMaxX() + deltaX,
                shape.getShape().getMaxY() + deltaY);

        Point2D newShapeSecondPoint = new Point2D.Double();
        newShapeSecondPoint.setLocation(shape.getShape().getMinX() + deltaX,
                shape.getShape().getMinY() + deltaY);

        shape.getShape().setFrameFromDiagonal(newShapeFirstPoint, newShapeSecondPoint);
        firstPoint = point;
        model.update();
    }

    @Override
    public void execute() {}

    @Override
    public void unexecute() {}

    @Override
    public AppAction cloneAction() {
        return null;
    }
}
