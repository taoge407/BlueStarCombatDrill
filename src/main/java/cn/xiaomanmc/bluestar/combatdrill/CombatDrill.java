package cn.xiaomanmc.bluestar.combatdrill;

import cn.xiaomanmc.bluestar.combatdrill.game.GameManager;
import cn.xiaomanmc.bluestar.combatdrill.role.RoleConfigLoader;
import com.onarandombox.MultiverseCore.MultiverseCore;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class CombatDrill extends JavaPlugin {
    public JavaPlugin instance = this;
    public static MultiverseCore mvCore = (MultiverseCore) Bukkit.getServer().getPluginManager().getPlugin("Multiverse-Core");
    public static GameManager gameManager = new GameManager();

    @Override
    public void onEnable() {
        saveDefaultConfig();
        if (Bukkit.getPluginCommand("join") != null) {
            Bukkit.getPluginCommand("join").setExecutor(new CommandHandler());
        }
        RoleConfigLoader.load();
        // Plugin startup logic
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
