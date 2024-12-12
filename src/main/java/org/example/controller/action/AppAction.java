package org.example.controller.action;

import java.awt.*;

public interface AppAction {
    void mousePressed(Point point);
    void mouseDragged(Point point);
    void execute(); //выполнение
    void unexecute(); //отмена
    AppAction cloneAction();
}
