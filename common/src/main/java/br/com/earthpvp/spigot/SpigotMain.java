package br.com.earthpvp.spigot;

import br.com.earthpvp.spigot.commands.CommandManager;
import br.com.earthpvp.spigot.listeners.ListenersManager;
import br.com.earthpvp.spigot.nametag.TagTeam;
import org.bukkit.plugin.java.JavaPlugin;

public class SpigotMain extends JavaPlugin {

    private static SpigotMain instance;

    public static SpigotMain getInstance() {
        return instance;
    }

    public void onEnable() {
        instance = this;

        new CommandManager();
        new ListenersManager(this);
        TagTeam.run();

        getServer().getConsoleSender().sendMessage("§6[EarthCommon] - Plugin started!");
    }

    public void onDisable() {

    }

}
