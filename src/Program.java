import factories.chess.BoardFactory;
import game.core.area.src.Interfaces.IArea;

public class Program {
    public static void main(String[] args) {
        BoardFactory factory = new BoardFactory();
        IArea board = factory.getTestBoard();
        var test = board.moveObject(25, 34);
    }
}
