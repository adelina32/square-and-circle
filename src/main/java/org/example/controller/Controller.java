package org.example.controller;

import org.example.controller.action.ActionDraw;
import org.example.controller.action.AppAction;
import org.example.controller.factory.MenuState;
import org.example.controller.factory.ShapeCreation;
import org.example.controller.state.UndoMachine;
import org.example.model.Model;
import org.example.model.MyShape;
import org.example.model.fill.NoFill;
import org.example.view.MyFrame;
import org.example.view.MyPanel;
import org.example.view.menu.MenuCreator;

import java.awt.*;
import java.awt.geom.Point2D;

public class Controller {
    private final Model model;
    private static Controller instance;
    private final MenuState menuState;
    private UndoMachine undoMachine;

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
        menuState.setAction(new ActionDraw(model, sampleShape));
        sampleShape.setFb(new NoFill());

        model.setMyShape(sampleShape);
        MyPanel panel = new MyPanel(this);

        model.addObserver(panel);

        MyFrame frame = new MyFrame();
        frame.setPanel(panel);

        MenuCreator menuCreator = MenuCreator.getInstance();
        menuCreator.setMenuState(menuState);
        menuCreator.setModel(model);

        frame.setJMenuBar(menuCreator.createMenuBar());
        frame.add(menuCreator.createToolBar(), BorderLayout.WEST);
    }

    public void getPointOne(Point2D p) {
        AppAction actionDraw1 = menuState.getAction();
        actionDraw1.mousePressed((Point) p);
        undoMachine.add(actionDraw1.cloneAction());
        undoMachine.updateButtons();

    }

    public void getPointTwo(Point2D p){
        AppAction actionDraw1 = menuState.getAction();
        actionDraw1.mouseDragged((Point) p);
    }

    public void draw(Graphics2D g2) {
        model.draw(g2);
    }

}
