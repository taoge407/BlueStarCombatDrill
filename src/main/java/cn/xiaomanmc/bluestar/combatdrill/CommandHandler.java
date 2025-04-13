package cn.xiaomanmc.bluestar.combatdrill;

import cn.xiaomanmc.bluestar.combatdrill.game.Game;
import cn.xiaomanmc.bluestar.combatdrill.game.GameFullException;
import cn.xiaomanmc.bluestar.combatdrill.game.Gamer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import javax.annotation.ParametersAreNonnullByDefault;

public class CommandHandler implements CommandExecutor
{
    @Override
    @ParametersAreNonnullByDefault
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (command.getName().equals("join")) {
            try {
                Game joinedGame =CombatDrill.gameManager.newGamer(
                        /*new CombatDrill().instance.getServer().getPlayer(commandSender.getName())*/
                        new Gamer((Player) commandSender));
                commandSender.sendMessage("&6您已加入房间 &3" + joinedGame.getUID());
            } catch (GameFullException e) {
                commandSender.sendMessage("&4游戏太爆满了！请稍后再试");
                return true;
            }
            return true;
        }
        return true;
    }
}
