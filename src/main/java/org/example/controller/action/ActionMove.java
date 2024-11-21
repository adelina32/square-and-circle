package org.example.controller.action;
import org.example.model.Model;
import org.example.model.MyShape;
import java.awt.*;

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

        if (shape != null) {
            firstPoint = point;
            secondPoint = point;
        }
    }

    public void mouseDragged(Point point) {
        if (shape != null) {
            // Pазница между текущей и начальной точкой
            int deltaX = point.x - firstPoint.x;
            int deltaY = point.y - firstPoint.y;

            // текущие координаты
            double maxX = shape.getShape().getBounds2D().getMaxX();
            double maxY = shape.getShape().getBounds2D().getMaxY();
            double minX = shape.getShape().getBounds2D().getMinX();
            double minY = shape.getShape().getBounds2D().getMinY();

            // новые координаты
            double newMaxX = maxX + deltaX;
            double newMaxY = maxY + deltaY;
            double newMinX = minX + deltaX;
            double newMinY = minY + deltaY;

            shape.getShape().setFrame(newMinX, newMinY, newMaxX - newMinX, newMaxY - newMinY);

            firstPoint = point;
        }
    }
}