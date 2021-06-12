package net.kunmc.lab.devserverutil;

import net.kunmc.lab.devserverutil.command.CommandHandler;
import net.kunmc.lab.devserverutil.listener.PlayerJoinListener;
import org.bukkit.GameRule;
import org.bukkit.plugin.java.JavaPlugin;

public final class DevServerUtil extends JavaPlugin {
    private static DevServerUtil INSTANCE;

    public static DevServerUtil getInstance() {
        return INSTANCE;
    }

    @Override
    public void onEnable() {
        INSTANCE = this;

        getServer().getWorlds().forEach(x -> {
            x.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
            x.setGameRule(GameRule.DO_WEATHER_CYCLE, false);
            x.setTime(1000);
            x.setClearWeatherDuration(1 << 30);
        });

        CommandHandler commandHandler = new CommandHandler();
        getServer().getPluginCommand("dsu").setExecutor(commandHandler);
        getServer().getPluginCommand("dsu").setTabCompleter(commandHandler);

        saveDefaultConfig();
        Config.plugin = this;
        Config.file = getConfig();
        Config.opBlacklistConfig.blacklist().addAll(getConfig().getStringList("opBlacklist"));

        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
    }

    @Override
    public void onDisable() {
    }
}
