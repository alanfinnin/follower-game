package game.interfaces;

import java.util.List;

import game.enums.EntityType;
import game.enums.GameState;
import game.models.Entity;
import game.models.Point;

public interface Board {
    public void drawBoard(int xLength, int yLength);
    public void createEntity(int id, String name, EntityType isPlayer, Point pos);
    public void moveEntity(int id, Point pos);
    public void deleteEntity(int id);
    public List<Entity> getEntities();
    public GameState checkState();
    public Entity getPlayer();
    public String toString();
    
}
