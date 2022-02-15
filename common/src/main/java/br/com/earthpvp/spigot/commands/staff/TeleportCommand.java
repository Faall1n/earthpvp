package br.com.earthpvp.spigot.commands.staff;

import br.com.earthpvp.core.Helper;
import br.com.earthpvp.core.spigot.LuckPermsSpigot;
import br.com.earthpvp.core.spigot.SpigotCommand;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class TeleportCommand extends SpigotCommand {

    public TeleportCommand() {
        super("tp", "/tp", "", Arrays.asList("teleport", "teleportar"));
        register();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cApenas in-game!");
            return true;
        }

        Player player = (Player) sender;
        if (args.length == 0) {
            player.sendMessage("§cUtilize /tp <player> para teleportar até um jogador.");
        } else if (args.length > 1 && args.length < 3) {
            player.sendMessage("§cUtilize /tp <x, y, z> para teleportar até uma coordenada.");
            return true;
        } else if (args.length == 1) {
            Player target = Bukkit.getServer().getPlayer(args[0]);
            if (target == player) {
                player.sendMessage("§cVocê não pode teleportar para si mesmo.");
                return true;
            }
            if (target == null) {
                player.sendMessage("§cEste jogador não está online.");
                return true;
            } else {
                player.teleport(target);
                player.sendMessage("§aVocê teleportou-se até " + LuckPermsSpigot.groupWithName(target) + "§a.");
                return true;
            }
        } else if (args.length == 3) {
            if (Helper.isDouble(args[0]) && Helper.isInteger(args[1]) && Helper.isInteger(args[2])) {
                double x = Double.parseDouble(args[0]);
                double y = Double.parseDouble(args[1]);
                double z = Double.parseDouble(args[2]);
                Location location = new Location(player.getWorld(), x, y, z);
                player.teleport(location);
            } else {
                player.sendMessage("§cVocê inseriu valores inválidos.");
                return true;
            }
        }
        return false;
    }
}
