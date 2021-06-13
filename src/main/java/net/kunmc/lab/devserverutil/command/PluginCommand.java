package net.kunmc.lab.devserverutil.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PluginCommand implements SubCommand {
    @Override
    public void run(CommandSender sender, String[] args) {
        if (args.length < 2) {
            sender.sendMessage(Message.pluginUsage);
            return;
        }

        PluginManager manager = Bukkit.getPluginManager();
        switch (args[0]) {
            case "enable":
                manager.enablePlugin(manager.getPlugin(args[1]));
                sender.sendMessage(ChatColor.GREEN + args[1] + "を有効化しました.");
                break;
            case "disable":
                manager.disablePlugin(manager.getPlugin(args[1]));
                sender.sendMessage(ChatColor.GREEN + args[1] + "を無効化しました.");
                break;
            default:
                sender.sendMessage(ChatColor.RED + args[0] + "不明なコマンドです.");
        }
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        if (args.length == 1) {
            return Stream.of("enable", "disable").filter(x -> x.startsWith(args[0])).collect(Collectors.toList());
        }

        if (args.length == 2) {
            switch (args[0]) {
                case "enable":
                    return Arrays.stream(Bukkit.getPluginManager().getPlugins())
                            .filter(x -> !x.isEnabled())
                            .map(Plugin::getName)
                            .filter(x -> x.startsWith(args[1]))
                            .collect(Collectors.toList());
                case "disable":
                    return Arrays.stream(Bukkit.getPluginManager().getPlugins())
                            .filter(Plugin::isEnabled)
                            .map(Plugin::getName)
                            .filter(x -> x.startsWith(args[1]))
                            .collect(Collectors.toList());
            }
        }

        return Collections.emptyList();
    }
}
