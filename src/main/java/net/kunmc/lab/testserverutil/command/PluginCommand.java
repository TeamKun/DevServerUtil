package net.kunmc.lab.testserverutil.command;

import org.bukkit.command.CommandSender;

import java.util.List;

public class PluginCommand implements SubCommand {
    @Override
    public void run(CommandSender sender, String[] args) {

    }

    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        return null;
    }
}
