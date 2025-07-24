package ui.settings;

import java.awt.Dimension;
import java.awt.Point;

public class Settings {
    private static int scale = 1;

    public static void setScale(int scale) {
        if (scale > 0) {
            Settings.scale = scale;
        } else {
            throw new IllegalArgumentException("Scale must be a positive integer.");
        }
    }

    public static int getScale(){
        return scale;
    }

    public static Point translateLogicPoint(Point logicPoint) {
        if (logicPoint == null) {
            throw new IllegalArgumentException("Logic point cannot be null.");
        }
        return new Point(logicPoint.x * scale, logicPoint.y * scale);

    }

    public static Point translateGraphicPoint(Point graphicPoint) {
        if (graphicPoint == null) {
            throw new IllegalArgumentException("Graphic point cannot be null.");
        }
        return new Point(graphicPoint.x / scale, graphicPoint.y / scale);
    }

    public static Dimension scaleDimention(Dimension dimension) {
        if (dimension == null) {
            throw new IllegalArgumentException("Dimension cannot be null.");
        }
        return new Dimension((int)dimension.getWidth() * scale, (int)dimension.getHeight() * scale);
    }
}
