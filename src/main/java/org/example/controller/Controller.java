package org.example.controller;

import org.example.model.Model;
import org.example.model.MyShape;
import org.example.model.fill.Fill;
import org.example.model.fill.NoFill;
import org.example.view.MyFrame;
import org.example.view.MyPanel;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class Controller {
    private Model model;
    private MyFrame frame;
    private MyPanel panel;
    private Point2D firstPoint;
    private Point2D secondPoint;
    private static Controller instance;
    private ActionDraw actionDraw;

    public static synchronized Controller getInstance(){
        if (instance == null){
            instance = new Controller();
        }
        return instance;
    }


    private Controller() {
        model = new Model();
        MyShape sampleShape = new MyShape(new Rectangle2D.Double());
        sampleShape.setFb(new NoFill());
        actionDraw = new ActionDraw(model, sampleShape);
        model.setMyShape(sampleShape);
        panel = new MyPanel(this, actionDraw);

        model.addObserver(panel);

        frame = new MyFrame();
        frame.setPanel(panel);

        MenuController menuController = MenuController.getInstance();
        menuController.setActionDraw(actionDraw);
        frame.setJMenuBar(menuController.createMenuBar());

    }


    public void getPointOne(Point2D p) {
        actionDraw.createShape((Point) p);
    }

    public void getPointTwo(Point2D p){
        actionDraw.stretchShape((Point) p);
    }

    public void draw(Graphics2D g2) {
        model.draw(g2);
    }

}
