package org.example.view.menu;

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
import java.net.URL;
import java.util.ArrayList;

@Getter
@Setter
public class MenuCreator {
    private static MenuCreator instance;
    private JMenuBar menuBar;
    private AppAction action;
    private MenuState menuState;
    private Model model;
    private MyShape shape;

    private MenuCreator(){
        menuBar = createMenuBar();
    }

    public static MenuCreator getInstance(){
        if (instance == null){
            instance = new MenuCreator();
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

    public JToolBar createToolBar(){
        ArrayList<Action> subMenuItems = createToolBarItems();
        JToolBar jToolBar = new JToolBar(JToolBar.VERTICAL);
        subMenuItems.forEach(jToolBar::add);
        return jToolBar;
    }

    private ArrayList<Action> createToolBarItems() {
        ArrayList<Action> menuItems = new ArrayList<>();

        URL colorUrl = getClass().getClassLoader().getResource("ico/color.png");
        ImageIcon colorIco = colorUrl == null ? null : new ImageIcon(colorUrl);
        AppCommand colorCommand = new SwitchColor(menuState, false, null);
        menuItems.add(new CommandActionListener("Цвет", colorIco, colorCommand));


        URL drawUrl = getClass().getClassLoader().getResource("ico/draw.png");
        ImageIcon drawIco = drawUrl == null ? null : new ImageIcon(drawUrl);
        AppCommand drawCommand = new SwitchAction(new ActionDraw(model, shape), menuState);
        menuItems.add(new CommandActionListener("Рисовать", drawIco, drawCommand));

        URL moveUrl = getClass().getClassLoader().getResource("ico/move.png");
        ImageIcon moveIco = moveUrl == null ? null : new ImageIcon(moveUrl);
        AppCommand moveCommand = new SwitchAction(new ActionMove(model), menuState);
        menuItems.add(new CommandActionListener("Двигать", moveIco, moveCommand));


//        URL redoUrl = getClass().getClassLoader().getResource("ico/redo.png");
//        ImageIcon redoIco = redoUrl == null ? null : new ImageIcon(redoUrl);
//        AppCommand redoCommand = new SwitchAction();
//        menuItems.add(new CommandActionListener("Вперёд", redoIco, redoCommand));
//
//        URL undoUrl = getClass().getClassLoader().getResource("ico/draw.png");
//        ImageIcon undoIco = undoUrl == null ? null : new ImageIcon(undoUrl);
//        AppCommand undoCommand = new SwitchAction(menuState, false, null);
//        menuItems.add(new CommandActionListener("Назад", undoIco, undoCommand));


        URL ellipseUrl = getClass().getClassLoader().getResource("ico/ellipse.png");
        ImageIcon ellipseIco = ellipseUrl == null ? null : new ImageIcon(ellipseUrl);
        AppCommand ellipseCommand = new SwitchShape(menuState, ShapeType.ELLIPSE);
        menuItems.add(new CommandActionListener("Эллипс", ellipseIco, ellipseCommand));

        URL rectangularUrl = getClass().getClassLoader().getResource("ico/rectangular.png");
        ImageIcon rectangularIco = rectangularUrl == null ? null : new ImageIcon(rectangularUrl);
        AppCommand rectangularCommand = new SwitchShape(menuState, ShapeType.RECTANGULAR);
        menuItems.add(new CommandActionListener("Прямоугольник", rectangularIco, rectangularCommand));



        URL fillUrl = getClass().getClassLoader().getResource("ico/fill.png");
        ImageIcon fillIco = fillUrl == null ? null : new ImageIcon(fillUrl);
        AppCommand fillCommand = new SwitchFill(menuState, true);
        menuItems.add(new CommandActionListener("Заливать", fillIco, fillCommand));

        URL no_fillUrl = getClass().getClassLoader().getResource("ico/no_fill.png");
        ImageIcon no_fillIco = no_fillUrl == null ? null : new ImageIcon(no_fillUrl);
        AppCommand no_fillCommand = new SwitchFill(menuState, false);
        menuItems.add(new CommandActionListener("Не заливать", no_fillIco, no_fillCommand));

        return menuItems;
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

        JMenuItem rgbItem = new JMenuItem("RGB");
        rgbItem.addActionListener(new CommandActionListener(new SwitchColor(menuState, false, null)));
        colorMenu.add(rgbItem);

        return colorMenu;
    }
}

