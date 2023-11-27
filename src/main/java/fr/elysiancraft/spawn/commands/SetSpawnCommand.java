package fr.elysiancraft.spawn.commands;

import fr.elysiancraft.spawn.Spawn;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SetSpawnCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        Player player = (Player) sender;
        double x = player.getLocation().getX();
        double z = player.getLocation().getZ();

        Spawn.getInstance().getConfig().set("spawn.x", x);
        Spawn.getInstance().getConfig().set("spawn.z", z);
        Spawn.getInstance().saveConfig();

        player.sendMessage("§aLe spawn a ete defini en X: " + x + " Z: " + z);
        return false;
    }
}
