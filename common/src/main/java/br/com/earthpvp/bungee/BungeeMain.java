package br.com.earthpvp.bungee;

import br.com.earthpvp.bungee.commands.CommandManager;
import br.com.earthpvp.bungee.listeners.ListenerManager;
import br.com.earthpvp.bungee.listeners.global.HeaderListener;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.md_5.bungee.api.plugin.Plugin;

import java.util.concurrent.TimeUnit;

public class BungeeMain extends Plugin {

    private static BungeeMain instance;
    private static LuckPerms luckPerms;

    public static BungeeMain getInstance() {
        return instance;
    }

    public static LuckPerms getLuckPerms() {
        return luckPerms;
    }

    public void onEnable() {
        instance = this;
        luckPerms = LuckPermsProvider.get();

        new CommandManager(this);
        new ListenerManager(this);

        HeaderListener headerListener = new HeaderListener();
        this.getProxy().getPluginManager().registerListener(this, new HeaderListener());
        this.getProxy().getScheduler().schedule(this, headerListener::run, 0L, 5L, TimeUnit.SECONDS);

        getProxy().getConsole().sendMessage("§6[EarthCommon] - Plugin started!");

    }

    public void onDisable() {

    }
}
