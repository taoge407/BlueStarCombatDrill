package cn.xiaomanmc.bluestar.combatdrill.role;

import cn.xiaomanmc.bluestar.combatdrill.game.Gamer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.Map;

public class jobSelectorGUIFactory {
    private Inventory inventoryGUI;
    private static final int[] paneSlot = {2, 3, 4, 5, 6, 7, 8, 9, 10, 15, 17, 18, 27, 36, 45, 24, 33, 42, 51, 26, 35, 44, 53};
    private static final int federationSlot = 0;
    private static final int pmcSlot = 1;
    private static final int[] mainGunColumn = {2, 3, 4};
    private static final int pistolColumn = 7;

    public jobSelectorGUIFactory(Gamer gamer) {
        Map<Integer, RoleConfig> roleConfigMap = RoleConfigLoader.getConfig();
        String guiTitle = "§3请直接点击主武器选择职业 §8| §6" + gamer.getMcPlayer().getName();
        inventoryGUI = Bukkit.createInventory(gamer.getMcPlayer(), 6 * 9, guiTitle);

        // place panes
        ItemStack pane = new ItemStack(Material.ORANGE_STAINED_GLASS_PANE, 1);
        ItemMeta paneMeta = pane.getItemMeta();
        paneMeta.setDisplayName(" ");
        pane.setItemMeta(paneMeta);
        for (int pSlot: paneSlot) {
            inventoryGUI.setItem(pSlot, pane);
        }

        // place team UI
        ItemStack teamFlag;
        int team = gamer.getRole() / 10;
        if (team == Roles.FEDERATION /10) {
            teamFlag = new ItemStack(Material.LIGHT_BLUE_CONCRETE);
            ItemMeta federationMeta = teamFlag.getItemMeta();
            federationMeta.setDisplayName("§b阵营：联邦");
            teamFlag.setItemMeta(federationMeta);
            inventoryGUI.setItem(federationSlot, teamFlag);
        } else {
            teamFlag = new ItemStack(Material.RED_CONCRETE);
            ItemMeta pmcMeta = teamFlag.getItemMeta();
            assert pmcMeta != null;
            pmcMeta.setDisplayName("§4阵营：PMC");
            inventoryGUI.setItem(pmcSlot, teamFlag);
        }

        // place Column Head
        ItemStack mainGunColumnHead = new ItemStack(Material.ORANGE_SHULKER_BOX);
        ItemMeta mainGunColumnHeadMeta = mainGunColumnHead.getItemMeta();
        mainGunColumnHeadMeta.setDisplayName("§5§l主武器");
        mainGunColumnHeadMeta.setLore(List.of("§6请点击职业栏所对应的武器来选择职业与所持武器！"));
        mainGunColumnHead.setItemMeta(mainGunColumnHeadMeta);
        for (int column: mainGunColumn) {
            inventoryGUI.setItem(column + 9, mainGunColumnHead);
        }
        ItemStack pistolColumnHead = new ItemStack(Material.YELLOW_SHULKER_BOX);
        ItemMeta pistolColumnHeadMeta = pistolColumnHead.getItemMeta();
        pistolColumnHeadMeta.setDisplayName("§9§l副武器");
        pistolColumnHeadMeta.setLore(List.of("§c副武器与职业固定搭配，无法选中！"));

        // pl
    }
}
