package net.kunmc.lab.devserverutil.command;

import net.kunmc.lab.devserverutil.DevServerUtil;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class SVPropCommand implements SubCommand {
    JavaPlugin plugin = DevServerUtil.getInstance();

    @Override
    public void run(CommandSender sender, String[] args) {
        
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        return null;
    }
}
