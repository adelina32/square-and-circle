package org.example.view.menu;

import org.example.controller.factory.MenuState;

public class SwitchFill implements AppCommand{
    private boolean fill;
    private MenuState menuState;
    public SwitchFill(MenuState menuState, boolean fill){
        this.menuState = menuState;
        this.fill = fill;
    }

    @Override
    public void execute() {menuState.setFill(fill);}
}
