package br.com.earthpvp.core.bungee;

import br.com.earthpvp.bungee.BungeeMain;
import net.luckperms.api.cacheddata.CachedMetaData;
import net.luckperms.api.model.user.User;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class LuckPermsBungee extends BungeeMain {

    public static User loadUser(ProxiedPlayer proxiedPlayer) {
        if (!proxiedPlayer.isConnected()) {
            throw new IllegalStateException("Player is offline!");
        }
        return getLuckPerms().getUserManager().getUser(proxiedPlayer.getUniqueId());
    }

    public static CachedMetaData metaData(ProxiedPlayer proxiedPlayer) {
        return loadUser(proxiedPlayer).getCachedData().getMetaData(getLuckPerms().getContextManager().getQueryOptions(proxiedPlayer));
    }

    public static String getPrefix(ProxiedPlayer proxiedPlayer) {
        String prefix = metaData(proxiedPlayer).getPrefix();
        return (prefix != null) ? prefix + " " : "§7";
    }

    public static String getFancyPrefix(ProxiedPlayer proxiedPlayer) {
        String prefix = metaData(proxiedPlayer).getPrefix();
        return (prefix != null) ? getColor(proxiedPlayer) + prefix.split("\\[")[1].split("]")[0] : "§7Membro";
    }

    public static String getColor(ProxiedPlayer proxiedPlayer) {
        String color = getPrefix(proxiedPlayer);
        return color.split("\\[")[0];
    }

    public static boolean getPrimaryGroup(ProxiedPlayer proxiedPlayer, String group) {
        boolean primaryGroup = getLuckPerms().getUserManager().getUser(proxiedPlayer.getUniqueId()).getPrimaryGroup().equalsIgnoreCase(group);
        return primaryGroup;
    }

    public static boolean isPlayerInGroup(ProxiedPlayer proxiedPlayer, String group) {
        return proxiedPlayer.hasPermission("group." + group);
    }

    public static String groupWithName(ProxiedPlayer proxiedPlayer) {
        return getPrefix(proxiedPlayer) + proxiedPlayer.getName();
    }

}
