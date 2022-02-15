package br.com.earthpvp.bungee.listeners.global;

import net.md_5.bungee.api.ServerPing;
import net.md_5.bungee.api.event.ProxyPingEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.util.UUID;

public class ProxyPingListener implements Listener {

    @EventHandler
    public void onPing(ProxyPingEvent event) {
        ServerPing ping = event.getResponse();
        ServerPing.Players player = ping.getPlayers();

        player.setSample(new ServerPing.PlayerInfo[]{
                new ServerPing.PlayerInfo("§e§lSITE§e: www.earthpvp.com.br", UUID.randomUUID()),
        });

        ping.setDescription("§e§lEARTH PVP §8- §7[§f1.8 §7➠ §f1.12.2§7]\n"
                + "§6§L➠ §fServidor §b§lABERTO §foficialmente.");

        event.setResponse(ping);
    }

}
