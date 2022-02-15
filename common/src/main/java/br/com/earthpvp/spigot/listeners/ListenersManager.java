package br.com.earthpvp.spigot.listeners;

import br.com.earthpvp.spigot.SpigotMain;

public class ListenersManager {

    public ListenersManager(SpigotMain main) {
        main.getServer().getPluginManager().registerEvents(new WeatherChangeListener(), main);
    }

}
