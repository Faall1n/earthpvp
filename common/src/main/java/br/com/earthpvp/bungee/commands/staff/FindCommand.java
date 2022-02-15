package br.com.earthpvp.bungee.commands.staff;

import br.com.earthpvp.core.bungee.JSONText;
import br.com.earthpvp.core.bungee.LuckPermsBungee;
import br.com.earthpvp.core.bungee.PlayerServer;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class FindCommand extends Command {

    public FindCommand() {
        super("find", "", "find", "encontrar");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof ProxiedPlayer)) {
            sender.sendMessage("§cApenas in-game!");
            return;
        }

        ProxiedPlayer player = (ProxiedPlayer) sender;
        if (!player.hasPermission("earth.bungee.find")) {
            player.sendMessage("§cVocê não possui permissão para executar este comando!");
            return;
        }

        if (args.length == 0) {
            player.sendMessage("§cUtilize /find <player> para encontrar um jogador.");
            return;
        }

        String targetName = args[0];
        ProxiedPlayer target = ProxyServer.getInstance().getPlayer(targetName);
        PlayerServer playerServer = new PlayerServer(target);

        if (target == null) {
            player.sendMessage("§cEste jogador não está online.");
            return;
        }

        if (targetName.equalsIgnoreCase(sender.getName())) {
            player.sendMessage("§eVocê está conectado no servidor §7" + playerServer.name() + "§e.");
            return;
        }

        JSONText jsonText = new JSONText();
        jsonText.next()
                .text("§eO jogador ")
                .next()
                .text(LuckPermsBungee.groupWithName(target))
                .next()
                .text("§e está conectado ao servidor ")
                .next()
                .text("§7" + playerServer.name() + "§e.")
                .next()
                .send(player);
    }
}
