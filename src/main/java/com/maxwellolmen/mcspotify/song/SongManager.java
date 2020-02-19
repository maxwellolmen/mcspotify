package com.maxwellolmen.mcspotify.song;

import com.maxwellolmen.mcspotify.ui.SongCreation;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

import java.util.*;

public class SongManager implements Listener {

    private List<Song> songs;
    private Map<UUID, SongCreation> uis;

    public SongManager() {
        songs = new ArrayList<>();
        uis = new HashMap<>();
    }

    public void addSong(Song song) {
        songs.add(song);
    }

    public Song getSongByName(String name) {
        for (Song song : songs) {
            if (song.getName().equalsIgnoreCase(name)) {
                return song;
            }
        }

        return null;
    }

    public boolean playSong(Player player, String name) {
        Song song = getSongByName(name);

        if (song == null) {
            return false;
        }

        song.play(player);
        return true;
    }

    public void addUI(Player player, SongCreation ui) {
        if (uis.containsKey(player.getUniqueId())) {
            player.closeInventory();
            uis.remove(player.getUniqueId());
        }

        uis.put(player.getUniqueId(), ui);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        System.out.println("WHATS?");
        if (uis.containsKey(event.getWhoClicked().getUniqueId())) {
            System.out.println("haha");
            SongCreation ui = uis.get(event.getWhoClicked().getUniqueId());
            ui.click(event);
        }
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        if (uis.containsKey(event.getPlayer().getUniqueId())) {
            SongCreation ui = uis.get(event.getPlayer().getUniqueId());

            if (ui.getPhase()) {
                ui.create();
                uis.remove(event.getPlayer().getUniqueId());
                event.getPlayer().sendMessage(ChatColor.GOLD + "Created song!");
            } else {
                uis.remove(event.getPlayer().getUniqueId());
                event.getPlayer().sendMessage(ChatColor.RED + "Ended song creation process.");
            }
        }
    }
}
