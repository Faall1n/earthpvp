package br.com.earthpvp.bungee.commands.global;

import br.com.earthpvp.bungee.BungeeMain;
import br.com.earthpvp.core.Helper;
import br.com.earthpvp.core.bungee.LuckPermsBungee;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import java.util.Date;

public class ReplyCommand extends Command {

    public ReplyCommand() {
        super("r", "", "reply", "reponder");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof ProxiedPlayer)) {
            return;
        }

        ProxiedPlayer player = (ProxiedPlayer) sender;
        if (args.length == 0) {
            sender.sendMessage("§cUtilize /r <mensagem> para enviar uma mensagem privada.");
            return;
        }

        if (args.length > 0) {
            if (TellCommand.tello.containsKey(player.getName())) {
                Date now = new Date();
                if (now.after(TellCommand.tello.get(player.getName()))) {
                    TellCommand.tello.remove(player.getName());
                } else {
                    player.sendMessage("§cAguarde para responder uma mensagem novamente.");
                    return;
                }
            }

            if (TellCommand.tell.get(player.getName()) == null) {
                player.sendMessage("§cVocê não tem uma conversa recente.");
                return;
            }

            String name = TellCommand.tell.get(player.getName());
            if (BungeeMain.getInstance().getProxy().getPlayer(name) == null) {
                player.sendMessage("§cEste jogador não está online.");
                return;
            }

            ProxiedPlayer target = BungeeMain.getInstance().getProxy().getPlayer(args[0]);
            if (args[0].equalsIgnoreCase(player.getName())) {
                player.sendMessage("§cVocê não pode conversar consigo mesmo.");
                return;
            }

            String message = Helper.toMessage(args);

            player.sendMessage("§8Mensagem para §7" + LuckPermsBungee.groupWithName(target) + "§8: §6" + message);
            target.sendMessage("§8Mensagem de §7" + LuckPermsBungee.groupWithName(player) + "§8: §6" + message);
            TellCommand.tello.remove(player.getName());
            Date na = new Date();
            na.setSeconds(na.getSeconds() + 3);
            TellCommand.tello.put(player.getName(), na);
        }
    }
}
