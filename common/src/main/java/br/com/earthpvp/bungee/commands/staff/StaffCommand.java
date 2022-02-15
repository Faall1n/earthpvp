package br.com.earthpvp.bungee.commands.staff;

import br.com.earthpvp.core.bungee.JSONText;
import br.com.earthpvp.core.bungee.LuckPermsBungee;
import br.com.earthpvp.core.bungee.PlayerServer;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class StaffCommand extends Command {

    public StaffCommand() {
        super("staff", "", "staffon", "stafflist");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof ProxiedPlayer)) {
            sender.sendMessage("§cApenas in-game!");
            return;
        }

        ProxiedPlayer player = (ProxiedPlayer) sender;
        if (!player.hasPermission("earth.bungee.staff")) {
            player.sendMessage("§cVocê não possui permissão para executar este comando!");
            return;
        }

        JSONText jsonText = new JSONText();
        int adm = 0;
        int ger = 0;
        int mod = 0;
        int ajd = 0;

        jsonText.next()
                .text("\n")
                .next()
                .text("§eEquipe de superior online:")
                .next()
                .text("\n");
        for (ProxiedPlayer superior : ProxyServer.getInstance().getPlayers()) {
            PlayerServer playerServer = new PlayerServer(superior);
            if (LuckPermsBungee.getPrimaryGroup(superior, "adm")) {
                jsonText.next()
                        .text("\n")
                        .next()
                        .text(" §8* " + LuckPermsBungee.getPrefix(superior) + superior.getDisplayName() + " ")
                        .next()
                        .text("§7 - ")
                        .next()
                        .text("§7[" + playerServer.name() + "]")
                        .hoverText("§fJogadores conectados a esse servidor: §7" + playerServer.size() +
                                "\n" +
                                "\n" +
                                "§e* Clique para conectar a este servidor!")
                        .clickRunCommand("/server " + playerServer.realName());
                adm++;
            }
        }
        for (ProxiedPlayer superior : ProxyServer.getInstance().getPlayers()) {
            PlayerServer playerServer = new PlayerServer(superior);
            if (LuckPermsBungee.getPrimaryGroup(superior, "ger")) {
                jsonText.next()
                        .text("\n")
                        .next()
                        .text(" §8* " + LuckPermsBungee.getPrefix(superior) + superior.getDisplayName() + " ")
                        .next()
                        .text("§7 - ")
                        .next()
                        .text("§7[" + playerServer.name() + "]")
                        .hoverText("§fJogadores conectados a esse servidor: §7" + playerServer.size() +
                                "\n" +
                                "\n" +
                                "§e* Clique para conectar a este servidor!")
                        .clickRunCommand("/server " + playerServer.realName());
                ger++;
            }
        }

        jsonText.next()
                .text("\n\n")
                .next()
                .text("§eEquipe de moderativa online:")
                .next()
                .text("\n");
        for (ProxiedPlayer moderate : ProxyServer.getInstance().getPlayers()) {
            PlayerServer playerServer = new PlayerServer(moderate);
            if (LuckPermsBungee.getPrimaryGroup(moderate, "mod")) {
                jsonText.next()
                        .text("\n")
                        .next()
                        .text(" §8* " + LuckPermsBungee.getPrefix(moderate) + moderate.getDisplayName() + " ")
                        .next()
                        .text("§7 - ")
                        .next()
                        .text("§7[" + playerServer.name() + "]")
                        .hoverText("§fJogadores conectados a esse servidor: §7" + playerServer.size() +
                                "\n" +
                                "\n" +
                                "§e* Clique para conectar a este servidor!")
                        .clickRunCommand("/server " + playerServer.realName());
                mod++;
            }
        }
        for (ProxiedPlayer moderate : ProxyServer.getInstance().getPlayers()) {
            PlayerServer playerServer = new PlayerServer(moderate);
            if (LuckPermsBungee.getPrimaryGroup(moderate, "ajd")) {
                jsonText.next()
                        .text("\n")
                        .next()
                        .text(" §8* " + LuckPermsBungee.getPrefix(moderate) + moderate.getDisplayName() + " ")
                        .next()
                        .text("§7 - ")
                        .next()
                        .text("§7[" + playerServer.name() + "]")
                        .hoverText("§fJogadores conectados a esse servidor: §7" + playerServer.size() +
                                "\n" +
                                "\n" +
                                "§e* Clique para conectar a este servidor!")
                        .clickRunCommand("/server " + playerServer.realName());
                ajd++;
            }
        }

        jsonText.next()
                .text("\n")
                .next()
                .send(player);

    }
}
