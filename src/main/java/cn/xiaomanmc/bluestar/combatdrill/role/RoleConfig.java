package cn.xiaomanmc.bluestar.combatdrill.role;

import org.bukkit.inventory.ItemStack;

import javax.annotation.Nullable;
import java.util.ArrayList;

public class RoleConfig {
    @Nullable
    private ItemStack flag;
    private ArrayList<GunBulletPair> mainGunList;
    private GunBulletPair pistol;
    /**
     * 从头到脚 0-3
     */
    private ArrayList<ItemStack> armorPair;

    public RoleConfig(@Nullable ItemStack flag, ArrayList<GunBulletPair> mainGunList, GunBulletPair pistol, ArrayList<ItemStack> armorPair) {
        this.flag = flag;
        this.mainGunList = mainGunList;
        this.pistol = pistol;
        this.armorPair = armorPair;
    }

    public ArrayList<GunBulletPair> getMainGunList() {
        return mainGunList;
    }

    public void setMainGunList(ArrayList<GunBulletPair> mainGunList) {
        this.mainGunList = mainGunList;
    }

    public GunBulletPair getPistol() {
        return pistol;
    }

    public void setPistol(GunBulletPair pistol) {
        this.pistol = pistol;
    }

    public ArrayList<ItemStack> getArmorPair() {
        return armorPair;
    }

    public void setArmorPair(ArrayList<ItemStack> armorPair) {
        this.armorPair = armorPair;
    }

    public ItemStack getFlag() {
        return flag;
    }

    public void setFlag(ItemStack flag) {
        this.flag = flag;
    }
}
