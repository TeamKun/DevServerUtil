package net.kunmc.lab.testserverutil.command;

import org.bukkit.command.CommandSender;

import java.util.List;

public class OPCommand implements SubCommand {
    @Override
    public void run(CommandSender sender, String[] args) {

    }

    @Override
    public List<String> tabComplete() {
        return null;
    }
}
