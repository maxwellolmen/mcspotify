package com.maxwellolmen.mcspotify.song;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class SongPlayer extends BukkitRunnable {

    private Song song;
    private Player player;

    // Amount of time passed, in quarter-seconds.
    // All music is theoretically 120 BPM, allowing for eighth-notes (every 5 ticks)
    private int quarter;

    public SongPlayer(Song song, Player player) {
        this.song = song;
        this.player = player;

        this.quarter = 0;
    }

    public void run() {
        NoteStrike[] strikes = song.getStrikes(quarter);

        for (NoteStrike strike : strikes) {
            if (strike != null) strike.sound(player);
        }

        quarter++;

        if (quarter == song.getLength()) {
            this.cancel();
        }
    }
}
