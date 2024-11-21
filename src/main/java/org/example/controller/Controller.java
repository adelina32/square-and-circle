package org.example.controller;

import org.example.controller.action.ActionDraw;
import org.example.controller.action.AppAction;
import org.example.controller.factory.MenuState;
import org.example.controller.factory.ShapeCreation;
import org.example.model.Model;
import org.example.model.MyShape;
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
    private AppAction actionDraw;
    private MenuState menuState;

    public static synchronized Controller getInstance(){
        if (instance == null){
            instance = new Controller();
        }
        return instance;
    }


    private Controller() {
        menuState = new MenuState();
        ShapeCreation shapeCreation = ShapeCreation.getInstance();
        shapeCreation.config(menuState);

        model = new Model();

        MyShape sampleShape = new MyShape(new Rectangle2D.Double());
        sampleShape.setFb(new NoFill());
        actionDraw = new ActionDraw(model, sampleShape) {
            @Override
            public void mousePressed(Point point) {

            }

            @Override
            public void mouseDragged(Point point) {

            }
        };

        model.setMyShape(sampleShape);
        panel = new MyPanel(this, actionDraw);

        model.addObserver(panel);

        frame = new MyFrame();
        frame.setPanel(panel);

        MenuController menuController = MenuController.getInstance();
        menuController.setAppaction(actionDraw);
        menuController.setState(menuState);
        frame.setJMenuBar(menuController.createMenuBar());
        frame.revalidate();

    }


    public void getPointOne(Point2D p) {
        AppAction actionDraw1 = menuState.getActionDraw();
        //actionDraw.mousePressed((Point) p);
    }

    public void getPointTwo(Point2D p){
        AppAction actionDraw1 = menuState.getActionDraw();
        //actionDraw.mouseDragged((Point) p);
    }

    public void draw(Graphics2D g2) {
        model.draw(g2);
    }

}
