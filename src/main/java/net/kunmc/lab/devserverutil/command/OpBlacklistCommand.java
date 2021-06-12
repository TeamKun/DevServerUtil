package net.kunmc.lab.devserverutil.command;

import net.kunmc.lab.devserverutil.Config;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OpBlacklistCommand implements SubCommand {
    @Override
    public void run(CommandSender sender, String[] args) {
        if (args.length < 2) {
            sender.sendMessage(Message.opBlacklistUsage);
            return;
        }

        switch (args[0]) {
            case "add":
                Config.opBlacklistConfig.add(args[1]);
                sender.sendMessage(ChatColor.GREEN + args[1] + "がブラックリストに追加されました.");
                break;
            case "remove":
                if (Config.opBlacklistConfig.remove(args[1])) {
                    sender.sendMessage(ChatColor.GREEN + args[1] + "がブラックリストから削除されました.");
                } else {
                    sender.sendMessage(ChatColor.RED + args[1] + "はブラックリストに登録されていません.");
                }
                break;
            default:
                sender.sendMessage(ChatColor.RED + "不明なコマンドです.");
        }
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        if (args.length == 1) {
            return Stream.of("add", "remove").filter(x -> x.startsWith(args[0])).collect(Collectors.toList());
        }

        if (args.length == 2) {
            return Bukkit.getOnlinePlayers().stream()
                    .map(Player::getName)
                    .filter(x -> {
                        if (args[0].equals("add")) {
                            return !Config.opBlacklistConfig.blacklist().contains(x);
                        } else {
                            return Config.opBlacklistConfig.blacklist().contains(x);
                        }
                    })
                    .filter(x -> x.startsWith(args[1])).collect(Collectors.toList());
        }

        return Collections.emptyList();
    }
}
