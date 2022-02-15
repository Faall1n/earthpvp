package br.com.earthpvp.bungee.commands.staff;

import br.com.earthpvp.core.Helper;
import br.com.earthpvp.core.bungee.LuckPermsBungee;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class AnnouncementCommand extends Command {

    public AnnouncementCommand() {
        super("anunciar", "", "announcement", "announce", "broadcast", "alerta", "alert");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof ProxiedPlayer)) {
            sender.sendMessage("§cApenas in-game!");
            return;
        }

        ProxiedPlayer player = (ProxiedPlayer) sender;
        if (!player.hasPermission("earth.bungee.announcement")) {
            player.sendMessage("§cVocê não possui permissão para executar este comando!");
            return;
        }

        if (args.length == 0) {
            sender.sendMessage("§cUtilize /anunciar <mensagem> para enviar um anúncio para todos os jogadores.");
        }

        String message = Helper.toMessage(args).replace("&", "§");

        ProxyServer.getInstance().broadcast();
        ProxyServer.getInstance().broadcast(LuckPermsBungee.getPrefix(player) + sender.getName() + "§f: " + message);
        ProxyServer.getInstance().broadcast();

    }
}
