package game.impl;

import java.util.List;

import javax.swing.JFrame;

import game.enums.EntityType;
import game.enums.GameState;
import game.interfaces.Board;
import game.models.Entity;
import game.models.Point;

public class GuiBoard implements Board{
    @Override
    public void drawBoard(int xLength, int yLength) {
        JFrame frame = new JFrame();

        String[][] content = new String[10][10];
        for(int x = 0; x < 10; x++) {
            for(int y = 0; y < 10; y++) {
                content[x][y] = "o";
            }
        }

        frame.setSize(400,500);//400 width and 500 height  
        frame.setLayout(null);//using no layout managers  
        frame.setVisible(true);
    }

    @Override
    public void createEntity(int id, String name, EntityType isPlayer, Point pos) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createEntity'");
    }

    @Override
    public void moveEntity(int id, Point pos) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'moveEntity'");
    }

    @Override
    public void deleteEntity(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteEntity'");
    }

    @Override
    public List<Entity> getEntities() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getEntities'");
    }

    @Override
    public GameState checkState() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'checkState'");
    }

    @Override
    public Entity getPlayer() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPlayer'");
    }
}