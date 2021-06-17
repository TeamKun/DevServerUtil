package net.kunmc.lab.devserverutil.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FlySpeedCommand implements SubCommand {
    @Override
    public void run(CommandSender sender, String[] args) {
        if (args.length < 2) {
            sender.sendMessage(Message.flySpeedUsage);
            return;
        }

        Player p = Bukkit.getPlayer(args[0]);
        if (p == null) {
            sender.sendMessage(ChatColor.RED + args[0] + "はオンラインではありません.");
            return;
        }

        float speed;
        try {
            speed = Float.parseFloat(args[1]);
        } catch (NumberFormatException e) {
            sender.sendMessage(ChatColor.RED + "<speed>には-1から1の範囲で実数を入力してください.");
            return;
        }

        if (speed < -1 || speed > 1) {
            sender.sendMessage(ChatColor.RED + "<speed>には-1から1の範囲で実数を入力してください.");
            return;
        }

        p.setFlySpeed(speed);
        sender.sendMessage(ChatColor.GREEN + p.getName() + "のflySpeedを" + speed + "に設定しました.");
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        if (args.length == 1) {
            return Stream.concat(Bukkit.getOnlinePlayers().stream().map(Player::getName), Stream.of("@a", "@r"))
                    .filter(x -> x.startsWith(args[0]))
                    .collect(Collectors.toList());
        }

        if (args.length == 2) {
            return Stream.of("<speed>").filter(x -> x.startsWith(args[1])).collect(Collectors.toList());
        }

        return Collections.emptyList();
    }
}
