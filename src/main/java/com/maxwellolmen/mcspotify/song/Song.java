package com.maxwellolmen.mcspotify.song;

import com.maxwellolmen.mcspotify.MCSpotify;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.Map;

public class Song {

    private String name;
    private Map<Integer, NoteStrike[]> strikeMap;

    public Song(String name, Map<Integer, NoteStrike[]> strikeMap) {
        this.name = name;
        this.strikeMap = strikeMap;
    }

    public void play(Player player) {
        SongPlayer songPlayer = new SongPlayer(this, player);
        songPlayer.runTaskTimer(MCSpotify.getInstance(), 0, 5);

        player.sendMessage(ChatColor.AQUA + "Now Playing: " + ChatColor.GOLD + name);
    }

    public NoteStrike[] getStrikes(int quarter) {
        return strikeMap.get(quarter);
    }

    public int getLength() {
        return strikeMap.size();
    }

    public String getName() {
        return name;
    }
}