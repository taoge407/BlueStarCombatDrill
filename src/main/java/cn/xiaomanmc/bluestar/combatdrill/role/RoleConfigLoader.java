package cn.xiaomanmc.bluestar.combatdrill.role;

import cn.xiaomanmc.bluestar.combatdrill.CombatDrill;
import org.bukkit.BlockChangeDelegate;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RoleConfigLoader {
    private static final Map<Integer, RoleConfig> config = new HashMap<>();

    public static void load() {
        World parentWorld = CombatDrill.mvCore.getMVWorldManager().getMVWorld("parent_world").getCBWorld();
        FileConfiguration fileConfiguration = new CombatDrill().instance.getConfig();
        //遍历config 11-14 21-24
        for (int team = 10; team <= 20; team+=10) {
            for (int job = 1; job <= 4; job++) {
                int pointer = team + job;
                int x = fileConfiguration.getInt(pointer + ".x");
                int y = fileConfiguration.getInt(pointer + ".y");
                int z = fileConfiguration.getInt(pointer + ".z");
                Block block = parentWorld.getBlockAt(x, y, z);
                if (block.getState() instanceof Chest chest) {
                    Inventory inv = chest.getInventory();
                    ItemStack[] itemStack = inv.getContents();
                    ArrayList<GunBulletPair> mainGunList = new ArrayList<>();
                    GunBulletPair pistol = null;
                    ArrayList<ItemStack> armorPair = new ArrayList<>();
                    //scroll the slot from bottom
                    for (int gunSlot = 8; gunSlot >= 0; gunSlot--) {
                        if (itemStack[gunSlot] != null) {
                            GunBulletPair pair = new GunBulletPair(itemStack[gunSlot], itemStack[gunSlot+9]);
                            if (pistol == null) {
                                pistol = pair;
                            } else {
                                mainGunList.add(pair);
                            }
                        }
                    }

                    //load flagItem
                    ItemStack flag = itemStack[18];

                    for (int armorSlot = 0; armorSlot <= 4; armorSlot++) {
                        int slot = 26 - armorSlot;
                        if (itemStack[slot] != null) {
                            armorPair.set(armorSlot, itemStack[slot]);
                        }
                    }
                    RoleConfig roleConfig = new RoleConfig(flag, mainGunList, pistol, armorPair);
                    config.put(pointer, roleConfig);
                } else {
                    throw new RuntimeException("Valid Chest Position At [" + x + "," + y + "," + z + "]");
                }
            }
        }
    }

    public static Map<Integer, RoleConfig> getConfig() {
        return config;
    }
}
