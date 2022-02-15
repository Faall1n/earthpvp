package br.com.earthpvp.core.bungee;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.net.InetSocketAddress;
import java.net.Socket;

public class PlayerServer {

    private final ProxiedPlayer proxiedPlayer;

    public PlayerServer(ProxiedPlayer proxiedPlayer) {
        this.proxiedPlayer = proxiedPlayer;
    }

    public String name() {
        return proxiedPlayer.getServer().getInfo().getName()
                .replace("lobby", "Saguão"
                        .replace("network", "Principal")
                        .replace("-", " "));
    }

    public String nameServer(String server) {
        return ProxyServer.getInstance().getServerInfo(server).getName()
                .replace("lobby", "Saguão"
                        .replace("network", "Principal")
                        .replace("-", " "));
    }

    public String realName() {
        return proxiedPlayer.getServer().getInfo().getName();
    }

    public int size() {
        return proxiedPlayer.getServer().getInfo().getPlayers().size();
    }

    public String slots() {
        return "";
    }

    public String status(String server) {
        ServerInfo serverInfo = ProxyServer.getInstance().getServerInfo(server);
        try {
            Socket s = new Socket();
            s.connect(new InetSocketAddress(serverInfo.getAddress().getAddress(), serverInfo.getAddress().getPort()), 15);
            s.close();
            server = "§aOnline";
        } catch (Exception e) {
            server = "§cOffline";
        }
        return server;
    }

    public String statusColor(String server) {
        return status(server).split("\\[")[0];
    }

}
