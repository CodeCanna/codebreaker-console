package edu.cnm.deepdive.codebreaker;

import edu.cnm.deepdive.codebreaker.model.Game;
import edu.cnm.deepdive.codebreaker.service.WebServiceProxy;
import edu.cnm.deepdive.codebreaker.model.Game;
import java.io.IOException;
import retrofit2.Call;
import retrofit2.Response;

public class Application {

  public static void main(String[] args) {
    // TODO Read command-line arguments for pool & length.
    String pool = "ABCDEF"; // FIXME Read from args
    int len = 3; // FIXME Read from args

    Game game = startGame(pool, len);
    System.out.printf("The game id is:  %s%n", game.getId());
    // TODO While code is not guessed:
    //   1. Read guess from user input
    //   2. Submit guess to codebreaker service
    //   3. Display guess results.


  }

  private static Game startGame(String pool, int len) {
    Game gameObject = null;
    try {
      WebServiceProxy wpx = WebServiceProxy.getInstance();
      Game game = new Game();
      game.setPool(pool);
      game.setLength(len);

      Call<Game> call = wpx.startGame(game);
      Response<Game> resp = call.execute();

      // Check if our request was successful
      if (! resp.isSuccessful()) {
        throw new RuntimeException(resp.message());
      }
      gameObject = resp.body(); // Set our game object to be return if request was successful.
    } catch (IOException | RuntimeException e) {
      e.printStackTrace();
    }
    return gameObject;
  }
}
