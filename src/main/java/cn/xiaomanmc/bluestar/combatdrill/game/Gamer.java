package cn.xiaomanmc.bluestar.combatdrill.game;

import org.bukkit.entity.Player;

public class Gamer {
    private Player mcPlayer;
    private int role;
    private int gunSlot;
    private int kill = 0;
    private int death = 0;

    public Gamer (Player player) {
        mcPlayer = player;
    }

    public Player getMcPlayer() {
        return mcPlayer;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public int getGunSlot() {
        return gunSlot;
    }

    public void setGunSlot(int gunSlot) {
        this.gunSlot = gunSlot;
    }
}
