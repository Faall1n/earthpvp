package br.com.earthpvp.bungee.commands.staff;

import br.com.earthpvp.core.bungee.JSONText;
import br.com.earthpvp.core.bungee.LuckPermsBungee;
import br.com.earthpvp.core.bungee.PlayerServer;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class AccountCommand extends Command {

    public AccountCommand() {
        super("account", "", "conta", "info", "inf");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof ProxiedPlayer)) {
            sender.sendMessage("§cApenas in-game!");
            return;
        }

        ProxiedPlayer player = (ProxiedPlayer) sender;
        if (!player.hasPermission("earth.bungee.account")) {
            player.sendMessage("§cVocê não possui permissão para executar este comando!");
            return;
        }
        if (args.length == 0) {
            sender.sendMessage("§cUtilize /account <player> para receber informações sobre os jogadores.");
            return;
        }

        String targetName = args[0];
        ProxiedPlayer target = ProxyServer.getInstance().getPlayer(targetName);
        PlayerServer playerServer = new PlayerServer(target);

        if (target == null) {
            player.sendMessage("§cEste jogador não está online.");
            return;
        }

        JSONText jsonText = new JSONText();

        jsonText.next()
                .text("\n\n")
                .next()
                .text("§eInformações sobre o usuário ")
                .next()
                .text(LuckPermsBungee.getColor(target) + target.getDisplayName())
                .next()
                .text("§e:")
                .next()
                .text("\n\n")
                .next()
                .text(" §eInformações básicas")
                .next()
                .text("\n\n")
                .next()
                .text("   UUID: §7" + target.getUniqueId())
                .next()
                .text("\n")
                .next()
                .text("   Grupo: " + LuckPermsBungee.getFancyPrefix(player))
                .next()
                .text("\n")
                .next()
                .text("   Punido: §c-/-")
                .next()
                .text("\n\n")
                .next()
                .text(" §eAssociações:")
                .next()
                .text("\n\n")
                .next()
                .text("   E-mail: §7" + "Indisponível")
                .next()
                .text("\n")
                .next()
                .text("   Discord: §7" + "§7Indisponível")
                .next()
                .text("\n\n")
                .next()
                .text(" §eConexão:")
                .next()
                .text("\n\n")
                .next()
                .text("   Conectado: " + (target.isConnected() ? "§aSim" : "§cNão"))
                .next()
                .text("\n")
                .next()
                .text("   Servidor: §7" + (target.isConnected() ? playerServer.name() : "Indisponível"))
                .next()
                .text("\n")
                .next()
                .send(player);
    }
}
