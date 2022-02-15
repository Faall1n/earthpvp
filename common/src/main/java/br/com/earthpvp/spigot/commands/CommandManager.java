package br.com.earthpvp.spigot.commands;

import br.com.earthpvp.spigot.commands.staff.GamemodeCommand;
import br.com.earthpvp.spigot.commands.staff.TeleportCommand;

public class CommandManager {

    public CommandManager() {
        new GamemodeCommand();
        new TeleportCommand();
    }

}
