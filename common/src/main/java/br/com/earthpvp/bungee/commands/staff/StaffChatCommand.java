package br.com.earthpvp.bungee.commands.staff;

import br.com.earthpvp.core.Helper;
import br.com.earthpvp.core.bungee.JSONText;
import br.com.earthpvp.core.bungee.LuckPermsBungee;
import br.com.earthpvp.core.bungee.PlayerServer;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class StaffChatCommand extends Command {

    public StaffChatCommand() {
        super("sc", "", "chatstaff", "staffchat", "schat", "chats");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof ProxiedPlayer)) {
            sender.sendMessage("§cApenas in-game!");
            return;
        }

        ProxiedPlayer player = (ProxiedPlayer) sender;
        if (!player.hasPermission("earth.bungee.staffchat")) {
            player.sendMessage("§cVocê não possui permissão para executar este comando!");
            return;
        }

        if (args.length == 0) {
            player.sendMessage("§cUtilize /staffchat <player> para teleportar até um jogador.");
            return;
        }

        String message = Helper.toMessage(args).replace("&", "§");

        JSONText jsonText = new JSONText();
        for (ProxiedPlayer players : ProxyServer.getInstance().getPlayers()) {
            PlayerServer playerServer = new PlayerServer(players);
            if (players.hasPermission("earth.bungee.staffchat"))
                jsonText.next()
                        .text("§d§l[STAFF] ")
                        .next()
                        .text("§7[" + playerServer.name() + "] ")
                        .hoverText("§fJogadores conectados a esse servidor: §7" + playerServer.size() +
                                "\n" +
                                "\n" +
                                "§e* Clique para conectar a este servidor!")
                        .clickRunCommand("/server " + playerServer.realName())
                        .next()
                        .text(LuckPermsBungee.groupWithName(players))
                        .next()
                        .text("§7: §f" + message)
                        .send(players);
        }

    }
}
