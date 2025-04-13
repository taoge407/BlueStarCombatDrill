package cn.xiaomanmc.bluestar.combatdrill.role;

import org.bukkit.inventory.ItemStack;

@Deprecated
public class ArmorPair {
    private ItemStack head;
    private ItemStack body;
    private ItemStack leg;
    private ItemStack foot;

    public ItemStack getHead() {
        return head;
    }

    public void setHead(ItemStack head) {
        this.head = head;
    }

    public ItemStack getBody() {
        return body;
    }

    public void setBody(ItemStack body) {
        this.body = body;
    }

    public ItemStack getLeg() {
        return leg;
    }

    public void setLeg(ItemStack leg) {
        this.leg = leg;
    }

    public ItemStack getFoot() {
        return foot;
    }

    public void setFoot(ItemStack foot) {
        this.foot = foot;
    }
}
