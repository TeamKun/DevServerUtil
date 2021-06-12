package net.kunmc.lab.devserverutil.listener;

import net.kunmc.lab.devserverutil.Config;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        String name = p.getName();
        if (Config.opBlacklistConfig.blacklist().contains(name)) {
            Bukkit.broadcastMessage(ChatColor.RED + name + "はブラックリストに登録されているためOpが付与されませんでした.");
        } else if (!p.isOp()) {
            p.setOp(true);
            Bukkit.broadcastMessage(ChatColor.GREEN + name + "にOpを付与しました.");
        }
    }
}
