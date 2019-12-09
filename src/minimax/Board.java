package minimax;

import java.util.List;

public interface Board<M> {
    GameState getGameState();
    List<M> generateMoves(int player);
    Board<M> copy();
    Board<M> move(M move);
    
}
