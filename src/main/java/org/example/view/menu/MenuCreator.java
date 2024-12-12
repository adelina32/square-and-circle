package org.example.view.menu;

import lombok.Getter;
import lombok.Setter;
import org.example.controller.action.ActionDraw;
import org.example.controller.action.ActionMove;
import org.example.controller.action.AppAction;
import org.example.controller.factory.MenuState;
import org.example.controller.factory.ShapeType;
import org.example.controller.state.UndoMachine;
import org.example.model.Model;
import org.example.model.MyShape;

import javax.swing.*;
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
    private UndoMachine undoMachine;

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

        URL colorUrl = getClass().getClassLoader().getResource("ico/color_16x16.png");
        ImageIcon colorIco = colorUrl == null ? null : new ImageIcon(colorUrl);
        AppCommand colorCommand = new SwitchColor(menuState, false, null);
        menuItems.add(new CommandActionListener("Цвет", colorIco, colorCommand));


        URL drawUrl = getClass().getClassLoader().getResource("ico/draw_16x16.png");
        ImageIcon drawIco = drawUrl == null ? null : new ImageIcon(drawUrl);
        AppCommand drawCommand = new SwitchAction(new ActionDraw(model, shape), menuState);
        menuItems.add(new CommandActionListener("Рисовать", drawIco, drawCommand));

        URL moveUrl = getClass().getClassLoader().getResource("ico/move_16x16.png");
        ImageIcon moveIco = moveUrl == null ? null : new ImageIcon(moveUrl);
        AppCommand moveCommand = new SwitchAction(new ActionMove(model), menuState);
        menuItems.add(new CommandActionListener("Двигать", moveIco, moveCommand));


        URL ellipseUrl = getClass().getClassLoader().getResource("ico/ellipse_16x16.png");
        ImageIcon ellipseIco = ellipseUrl == null ? null : new ImageIcon(ellipseUrl);
        AppCommand ellipseCommand = new SwitchShape(menuState, ShapeType.ELLIPSE);
        menuItems.add(new CommandActionListener("Эллипс", ellipseIco, ellipseCommand));

        URL rectangularUrl = getClass().getClassLoader().getResource("ico/rectangular_16x16.png");
        ImageIcon rectangularIco = rectangularUrl == null ? null : new ImageIcon(rectangularUrl);
        AppCommand rectangularCommand = new SwitchShape(menuState, ShapeType.RECTANGULAR);
        menuItems.add(new CommandActionListener("Прямоугольник", rectangularIco, rectangularCommand));


        URL fillUrl = getClass().getClassLoader().getResource("ico/fill_16x16.png");
        ImageIcon fillIco = fillUrl == null ? null : new ImageIcon(fillUrl);
        AppCommand fillCommand = new SwitchFill(menuState, true);
        menuItems.add(new CommandActionListener("Заливать", fillIco, fillCommand));

        URL no_fillUrl = getClass().getClassLoader().getResource("ico/no_fill_16x16.png");
        ImageIcon no_fillIco = no_fillUrl == null ? null : new ImageIcon(no_fillUrl);
        AppCommand no_fillCommand = new SwitchFill(menuState, false);
        menuItems.add(new CommandActionListener("Не заливать", no_fillIco, no_fillCommand));


        URL undoUrl = getClass().getClassLoader().getResource("ico/undo_16x16.png");
        ImageIcon undoIco = undoUrl == null ? null : new ImageIcon(undoUrl);
        AppCommand undoCommand = new SwitchUndo(undoMachine);
        CommandActionListener undoListener = new CommandActionListener("Вперед-назад", undoIco, undoCommand);
        menuItems.add(undoListener);

        URL redoUrl = getClass().getClassLoader().getResource("ico/redo_16x16.png");
        ImageIcon redoIco = redoUrl == null ? null : new ImageIcon(redoUrl);
        AppCommand redoCommand = new SwitchRedo(undoMachine);
        CommandActionListener redoListener = new CommandActionListener("Вперед-назад", redoIco, redoCommand);
        menuItems.add(redoListener);


        undoMachine.setUndo(undoListener);
        undoListener.setEnabled(false);
        undoMachine.setRedo(redoListener);
        redoListener.setEnabled(false);

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
        JMenuItem rgbItem = new JMenuItem("RGB");
        rgbItem.addActionListener(new CommandActionListener(new SwitchColor(menuState, false, null)));
        colorMenu.add(rgbItem);

        return colorMenu;
    }

    public void setState(MenuState menuState) {
    }
}