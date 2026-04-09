package com.example.guessgame;

import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
public class GameController {

    private final GameRepository gameRepository;

    public GameController(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    // endpoint 1 - create a game and save in db
    @GetMapping("/game")
    public String startGame() {
        String gameId = UUID.randomUUID().toString();
        int secretNumber = new Random().nextInt(100) + 1;

        GameEntity game = new GameEntity(gameId, secretNumber);
        gameRepository.save(game);
        return "Game ID: " + gameId;
    }

    // endpoint 2 : guess
    @GetMapping("/game/{gameId}/guess/{number}")
    public String makeGuess(@PathVariable String gameId, @PathVariable int number) {

        Optional<GameEntity> optional = gameRepository.findById(gameId);

        if (optional.isEmpty()) {
            return "Game not found!";
        }

        GameEntity game = optional.get();

        if (game.isFinished()) {
            return "This game is already finished!";
        }

        game.setAttempts(game.getAttempts() + 1);
        String result;

        if (number < game.getSecretNumber()) {
            result = "Nr is bigger";
        } else if (number > game.getSecretNumber()) {
            result = "Nr is smaller";
        } else {
            game.setFinished(true);
            result = "Correct, it took you " + game.getAttempts() + " times";
        }

        gameRepository.save(game);
        return result;
    }
}