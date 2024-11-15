package org.example.controller;

import lombok.Getter;
import lombok.Setter;
import org.example.controller.action.AppAction;
import org.example.controller.factory.MenuState;
import org.example.controller.factory.ShapeType;

import javax.swing.*;

@Getter
@Setter
public class MenuController {
    private static MenuController instance;
    private JMenuBar menuBar;
    private AppAction actionDraw;
    private MenuState state;
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

        //JMenu colorMenu = new JMenu();


        return menuBar;
    }


    private JMenu createShapeMenu() {

        JMenu shapeMenu = new JMenu("Фигура");
        ButtonGroup group = new ButtonGroup();

        JRadioButtonMenuItem square = new JRadioButtonMenuItem("Прямоугольник");
        square.addActionListener(e -> state.setShapeType(ShapeType.RECTANGULAR));
        shapeMenu.add(square);
        group.add(square);

        JRadioButtonMenuItem ellipse = new JRadioButtonMenuItem("Эллипс");
        ellipse.addActionListener(e -> state.setShapeType(ShapeType.ELLIPSE));

        shapeMenu.add(ellipse);
        group.add(ellipse);
        return shapeMenu;


       // JMenu colorMenu = new JMenuBar("Цвета");


    }
}
