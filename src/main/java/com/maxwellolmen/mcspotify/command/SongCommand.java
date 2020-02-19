package com.maxwellolmen.mcspotify.command;

import com.maxwellolmen.mcspotify.MCSpotify;
import com.maxwellolmen.mcspotify.song.SongManager;
import com.maxwellolmen.mcspotify.ui.SongCreation;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SongCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("That command is for players only!");
            return true;
        }

        Player player = (Player) sender;

        if (args.length == 0) {
            player.sendMessage(ChatColor.GOLD + "Usage: /song <create/listen/delete> [name]");
            return true;
        }

        SongManager songManager = MCSpotify.getInstance().getSongManager();

        if (args[0].equalsIgnoreCase("create")) {
            if (args.length < 2) {
                player.sendMessage(ChatColor.RED + "Usage: /song create <name>");
                return true;
            }

            if (songManager.getSongByName(args[1]) != null) {
                player.sendMessage(ChatColor.RED + "There is already a song with that name.");
                return true;
            }

            SongCreation ui = new SongCreation(player, args[1]);
            songManager.addUI(player, ui);
        } else if (args[0].equalsIgnoreCase("listen")) {
            if (args.length < 2) {
                player.sendMessage(ChatColor.RED + "Usage: /song listen <name>");
                return true;
            }

            String name = args[1];
            boolean success = songManager.playSong(player, name);

            if (!success) {
                player.sendMessage(ChatColor.RED + "There is no song with that name.");
                return true;
            }
        }

        return true;
    }
}