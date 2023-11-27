package fr.elysiancraft.spawn.commands;

import fr.elysiancraft.spawn.Spawn;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SpawnCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        Player player = (Player) sender;

        player.sendMessage("§cVous allez etre teleporte dans " + Spawn.getInstance().getConfig().get("spawn-delay", 3) + " secondes.");

        try {
            Thread.sleep((Integer) Spawn.getInstance().getConfig().get("spawn-delay", 3) * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Spawn.getInstance().tpSpawnPlayer(player, true);
        return false;
    }
}
