package br.com.earthpvp.core.spigot;

import net.luckperms.api.LuckPerms;
import net.luckperms.api.cacheddata.CachedMetaData;
import net.luckperms.api.model.user.User;
import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;

public class LuckPermsSpigot {

    public static User loadUser(Player player) {
        if (!player.isOnline()) {
            throw new IllegalStateException("Player is offline!");
        }
        return getApi().getUserManager().getUser(player.getUniqueId());
    }

    public static CachedMetaData metaData(Player player) {
        return loadUser(player).getCachedData().getMetaData(getApi().getContextManager().getQueryOptions(player));
    }

    public static LuckPerms getApi() {
        RegisteredServiceProvider<LuckPerms> provider = (RegisteredServiceProvider<LuckPerms>) Bukkit.getServer().getServicesManager().getRegistration((Class) LuckPerms.class);
        Validate.notNull(provider);
        return provider.getProvider();
    }

    public static String getPrefix(Player player) {
        String prefix = metaData(player).getPrefix();
        return (prefix != null) ? prefix + " " : "§7";
    }

    public static String getFancyPrefix(Player player) {
        String prefix = metaData(player).getPrefix();
        return (prefix != null) ? getColor(player) + prefix.split("\\[")[1].split("]")[0] : "§7Membro";
    }

    public static String getColor(Player player) {
        String color = getPrefix(player);
        return ChatColor.getLastColors(color);
    }

    public static boolean getPrimaryGroup(Player player, String group) {
        boolean primaryGroup = getApi().getUserManager().getUser(player.getUniqueId()).getPrimaryGroup().equalsIgnoreCase(group);
        return primaryGroup;
    }

    public static boolean isPlayerInGroup(Player player, String group) {
        return player.hasPermission("group." + group);
    }

    public static String groupWithName(Player player) {
        return getPrefix(player) + player.getName();
    }

}
