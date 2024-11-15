package org.example.controller.factory;

import org.example.model.MyShape;
import org.example.model.fill.Fill;
import org.example.model.fill.FillBehavior;
import org.example.model.fill.NoFill;

import java.awt.*;

public class ShapeCreation {
    private static ShapeCreation instance;
    private MenuState state;
    public static synchronized ShapeCreation getInstance(){
        if (instance == null){
            instance = new ShapeCreation();
        }
        return instance;
    }
    private ShapeCreation(){}
    public void config(MenuState state){
        this.state = state;
    }

    public MyShape createShape(){
        MyShape newShape = new MyShape();
        RestangularShape shape = state.getShapeType().createShape();

        FillBehavior fillBehavior = state.isFill ? new Fill() : new NoFill();
        fillBehavior.setColor(state.getColor());

        newShape.setFb(fillBehavior);
        return newShape;
    }
}
