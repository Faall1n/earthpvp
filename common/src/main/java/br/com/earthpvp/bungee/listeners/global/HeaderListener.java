package br.com.earthpvp.bungee.listeners.global;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ServerConnectedEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HeaderListener implements Listener {

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private final SimpleDateFormat hourFormat = new SimpleDateFormat("HH:mm");

    public void run() {
        for (ProxiedPlayer player : ProxyServer.getInstance().getPlayers()) {
            BaseComponent[] header = createHeader();
            BaseComponent[] foot = createFoot();
            player.setTabHeader(header, foot);
        }
    }

    @EventHandler(priority = 64)
    public void onJoin(ServerConnectedEvent event) {
        if (event.getPlayer().getServer() == null)
            return;
        event.getPlayer().setTabHeader(createHeader(), createFoot());
    }

    private BaseComponent[] createHeader() {
        int online = ProxyServer.getInstance().getOnlineCount();
        return TextComponent.fromLegacyText("\n§e§lETHOS RELAMS\n" + "§7Servidor atualmente contém §a" + online + " §7jogadores\n");
    }

    private BaseComponent[] createFoot() {
        Date time = new Date();
        String date = this.dateFormat.format(time);
        String hour = this.hourFormat.format(time);
        return TextComponent.fromLegacyText(
                "\nAtualmente são §b" + hour + "§f do dia §b" + date + "\n" +
                        "§eAdquira VIP ou Cash acessando §fwww.ethosrelams.com.br\n");
    }

}
