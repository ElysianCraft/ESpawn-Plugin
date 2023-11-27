package fr.elysiancraft.spawn;

import fr.elysiancraft.spawn.commands.SetSpawnCommand;
import fr.elysiancraft.spawn.commands.SpawnCommand;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public final class Spawn extends JavaPlugin implements Listener {
    @Override
    public void onEnable() {
        getLogger().info("Plugin activé !");
        Objects.requireNonNull(getCommand("spawn")).setExecutor(new SpawnCommand());
        Objects.requireNonNull(getCommand("setspawn")).setExecutor(new SetSpawnCommand());
        getServer().getPluginManager().registerEvents(this, this);
        saveDefaultConfig();
    }

    @Override
    public void onDisable() {
        getLogger().info("Plugin désactivé !");
    }

    @Override
    public @NotNull FileConfiguration getConfig() {
        return super.getConfig();
    }

    @Override
    public void saveDefaultConfig() {
        super.saveDefaultConfig();
    }

    @Override
    public void reloadConfig() {
        super.reloadConfig();
    }

    @Override
    public void saveConfig() {
        super.saveConfig();
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        if (event.getPlayer().getWorld() != getServer().getWorld("world")) return;
        if (Objects.requireNonNull(event.getTo()).getY() < (Integer) getConfig().get("void", 35)) {
            tpSpawnPlayer(event.getPlayer(), false);
            event.getPlayer().setFallDistance(0);
        }
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        Bukkit.getScheduler().runTaskLater(this, () -> {
            tpSpawnPlayer(event.getPlayer(), false);
        }, 1);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        tpSpawnPlayer(event.getPlayer(), false);
    }

    public void tpSpawnPlayer(Player player, Boolean message) {
        double x = getConfig().getDouble("spawn.x");
        double z = getConfig().getDouble("spawn.z");
        player.teleport(Objects.requireNonNull(Bukkit.getWorld("world")).getHighestBlockAt((int) x, (int) z).getLocation().add(0.5, 1, 0.5).setDirection(new Vector(1, 0, 0)));
        if (message) player.sendMessage("§aVous avez ete teleporte au spawn.");
    }

    public static Spawn getInstance() {
        return JavaPlugin.getPlugin(Spawn.class);
    }
}