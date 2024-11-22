package org.example.controller;

import lombok.Getter;
import lombok.Setter;
import org.example.controller.action.ActionDraw;
import org.example.controller.action.ActionMove;
import org.example.controller.action.AppAction;
import org.example.controller.factory.MenuState;
import org.example.controller.factory.ShapeType;
import org.example.model.Model;
import org.example.model.MyShape;

import javax.swing.*;
import java.awt.*;

@Getter
@Setter
public class MenuController {
    private static MenuController instance;
    private JMenuBar menuBar;
    private AppAction action;
    private MenuState menuState;
    private Model model;
    private MyShape shape;

    private MenuController(){
        menuBar = createMenuBar();
    }

    public static MenuController getInstance(){
        if (instance == null){
            instance = new MenuController();
        }
        return instance;
    }
    public JMenuBar createMenuBar(){
        JMenuBar menuBar = new JMenuBar();

        JMenu shapeMenu = createShapeMenu();
        menuBar.add(shapeMenu);

        JMenu colorMenu = createColorMenu();
        menuBar.add(colorMenu);

        JMenu actionMenu = createActionMenu();
        menuBar.add(actionMenu);

        return menuBar;
    }

    private JMenu createActionMenu() {
        JMenu shapeMenu = new JMenu("Действие");
        ButtonGroup group = new ButtonGroup();

        JRadioButtonMenuItem square = new JRadioButtonMenuItem("Рисовать");
        square.addActionListener(e -> menuState.setAction(new ActionDraw(model, shape)));
        shapeMenu.add(square);
        group.add(square);

        JRadioButtonMenuItem ellipse = new JRadioButtonMenuItem("Двигать");
        ellipse.addActionListener(e -> menuState.setAction(new ActionMove(model)));
        shapeMenu.add(ellipse);
        group.add(ellipse);
        return shapeMenu;
    }


    private JMenu createShapeMenu() {

        JMenu shapeMenu = new JMenu("Фигура");
        ButtonGroup group = new ButtonGroup();

        JRadioButtonMenuItem square = new JRadioButtonMenuItem("Прямоугольник");
        square.addActionListener(e -> menuState.setShapeType(ShapeType.RECTANGULAR));
        shapeMenu.add(square);
        group.add(square);

        JRadioButtonMenuItem ellipse = new JRadioButtonMenuItem("Эллипс");
        ellipse.addActionListener(e -> menuState.setShapeType(ShapeType.ELLIPSE));
        shapeMenu.add(ellipse);
        group.add(ellipse);
        return shapeMenu;
    }

    private JMenu createColorMenu() {
        JMenu colorMenu = new JMenu("Цвет");

        JMenuItem redItem = new JMenuItem("Красный");
        redItem.addActionListener(e -> menuState.setColor(Color.RED));
        colorMenu.add(redItem);

        JMenuItem greenItem = new JMenuItem("Зеленый");
        greenItem.addActionListener(e -> menuState.setColor(Color.GREEN));
        colorMenu.add(greenItem);

        JMenuItem blueItem = new JMenuItem("Синий");
        blueItem.addActionListener(e -> menuState.setColor(Color.BLUE));
        colorMenu.add(blueItem);

        return colorMenu;
    }
}

