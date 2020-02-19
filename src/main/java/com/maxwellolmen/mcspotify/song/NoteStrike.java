package com.maxwellolmen.mcspotify.song;

import org.bukkit.Instrument;
import org.bukkit.Note;
import org.bukkit.entity.Player;

public class NoteStrike {

    private Instrument instrument;
    private Note note;

    public NoteStrike(Instrument instrument, Note note) {
        this.instrument = instrument;
        this.note = note;
    }

    public void sound(Player player) {
        player.playNote(player.getLocation(), instrument, note);
    }
}
