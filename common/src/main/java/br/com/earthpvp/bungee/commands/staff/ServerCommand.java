package br.com.earthpvp.bungee.commands.staff;

import br.com.earthpvp.core.bungee.JSONText;
import br.com.earthpvp.core.bungee.PlayerServer;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Random;

public class ServerCommand extends Command {

    public ServerCommand() {
        super("server", "", "servidor", "go");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof ProxiedPlayer)) {
            sender.sendMessage("§cApenas in-game!");
            return;
        }

        ProxiedPlayer player = (ProxiedPlayer) sender;
        if (!player.hasPermission("earth.bungee.server")) {
            player.sendMessage("§cVocê não possui permissão para executar este comando!");
            return;
        }

        if (args.length == 0) {
            Random random = new Random();
            ChatColor color = ChatColor.WHITE;

            JSONText jsonText = new JSONText();
            jsonText.next()
                    .text("\n")
                    .next()
                    .text("§eLista de servidores disponível:")
                    .next()
                    .text("\n");
            for (ServerInfo serverInfo : ProxyServer.getInstance().getServers().values()) {
                int randomNum = random.nextInt(3);
                if (randomNum == 1) {
                    color = ChatColor.YELLOW;
                } else if (randomNum == 2) {
                    color = ChatColor.GRAY;
                }
                PlayerServer playerServer = new PlayerServer(player);
                jsonText.next()
                        .text("\n")
                        .next()
                        .text("  §f- ")
                        .next()
                        .text(playerServer.statusColor(serverInfo.getName()) + playerServer.nameServer(serverInfo.getName()) + " ")
                        .hoverText(
                                "§e" + playerServer.name() +
                                        "\n\n" +
                                        "§fJogadores online: §7" + serverInfo.getPlayers().size() +
                                        "\n" +
                                        "§fEstado atual: " + playerServer.status(serverInfo.getName()))
                        .next()
                        .text("§7[Clique para conectar]")
                        .clickRunCommand("/server " + playerServer.realName())
                        .send(player);
            }
        } else if (args.length == 1) {
            PlayerServer playerServer = new PlayerServer(player);
            ServerInfo serverInfo = ProxyServer.getInstance().getServerInfo(args[0]);
            if (serverInfo == player.getServer()) {
                player.sendMessage("§cVocê já está conectado no servidor.");
                return;
            }
            if (serverInfo == null) {
                player.sendMessage("§cEste servidor não existe.");
                return;
            }
            try {
                Socket server = new java.net.Socket();
                server.connect(new InetSocketAddress(serverInfo.getAddress().getHostName(), serverInfo.getAddress().getPort()));
                server.close();
                player.connect(serverInfo);
                JSONText jsonText = new JSONText();
                jsonText.next()
                        .text("§aConectando...")
                        .hoverText("§7Server: §f" + playerServer.nameServer(serverInfo.getName()))
                        .send(player);
                return;
            } catch (Exception e) {
                player.sendMessage("§cEste servidor está offline.");
                return;
            }
        }
    }
}
