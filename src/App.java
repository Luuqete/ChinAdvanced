import game.Game;

public class App {
    public static void main(String[] args) throws Exception {
        Game game = new Game();
        game.addEmptyPlayer();
        game.startGame();
    }
}
