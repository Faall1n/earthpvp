package br.com.earthpvp.core.spigot;

import org.bukkit.Bukkit;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public abstract class SpigotCommand implements CommandExecutor, TabExecutor {

    protected static CommandMap cmap;
    protected final String command;
    protected final String description;
    protected final List<String> alias;
    protected final String usage;
    protected final String permMessage;

    public SpigotCommand(String command) {
        this(command, null, null, null, null);
    }

    public SpigotCommand(String command, String usage) {
        this(command, usage, null, null, null);
    }

    public SpigotCommand(String command, String usage, String description) {
        this(command, usage, description, null, null);
    }

    public SpigotCommand(String command, String usage, String description, String permissionMessage) {
        this(command, usage, description, permissionMessage, null);
    }

    public SpigotCommand(String command, String usage, String description, List<String> aliases) {
        this(command, usage, description, null, aliases);
    }

    public SpigotCommand(String command, String usage, String description, String permissionMessage, List<String> aliases) {
        this.command = command.toLowerCase();
        this.usage = usage;
        this.description = description;
        this.permMessage = permissionMessage;
        this.alias = aliases;
    }

    public void register() {
        ReflectCommand cmd = new ReflectCommand(this.command);
        if (this.alias != null) cmd.setAliases(this.alias);
        if (this.description != null) cmd.setDescription(this.description);
        if (this.usage != null) cmd.setUsage(this.usage);
        if (this.permMessage != null) cmd.setPermissionMessage(this.permMessage);
        getCommandMap().register("", cmd);
        cmd.setExecutor(this);
    }

    final CommandMap getCommandMap() {
        if (cmap == null) {
            try {
                final Field f = Bukkit.getServer().getClass().getDeclaredField("commandMap");
                f.setAccessible(true);
                cmap = (CommandMap) f.get(Bukkit.getServer());
                return getCommandMap();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (cmap != null) {
            return cmap;
        }
        return getCommandMap();
    }

    public abstract boolean onCommand(CommandSender sender, Command cmd, String label, String[] args);

    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        List<String> nonOps = new ArrayList<String>();
        List<String> result = new ArrayList<String>();
        for (Player player : Bukkit.getOnlinePlayers()) {
            nonOps.add(player.getName());
        }
        for (String name : nonOps) {
            if (name.toLowerCase().startsWith(args[args.length - 1].toLowerCase()))
                result.add(name);
        }
        return result;
    }

    private final class ReflectCommand extends Command {
        private SpigotCommand exe = null;

        protected ReflectCommand(String command) {
            super(command);
        }

        public void setExecutor(SpigotCommand exe) {
            this.exe = exe;
        }

        @Override
        public boolean execute(CommandSender sender, String commandLabel, String[] args) {
            if (exe != null) {
                return exe.onCommand(sender, this, commandLabel, args);
            }
            return false;
        }

        @Override
        public List<String> tabComplete(CommandSender sender, String alais, String[] args) {
            if (exe != null) {
                return exe.onTabComplete(sender, this, alais, args);
            }
            return null;
        }
    }
}
