package br.com.earthpvp.spigot.nametag;

import br.com.earthpvp.core.spigot.LuckPermsSpigot;
import br.com.earthpvp.spigot.SpigotMain;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class TagTeam extends LuckPermsSpigot {

    public static void update(Player player) {
        try {
            Scoreboard scoreboard = (player.getScoreboard() != null) ? player.getScoreboard() : Bukkit.getScoreboardManager().getNewScoreboard();
            for (Player players : Bukkit.getOnlinePlayers()) {
                String uuid = Bukkit.getPlayer(player.getName().toLowerCase()).getUniqueId().toString().replace("-", "").substring(0, 15);
                Team team;

                if (isPlayerInGroup(players, "ADM")) {
                    team = scoreboard.getTeam("a" + uuid);
                    if (team == null) {
                        team = scoreboard.registerNewTeam("a" + uuid);
                        team.setPrefix(getPrefix(players));
                    }
                } else if (isPlayerInGroup(players, "GER")) {
                    team = scoreboard.getTeam("b" + uuid);
                    if (team == null) {
                        team = scoreboard.registerNewTeam("b" + uuid);
                        team.setPrefix(getPrefix(players));
                    }
                } else if (isPlayerInGroup(players, "DEV")) {
                    team = scoreboard.getTeam("c" + uuid);
                    if (team == null) {
                        team = scoreboard.registerNewTeam("c" + uuid);
                        team.setPrefix(getPrefix(players));
                    }
                } else if (isPlayerInGroup(players, "MOD")) {
                    team = scoreboard.getTeam("d" + uuid);
                    if (team == null) {
                        team = scoreboard.registerNewTeam("d" + uuid);
                        team.setPrefix(getPrefix(players));
                    }
                } else if (isPlayerInGroup(players, "AJD")) {
                    team = scoreboard.getTeam("e" + uuid);
                    if (team == null) {
                        team = scoreboard.registerNewTeam("e" + uuid);
                        team.setPrefix(getPrefix(players));
                    }
                } else if (isPlayerInGroup(players, "YT")) {
                    team = scoreboard.getTeam("f" + uuid);
                    if (team == null) {
                        team = scoreboard.registerNewTeam("f" + uuid);
                        team.setPrefix(getPrefix(players));
                    }
                } else {
                    team = scoreboard.getTeam("z" + uuid);
                    if (team == null) {
                        team = scoreboard.registerNewTeam("z" + uuid);
                        team.setPrefix("§7");
                    }
                }
                team.addPlayer(player);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static void run() {
        new BukkitRunnable() {
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    TagTeam.update(player);
                }
            }
        }.runTaskTimer(SpigotMain.getInstance(), 0L, 10L);
    }
}
