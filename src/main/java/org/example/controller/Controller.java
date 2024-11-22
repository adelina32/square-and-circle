package org.example.controller;

import org.example.controller.action.ActionDraw;
import org.example.controller.action.ActionMove;
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
    private final Model model;
    private static Controller instance;
    private final MenuState menuState;

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

        MyShape sampleShape = shapeCreation.createShape();
        menuState.setActionDraw(new ActionDraw(model, sampleShape));
        sampleShape.setFb(new NoFill());

        model.setMyShape(sampleShape);
        MyPanel panel = new MyPanel(this);

        model.addObserver(panel);

        MyFrame frame = new MyFrame();
        frame.setPanel(panel);

        MenuController menuController = MenuController.getInstance();
        menuController.setState(menuState);
        frame.setJMenuBar(menuController.createMenuBar());
        frame.revalidate();

    }


    public void getPointOne(Point2D p) {
        AppAction actionDraw1 = menuState.getActionDraw();
        actionDraw1.mousePressed((Point) p);
    }

    public void getPointTwo(Point2D p){
        AppAction actionDraw1 = menuState.getActionDraw();
        actionDraw1.mouseDragged((Point) p);
    }

    public void draw(Graphics2D g2) {
        model.draw(g2);
    }

}
