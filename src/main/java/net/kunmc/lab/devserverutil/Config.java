package net.kunmc.lab.devserverutil;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class Config {
    public static JavaPlugin plugin;
    public static FileConfiguration file;
    public static OpBlacklistConfig opBlacklistConfig = new OpBlacklistConfig();

    public static class OpBlacklistConfig {
        private final List<String> blacklist = new ArrayList<>();

        public List<String> blacklist() {
            return this.blacklist;
        }

        public void add(String name) {
            blacklist.add(name);
            file.set("opBlacklist", blacklist);
            plugin.saveConfig();
        }

        public boolean remove(String name) {
            boolean isContained = blacklist.remove(name);
            file.set("opBlacklist", blacklist);
            plugin.saveConfig();
            return isContained;
        }
    }
}
