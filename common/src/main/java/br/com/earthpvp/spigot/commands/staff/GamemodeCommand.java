package br.com.earthpvp.spigot.commands.staff;

import br.com.earthpvp.core.spigot.LuckPermsSpigot;
import br.com.earthpvp.core.spigot.SpigotCommand;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collections;

public class GamemodeCommand extends SpigotCommand {

    public GamemodeCommand() {
        super("gamemode", "/gamemode", "", Collections.singletonList("gm"));
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
            player.sendMessage("§cUtilize /gamemode <mode> <player> para alterar o modo de um jogador.");
        } else if (args.length == 1) {
            String mode = args[0];

            GameMode gamemode = getGamemode(mode);

            if (gamemode == null) {
                player.sendMessage("§cModo inválido.");
                return false;
            }

            if (player.getGameMode() == gamemode) {
                player.sendMessage("§cVocê já está no §l" + player.getGameMode().name().toLowerCase() + "§c!");
            } else {
                player.setGameMode(gamemode);
                player.sendMessage("§aVocê alterou seu §lGAMEMODE§a para §f§l" + player.getGameMode().name().toLowerCase() + "§a!");
            }
            return true;
        } else if (args.length == 2) {
            String mode = args[0];
            Player target = Bukkit.getPlayer(args[1]);

            if (target == null) {
                player.sendMessage("§cEste jogador não está online.");
                return true;
            }

            GameMode gamemode = getGamemode(mode);
            if (gamemode == null) {
                player.sendMessage("§cModo inválido.");
                return false;
            }

            target.setGameMode(gamemode);
            player.sendMessage("§aVocê alterou o §lGAMEMODE§a do player " + LuckPermsSpigot.groupWithName(target) + " §a para §f§l" + target.getGameMode() + "§a!");
        } else {
            player.sendMessage("§cUtilize /gamemode <mode> <player> para alterar o modo de um jogador.");
        }
        return false;
    }

    public GameMode getGamemode(String mode) {
        GameMode gamemode = null;
        if (mode.equalsIgnoreCase("0") || mode.equalsIgnoreCase("s") || mode.equalsIgnoreCase("survival")) {
            gamemode = GameMode.SURVIVAL;
        } else if (mode.equalsIgnoreCase("1") || mode.equalsIgnoreCase("c") || mode.equalsIgnoreCase("criativo")) {
            gamemode = GameMode.CREATIVE;
        } else if (mode.equalsIgnoreCase("2") || mode.equalsIgnoreCase("a") || mode.equalsIgnoreCase("aventura")) {
            gamemode = GameMode.CREATIVE;
        }
        return gamemode;
    }
}
