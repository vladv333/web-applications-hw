package com.example.guessgame;

import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
public class GameController {

    // store all games: key = gameId, value = Game object
    private Map<String, Game> games = new HashMap<>();

    // endpoint 1: new game
    @GetMapping("/game")
    public String startGame() {
        String gameId = UUID.randomUUID().toString();
        games.put(gameId, new Game());
        return "Game ID: " + gameId;
    }

    // endpoint 2: guess
    @GetMapping("/game/{gameId}/guess/{number}")
    public String makeGuess(@PathVariable String gameId, @PathVariable int number) {

        Game game = games.get(gameId);

        if (game == null) {
            return "Game not found!";
        }

        game.attempts++;

        if (number < game.secretNumber) {
            return "Nr is bigger";
        } else if (number > game.secretNumber) {
            return "Nr is smaller";
        } else {
            return "Correct, it took you " + game.attempts + " times";
        }
    }

    static class Game {
        int secretNumber = new Random().nextInt(100) + 1;
        int attempts = 0;
    }
}