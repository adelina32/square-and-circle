package org.example.controller.factory;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;

public enum ShapeType {
    RECTANGULAR {
        @Override
        public RectangularShape createShape()  {
            return new Rectangle2D.Double();
        }
    },


    ELLIPSE {
        @Override
        public RectangularShape createShape() {
            return new Ellipse2D.Double();
        }
    };

    public abstract RectangularShape createShape();
}
