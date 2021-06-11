package net.kunmc.lab.testserverutil;

import net.kunmc.lab.testserverutil.command.CommandHandler;
import org.bukkit.GameRule;
import org.bukkit.plugin.java.JavaPlugin;

public final class TestServerUtil extends JavaPlugin {
    private static TestServerUtil INSTANCE;

    public static TestServerUtil getInstance() {
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
        getServer().getPluginCommand("tsu").setExecutor(commandHandler);
        getServer().getPluginCommand("tsu").setTabCompleter(commandHandler);
    }

    @Override
    public void onDisable() {
    }
}
