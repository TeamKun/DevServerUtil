package net.kunmc.lab.testserverutil.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.stream.Collectors;

public class CommandHandler implements CommandExecutor, TabCompleter {
    private final Map<String, SubCommand> subCmdMap = new HashMap<String, SubCommand>() {{
       put("plugin", new PluginCommand());
       put("op", new OPCommand());
       put("serverProperties", new SVPropCommand());
    }};

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length < 1) {
            return false;
        }

        String[] nextArgs = Arrays.copyOfRange(args, 1, args.length);
        if (subCmdMap.containsKey(args[0])) {
            subCmdMap.get(args[0]).run(sender, nextArgs);
        } else {
            sender.sendMessage(ChatColor.RED+"不明なコマンドです.");
        }

        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        if (args.length == 1) {
            return subCmdMap.keySet().stream().filter(x -> x.startsWith(args[0])).collect(Collectors.toList());
        }

        if (args.length == 2 && subCmdMap.containsKey(args[0])) {
            return subCmdMap.get(args[0]).tabComplete();
        }

        return Collections.emptyList();
    }
}
