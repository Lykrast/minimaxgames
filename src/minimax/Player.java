package minimax;

public interface Player<B extends Board<M>, M> {
    M move(B b, int player);
}
