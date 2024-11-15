package org.example.controller.factory;

import lombok.Getter;
import lombok.Setter;
import org.example.controller.ActionDraw;

import java.awt.*;
@Setter
@Getter

public class MenuState {
    private boolean fill;
    private Color color;
    private ShapeType shapeType;
    private ActionDraw actionDraw;
    public MenuState(){
        shapeType = ShapeType.RECTANGULAR;
        color = Color.BLUE;
        fill = false;
    }

}
