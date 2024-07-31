package game.models;

import game.enums.EntityType;

public class Entity {
    public final int id;
    public final String name;
    public final EntityType type;
    public Point point;

    public Entity(int id, String name, EntityType type, Point point) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.point = point;
    }

    public void move(int x, int y) {
        this.point.x = x;
        this.point.y = y;
    }

    @Override
    public String toString() {
        // TODO should be a string format
        return this.id + ":" + this.name + " x:" + this.point.x + " y:" + this.point.y;
    }
}
