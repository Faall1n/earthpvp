package br.com.earthpvp.bungee.commands.global;

import br.com.earthpvp.bungee.BungeeMain;
import br.com.earthpvp.core.Helper;
import br.com.earthpvp.core.bungee.LuckPermsBungee;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class TellCommand extends Command {

    public static HashMap<String, Date> tello = new HashMap<String, Date>();
    public static HashMap<String, String> tell = new HashMap<String, String>();

    public TellCommand() {
        super("tell", "", "msg", "wmsg", "me", "whisper");
    }

    @SuppressWarnings("deprecation")
    public static String getDayFormated() {
        Date now = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        int hora = now.getHours();
        int dps = hora + 1;
        now.setHours(dps);
        String data = format.format(now);
        return data;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof ProxiedPlayer)) {
            return;
        }

        ProxiedPlayer player = (ProxiedPlayer) sender;
        if (args.length == 0) {
            sender.sendMessage("§cUtilize /tell <usuário> <mensagem> para enviar uma mensagem privada.");
            return;
        }
        if (args.length == 1) {
            sender.sendMessage("§cUtilize /tell <usuário> <mensagem> para enviar uma mensagem privada.");
            return;
        }
        if (args.length > 1) {
            if (tello.containsKey(player.getName())) {
                Date now = new Date();
                if (now.after(tello.get(player.getName()))) {
                    tello.remove(player.getName());
                } else {
                    player.sendMessage("§cAguarde para enviar uma mensagem novamente.");
                    return;
                }
            }
            if (BungeeMain.getInstance().getProxy().getPlayer(args[0]) == null) {
                player.sendMessage("§cEste jogador não está online.");
                return;
            }
            ProxiedPlayer target = BungeeMain.getInstance().getProxy().getPlayer(args[0]);
            if (args[0].equalsIgnoreCase(player.getName())) {
                player.sendMessage("§cVocê não pode conversar consigo mesmo.");
                return;
            }
            tell.remove(player.getName());
            tell.remove(target.getName());
            tell.put(player.getName(), target.getName());
            tell.put(target.getName(), player.getName());

            String message = Helper.toMessage(args);

            player.sendMessage("§8Mensagem para §7" + LuckPermsBungee.groupWithName(target) + "§8: §6" + message);
            target.sendMessage("§8Mensagem de §7" + LuckPermsBungee.groupWithName(player) + "§8: §6" + message);
            tello.remove(player.getName());
            Date na = new Date();
            na.setSeconds(na.getSeconds() + 3);
            tello.put(player.getName(), na);
        }
    }
}
