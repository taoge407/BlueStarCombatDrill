package cn.xiaomanmc.bluestar.combatdrill.game;

import cn.xiaomanmc.bluestar.combatdrill.CombatDrill;
import cn.xiaomanmc.bluestar.combatdrill.role.Roles;
import com.onarandombox.MultiverseCore.MultiverseCore;
import org.bukkit.Location;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

public class Game {
    public static final int MAX_PLAYER_COUNT = 20;

    private String UID;
    private Set<Gamer> currentGamers = new HashSet<>();
    private Set<Gamer> leftGamers = new HashSet<>();
    private int gameStage = GameStage.GAME_PREPARE;

    public boolean create() {
        //time hex
        UID = "Game-" + Long.toHexString(System.currentTimeMillis()/1000);
        MultiverseCore mvCore = CombatDrill.mvCore;
        mvCore.getMVWorldManager().cloneWorld("parent_world", UID);
        //Divide team
        Iterator<Gamer> iterator = currentGamers.iterator();
        for(int i = 1; i <= currentGamers.size()/2; i++) {
            iterator.next().setRole(Roles.FEDERATION);
        }
        if (currentGamers.size() % 2 == 0) {
            while (iterator.hasNext()) {
                iterator.next().setRole(Roles.PMC);
            }
        } else {
            if (new Random().nextBoolean()) {
                iterator.next().setRole(Roles.FEDERATION);
            }
            while (iterator.hasNext()) {
                iterator.next().setRole(Roles.PMC);
            }
        }

        return false;
    }

    public void joinGame(Gamer gamer) throws GameFullException {
        if (leftGamers.isEmpty() && gameStage != GameStage.GAME_PREPARE && currentGamers.size() >= MAX_PLAYER_COUNT) {
            throw new GameFullException();
        }
        //准备状态则加入等待大厅
        if (gameStage == GameStage.GAME_PREPARE) {
            currentGamers.add(gamer);
            MultiverseCore core = CombatDrill.mvCore;
            Location lobby = core.getMVWorldManager().getMVWorld("wait_lobby").getSpawnLocation();
            core.getSafeTTeleporter().safelyTeleport(gamer.getMcPlayer(), gamer.getMcPlayer(), lobby, false);
            /*
            //分配队伍
            int federationCount = getTeamGamer(Roles.FEDERATION, false).size();
            int pmcCount = getTeamGamer(Roles.PMC, false).size();
            //When count is the same, divide team by random
            if (pmcCount == federationCount) {
                if (new Random().nextBoolean()) {
                    gamer.setRole(Roles.FEDERATION);
                } else {
                    gamer.setRole(Roles.PMC);
                }
            } else {
                if (federationCount < pmcCount) {
                    gamer.setRole(Roles.FEDERATION);
                } else {
                    gamer.setRole(Roles.PMC);
                }
            }


             */
        }

    }

    public int getGameStage() {
        return gameStage;
    }

    public Set<Gamer> getLeftGamers() {
        return leftGamers;
    }

    public Set<Gamer> getCurrentGamers() {
        return currentGamers;
    }

    public String getUID() {
        return UID;
    }

    public Set<Gamer> getTeamGamer(int team, boolean containOffline) {
        if (team != Roles.FEDERATION && team != Roles.PMC) throw new IllegalArgumentException("Valid Team Code");
        Set<Gamer> result = currentGamers.stream().filter(gamer -> (gamer.getRole() / 10) == (team / 10)).collect(Collectors.toSet());
        if (containOffline) {
            result.addAll(leftGamers.stream().filter(gamer -> (gamer.getRole() / 10) == (team / 10)).collect(Collectors.toSet()));
        }
        return result;
        /*
        Set<Gamer> result = new HashSet<>();
        for (Gamer gamer : currentGamers) {
            if ((gamer.getRole() / 10) == (team / 10)) {
                result.add(gamer);
            }
        }
        if (containOffline) {
            for (Gamer gamer : leftGamers) {
                if ((gamer.getRole() / 10) == (team / 10)) {
                    result.add(gamer);
                }
            }
        }
        return result;
         */
    }
}
