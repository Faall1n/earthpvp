package br.com.earthpvp.bungee.commands.staff;

import br.com.earthpvp.core.bungee.JSONText;
import br.com.earthpvp.core.bungee.LuckPermsBungee;
import br.com.earthpvp.core.bungee.PlayerServer;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class BtpCommand extends Command {

    public BtpCommand() {
        super("btp", "", "bungeetp");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof ProxiedPlayer)) {
            sender.sendMessage("§cApenas in-game!");
            return;
        }

        ProxiedPlayer player = (ProxiedPlayer) sender;
        if (!player.hasPermission("earth.bungee.btp")) {
            player.sendMessage("§cVocê não possui permissão para executar este comando!");
            return;
        }

        if (args.length == 0) {
            player.sendMessage("§cUtilize /btp <player> para teleportar até um jogador.");
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
            player.sendMessage("§cVocê não pode se teletransportar para si mesmo.");
            return;
        }

        ServerInfo server = ProxyServer.getInstance().getServerInfo(target.getServer().getInfo().getName());
        if (server == player.getServer()) {
            player.sendMessage("§cVocê já está conectado ao mesmo servidor do que esse player.");
            return;
        }

        JSONText jsonText = new JSONText();
        jsonText.next()
                .text("§eTeletransportando você até ")
                .next()
                .text(LuckPermsBungee.groupWithName(target))
                .hoverText("§fServer: §7" + playerServer.name())
                .next()
                .text("§e.")
                .next()
                .send(player);
        player.connect(server);

    }
}
