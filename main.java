package game;
import game.controllers.EnemyController;
import game.controllers.NpcController;
import game.enums.EntityType;
import game.enums.GameState;
import game.impl.CliBoard;
import game.interfaces.Board;
import game.models.Entity;
import game.models.Point;
public class main {

    private static boolean gameLoop = true;
    public static void main(String[] args) throws InterruptedException {
        Board board = new CliBoard();
        board.drawBoard(10, 10);
        board.createEntity(0, "Player", EntityType.PLAYER, new Point(5, 5));
        board.createEntity(1, "NonPlayer", EntityType.NPC, new Point(9, 9));

        GameState state;
        Entity player = board.getPlayer();
        while(gameLoop) {        
            Thread.sleep(1000);
        
            System.out.println(board.toString());
            state = board.checkState();

            for(Entity e: board.getEntities()) {
                if(e.type != EntityType.PLAYER) {
                    board.moveEntity(e.id, EnemyController.getBestNextPoint(e, player));
                    // TODO, change this so it can handle multiple enemies                    
                    board.moveEntity(player.id, NpcController.getBestNextPoint(player, e));                
                }
            }

            switch(state) {
                case GAME_OVER:
                    System.out.println("Game ending");
                    gameLoop = false;
                    break;
                case ENTITY_KILLED:
                    System.out.println("Entity killed");
                    break;
                case NOTHING:
                    break;
            }
        }
    }
}