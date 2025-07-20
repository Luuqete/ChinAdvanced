package entity;

import java.awt.Point;

public class Entity {
    private Point location;

    public Entity() {
        location = new Point(0, 0);
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }
}
