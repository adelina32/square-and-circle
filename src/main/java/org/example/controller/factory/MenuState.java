package org.example.controller.factory;

import org.example.controller.action.ActionDraw;
import org.example.controller.action.AppAction;

import java.awt.*;
public class MenuState {
    private boolean fill;
    private Color color;
    private ShapeType shapeType;
    private AppAction actionDraw;
    public MenuState(){
        shapeType = ShapeType.RECTANGULAR;
        color = Color.BLUE;
        fill = false;
    }

    public boolean isFill() {
        return fill;
    }

    public void setFill(boolean fill) {
        this.fill = fill;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public ShapeType getShapeType() {
        return shapeType;
    }

    public void setShapeType(ShapeType shapeType) {
        this.shapeType = shapeType;
    }

    public AppAction getActionDraw() {
        return actionDraw;
    }

    public void setActionDraw(AppAction actionDraw) {
        this.actionDraw = actionDraw;
    }
}
