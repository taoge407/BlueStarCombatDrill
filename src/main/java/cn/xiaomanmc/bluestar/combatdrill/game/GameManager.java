package cn.xiaomanmc.bluestar.combatdrill.game;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class GameManager {
    public final int maxSlot = 2;
    private ArrayList<Game> gamePool = new ArrayList<>();

    public ArrayList<Game> getCurrentGames () {
        return gamePool;
    }

    private Set<Game> filter (int stage) {
        if (stage < GameStage.GAME_PREPARE || stage > GameStage.GAME_ROUND_4) {
            throw new IllegalArgumentException("Not a valid stage code!");
        }
        return gamePool.stream().filter(game -> game.getGameStage() == stage).collect(Collectors.toSet());
    }

    /**
     * 给玩家匹配房间
     * 优先 多人准备房间>少人准备房间>
     * @param gamer
     * @return
     * @throws GameFullException
     */
    public Game newGamer(Gamer gamer) throws GameFullException {
        //准备房间非空就排序
        if (!filter(GameStage.GAME_PREPARE).isEmpty()) {
            List<Game> gameList = filter(GameStage.GAME_PREPARE).stream().sorted(Comparator.comparing(
                    game -> game.getCurrentGamers().size())).toList();
            for(Game gameToJoin : gameList) {
                try {
                    gameToJoin.joinGame(gamer);
                    return gameToJoin;
                } catch (GameFullException ignored) {}
            }
        }
        //如果都有人了就看看有无房间有人退出的
        Set<Game> hasPlayerLeftGames = gamePool.stream().filter
                (game -> !(game.getLeftGamers().isEmpty())).collect(Collectors.toSet());
        for (Game hasPlayerLeftGame : hasPlayerLeftGames) {
            try {
                hasPlayerLeftGame.joinGame(gamer);
                return hasPlayerLeftGame;
            } catch (GameFullException ignored) {}
        }
        //都没有就只好新增房间了
        if (gamePool.size() < maxSlot) {
            Game newGame = new Game();
            gamePool.add(newGame);
            newGame.joinGame(gamer);
            return newGame;
        }
        throw new GameFullException();
        /*
        //if game pool isn't full
        if (gamePool.size() < maxSlot) {
            Game game = new Game();
            game.joinGame(gamer);
            gamePool.add(game);
            return game;
        }
        //if
        else if (!filter(GameStage.GAME_PREPARE).isEmpty()) {
            Game game = filter(GameStage.GAME_PREPARE).iterator().next();
            game.joinGame(gamer);
            return game;
        }
        Set<Game> joinableGame = gamePool.stream().filter
                (game -> !(game.getLeftGamers().isEmpty())).collect(Collectors.toSet());
        if (!joinableGame.isEmpty()) {
            Game game = joinableGame.iterator().next();
            game.joinGame(gamer);
            return game;
        }
        throw new GameFullException();
         */
    }
}
