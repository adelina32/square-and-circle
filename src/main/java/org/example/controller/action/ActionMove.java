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


}