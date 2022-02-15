package br.com.earthpvp.bungee.commands;

import br.com.earthpvp.bungee.BungeeMain;
import br.com.earthpvp.bungee.commands.global.ReplyCommand;
import br.com.earthpvp.bungee.commands.global.TellCommand;
import br.com.earthpvp.bungee.commands.staff.*;

public class CommandManager {

    // Staff
    private final AnnouncementCommand announcementCommand = new AnnouncementCommand();
    private final AccountCommand accountCommand = new AccountCommand();
    private final BtpCommand btpCommand = new BtpCommand();
    private final FindCommand findCommand = new FindCommand();
    private final StaffChatCommand staffChatCommand = new StaffChatCommand();
    private final StaffCommand staffCommand = new StaffCommand();

    // Global
    private final ReplyCommand replyCommand = new ReplyCommand();
    private final TellCommand tellCommand = new TellCommand();

    public CommandManager(BungeeMain main) {
        // Staff
        main.getProxy().getPluginManager().registerCommand(main, announcementCommand);
        main.getProxy().getPluginManager().registerCommand(main, accountCommand);
        main.getProxy().getPluginManager().registerCommand(main, btpCommand);
        main.getProxy().getPluginManager().registerCommand(main, findCommand);
        main.getProxy().getPluginManager().registerCommand(main, staffChatCommand);
        main.getProxy().getPluginManager().registerCommand(main, staffCommand);


        // Global
        main.getProxy().getPluginManager().registerCommand(main, replyCommand);
        main.getProxy().getPluginManager().registerCommand(main, tellCommand);
    }

}
