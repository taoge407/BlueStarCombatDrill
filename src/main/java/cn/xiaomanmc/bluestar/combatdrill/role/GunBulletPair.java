package cn.xiaomanmc.bluestar.combatdrill.role;

import org.bukkit.inventory.ItemStack;

public class GunBulletPair {
    private ItemStack gun;
    private ItemStack bullet;

    public GunBulletPair(ItemStack gun, ItemStack bullet) {
        this.gun = gun;
        this.bullet = bullet;
    }

    public ItemStack getGun() {
        return gun;
    }

    public void setGun(ItemStack gun) {
        this.gun = gun;
    }

    public ItemStack getBullet() {
        return bullet;
    }

    public void setBullet(ItemStack bullet) {
        this.bullet = bullet;
    }
}
