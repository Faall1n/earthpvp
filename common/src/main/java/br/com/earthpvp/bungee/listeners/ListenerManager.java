package br.com.earthpvp.bungee.listeners;

import br.com.earthpvp.bungee.BungeeMain;
import br.com.earthpvp.bungee.listeners.global.HeaderListener;
import br.com.earthpvp.bungee.listeners.global.ProxyPingListener;

import java.util.concurrent.TimeUnit;

public class ListenerManager {

    public ListenerManager(BungeeMain main) {
        HeaderListener headerListener = new HeaderListener();
        main.getProxy().getPluginManager().registerListener(main, headerListener);
        main.getProxy().getScheduler().schedule(main, headerListener::run, 0L, 5L, TimeUnit.SECONDS);

        main.getProxy().getPluginManager().registerListener(main, new ProxyPingListener());
    }

}
