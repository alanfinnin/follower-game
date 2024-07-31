package game.impl;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import game.controllers.EnemyController;
import game.enums.EntityType;
import game.enums.GameState;
import game.interfaces.Board;
import game.models.Entity;
import game.models.Point;

public class CliBoard implements Board {

    int xLength;
    int yLength;
    
    List<Entity> entites = new ArrayList<>();

    String[][] board;

    @Override
    public void drawBoard(int xLength, int yLength) {
        this.xLength = xLength;
        this.yLength = yLength;

        this.board = new String[this.xLength][this.yLength];
    }

    @Override
    public void createEntity(int id, String name, EntityType type, Point pos) {
        this.entites.add(new Entity(id, name, type, pos));
    }

    @Override
    public void moveEntity(int id, Point pos) {
        for(Entity e: this.entites) {
            if(e.id == id) {
                e.point = pos;
                break;
            }
        }
    }

    @Override
    public void deleteEntity(int id) {
        this.entites.remove(id);
    }

    @Override
    public List<Entity> getEntities() {
        return this.entites;
    }

    @Override
    public GameState checkState() {
        boolean isEntityKilled = false;
        Set<Integer> elementsToDelete = new LinkedHashSet<Integer>();
        for(Entity outerEntity: this.entites) {
            for(Entity innerEntity: this.entites) {
                if(!(outerEntity.id == innerEntity.id)) {
                    if(this.entitiesInSamePosition(outerEntity, innerEntity)) {
                        isEntityKilled = true;
                        elementsToDelete.add(outerEntity.id);
                        elementsToDelete.add(innerEntity.id);
                    }
                }
            }
        }

        for(int eid: elementsToDelete) {
            this.deleteEntity(this.getEntityIndexById(eid));
        }

        if (this.isGameOver()) {
            return GameState.GAME_OVER;
        } else if (isEntityKilled) {
            return GameState.ENTITY_KILLED;
        }

        return GameState.NOTHING;

    }

    @Override
    public String toString() {
        String ret = "";
        for(int i = 0; i < this.entites.size(); i++) {
            ret += i + " -> " + this.entites.get(i).toString() + "\n";
        }

        for(int y = 0; y < this.board[1].length; y++) {
            for(int x = 0; x < this.board[1].length; x++) {  
                if(!this.isEntityOnPosition(x, y)){
                    System.out.print("- ");
                } else {
                    EntityType et = this.whoIsOnPosition(x, y);
                    switch(et) {
                        case PLAYER:
                            System.out.print("1 ");
                            break;
                        case NPC:
                            System.out.print("0 ");
                            break;
                    }
                }
            }
            System.out.println();
        }
        System.out.println("\n");

        return ret;
    }

    @Override
    public Entity getPlayer() {
        for(Entity e: this.entites) {
            if(e.type == EntityType.PLAYER) {
                return e;
            }
        }
        return null;
    }

    private boolean entitiesInSamePosition(Entity e1, Entity e2) {
        return ((e1.point.x == e2.point.x) && (e1.point.y == e2.point.y));
    }

    private boolean isGameOver(){
        if (this.getPlayer() == null) return true;

        Entity player = this.getPlayer();
        for(Entity entity: this.entites) {
            if(entity.type != EntityType.PLAYER){
                System.out.print(EnemyController.getBestNextPoint(entity, player) + " -> ");
                System.out.print(player.point);
                if(EnemyController.getBestNextPoint(entity, player).equals(player.point)){
                    return true;
                }
            }
        }
        return false;
    }

    private int getEntityIndexById(int eid) {
        for(int i = 0; i < this.entites.size(); i++) {
            if (eid == this.entites.get(i).id) {
                return i;
            }
        }
        return -1;
    }

    private EntityType whoIsOnPosition(int x, int y) {
        for(Entity e: this.entites) {
            if((e.point.x == x) && (e.point.y == y)) {
                return e.type;
            }
        }
        return null;
    }

    // TODO: these sorts of functions should be in the super. Not here, this is just for gui work
    private boolean isEntityOnPosition(int x, int y) {
        for(Entity e: this.entites) {
            if((e.point.x == x) && (e.point.y == y)) {
                return true;
            }
        }
        return false;
    }
}
