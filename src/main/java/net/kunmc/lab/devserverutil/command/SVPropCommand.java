package net.kunmc.lab.devserverutil.command;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class SVPropCommand implements SubCommand {
    private final Path propsFile = Paths.get("./server.properties");

    @Override
    public void run(CommandSender sender, String[] args) {
        if (args.length < 2) {
            sender.sendMessage(Message.SVPropUsage);
            return;
        }

        try {
            List<String> propList = Files.readAllLines(propsFile);
            int lineOffset = 0;
            for (String prop : propList) {
                if (prop.split("=")[0].equals(args[0])) {
                    break;
                }
                lineOffset++;
            }

            if (lineOffset >= propList.size()) {
                propList.add(args[0] + "=" + args[1]);
            } else {
                propList.set(lineOffset, args[0] + "=" + args[1]);
            }

            Files.write(propsFile, propList, StandardOpenOption.WRITE);
        } catch (IOException e) {
            e.printStackTrace();
            sender.sendMessage(ChatColor.RED + "実行中にエラーが発生しました.");
            return;
        }

        sender.sendMessage(ChatColor.GREEN + args[0] + "の値を" + args[1] + "に変更しました.\n適用にはサーバーの再起動が必要です.");
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        if (args.length == 1) {
            try {
                return Files.readAllLines(propsFile).stream()
                        .filter(x -> !x.startsWith("#"))
                        .map(x -> x.split("=")[0])
                        .collect(Collectors.toList());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (args.length == 2) {
            return Collections.singletonList("<value>");
        }

        return Collections.emptyList();
    }
}
