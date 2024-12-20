package org.example.view.menu;

import org.example.controller.factory.MenuState;
import org.example.controller.factory.ShapeType;

public class SwitchShape implements AppCommand{
    private final MenuState state;
    private final ShapeType shapeType;
    public SwitchShape(MenuState state, ShapeType shapeType){
        this.state = state;
        this.shapeType = shapeType;
    }

    @Override
    public void execute() { state.setShapeType(shapeType);}
}
