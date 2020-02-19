package com.maxwellolmen.mcspotify.ui;

import com.maxwellolmen.mcspotify.MCSpotify;
import com.maxwellolmen.mcspotify.song.NoteStrike;
import com.maxwellolmen.mcspotify.song.Song;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.Map;

public class SongCreation {

    public static void init() {
        REST = new ItemStack(Material.WHITE_STAINED_GLASS_PANE);
        KICK = new ItemStack(Material.STONE);
        SNARE = new ItemStack(Material.SAND);
        BASS = new ItemStack(Material.OAK_LOG);
        GUITAR = new ItemStack(Material.WHITE_WOOL);
        PIANO = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        FLUTE = new ItemStack(Material.CLAY);

        setDisplayName(REST, ChatColor.GOLD + "Rest");
        setDisplayName(KICK, ChatColor.GOLD + "Kick");
        setDisplayName(SNARE, ChatColor.GOLD + "Snare");
        setDisplayName(BASS, ChatColor.GOLD + "Bass");
        setDisplayName(GUITAR, ChatColor.GOLD + "Guitar");
        setDisplayName(PIANO, ChatColor.GOLD + "Piano");
        setDisplayName(FLUTE, ChatColor.GOLD + "Flute");
    }

    public static void setDisplayName(ItemStack item, String name) {
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        item.setItemMeta(meta);
    }

    private static ItemStack REST, KICK, SNARE, BASS, GUITAR, PIANO, FLUTE;

    private Player player;

    private boolean phase;

    private Inventory inventory;

    private String name;

    public SongCreation(Player player, String name) {
        this.player = player;
        this.phase = false;

        inventory = Bukkit.createInventory(null, 54, "Song Creation - Select Instruments");

        this.name = name;

        for (int i = 0; i < 54; i++) {
            inventory.setItem(i, REST);
        }

        player.openInventory(inventory);
    }

    public void phaseTwo() {
        phase = true;

        for (int i = 0; i < 54; i++) {
            renameItem(i, "Gb1");
        }

        System.out.println("TWO");
    }

    public void renameItem(int slot, String tone) {
        ItemStack item = inventory.getItem(slot);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GOLD + tone);
        item.setItemMeta(meta);
    }

    public void click(InventoryClickEvent event) {
        System.out.println("bruh");
        if (event.getClickedInventory() != inventory) {
            phaseTwo();
        }

        event.setCancelled(true);

        System.out.println("bruh1");

        if (event.getCurrentItem() == null) {
            return;
        }

        System.out.println(getPhase());

        if (getPhase()) {
            clickPhaseTwo(event);
        } else {
            clickPhaseOne(event);
            System.out.println("bruh2");
        }
    }

    public void clickPhaseOne(InventoryClickEvent event) {
        ItemStack item = event.getCurrentItem();
        int slot = event.getSlot();
        ItemStack newItem;

        switch (item.getType()) {
            case WHITE_STAINED_GLASS_PANE:
                newItem = KICK;
                break;
            case STONE:
                newItem = SNARE;
                break;
            case SAND:
                newItem = BASS;
                break;
            case OAK_LOG:
                newItem = GUITAR;
                break;
            case WHITE_WOOL:
                newItem = PIANO;
                break;
            case BLACK_STAINED_GLASS_PANE:
                newItem = FLUTE;
                break;
            case CLAY:
                newItem = REST;
                break;
            default:
                return;
        }

        inventory.setItem(slot, newItem);
    }

    public void clickPhaseTwo(InventoryClickEvent event) {
        ItemStack item = event.getCurrentItem();
        int slot = event.getSlot();
        ItemStack newItem;

        String strippedName = ChatColor.stripColor(item.getItemMeta().getDisplayName());

        if (strippedName.equals("Gb1")) {
            renameItem(slot, "G1");
        } else if (strippedName.equals("G1")) {
            renameItem(slot, "Ab1");
        } else if (strippedName.equals("Ab1")) {
            renameItem(slot, "A1");
        } else if (strippedName.equals("A1")) {
            renameItem(slot, "Bb1");
        } else if (strippedName.equals("Bb1")) {
            renameItem(slot, "B1");
        } else if (strippedName.equals("B1")) {
            renameItem(slot, "C1");
        } else if (strippedName.equals("C1")) {
            renameItem(slot, "Db1");
        } else if (strippedName.equals("Db1")) {
            renameItem(slot, "D1");
        } else if (strippedName.equals("D1")) {
            renameItem(slot, "Eb1");
        } else if (strippedName.equals("Eb1")) {
            renameItem(slot, "E1");
        } else if (strippedName.equals("E1")) {
            renameItem(slot, "F1");
        } else if (strippedName.equals("F1")) {
            renameItem(slot, "Gb2");
        } else if (strippedName.equals("Gb2")) {
            renameItem(slot, "G2");
        } else if (strippedName.equals("G2")) {
            renameItem(slot, "Ab2");
        } else if (strippedName.equals("Ab2")) {
            renameItem(slot, "A2");
        } else if (strippedName.equals("A2")) {
            renameItem(slot, "Bb2");
        } else if (strippedName.equals("Bb2")) {
            renameItem(slot, "B2");
        } else if (strippedName.equals("B2")) {
            renameItem(slot, "C2");
        } else if (strippedName.equals("C2")) {
            renameItem(slot, "Db2");
        } else if (strippedName.equals("Db2")) {
            renameItem(slot, "D2");
        } else if (strippedName.equals("D2")) {
            renameItem(slot, "Eb2");
        } else if (strippedName.equals("Eb2")) {
            renameItem(slot, "E2");
        } else if (strippedName.equals("E2")) {
            renameItem(slot, "F2");
        } else if (strippedName.equals("F2")) {
            renameItem(slot, "Gb3");
        } else if (strippedName.equals("Gb3")) {
            renameItem(slot, "Gb1");
        }
    }

    public boolean create() {
        boolean success = false;
        for (int i = 0; i < 54; i++) {
            if (inventory.getItem(i).getType() != Material.WHITE_STAINED_GLASS_PANE) {
                success = true;
                break;
            }
        }

        if (!success) {
            return false;
        }

        Map<Integer, NoteStrike[]> strikeMap = new HashMap<>();

        for (int i = 0; i < 9; i++) {
            NoteStrike[] strikes = new NoteStrike[6];

            for (int j = 0; j < 6; j++) {
                ItemStack item = inventory.getItem(j * 9 + i);

                if (item == null || item.getItemMeta() == null) {
                    continue;
                }

                Instrument instrument = getInstrument(item);
                Note note = getNote(item);

                if (instrument == null || note == null) {
                    continue;
                }

                strikes[j] = new NoteStrike(instrument, note);
            }

            strikeMap.put(i, strikes);
        }

        MCSpotify.getInstance().getSongManager().addSong(new Song(name, strikeMap));

        return true;
    }

    public Instrument getInstrument(ItemStack item) {
        switch (item.getType()) {
            case STONE:
                return Instrument.BASS_DRUM;
            case SAND:
                return Instrument.SNARE_DRUM;
            case OAK_LOG:
                return Instrument.BASS_GUITAR;
            case WHITE_WOOL:
                return Instrument.GUITAR;
            case BLACK_STAINED_GLASS_PANE:
                return Instrument.PIANO;
            case CLAY:
                return Instrument.FLUTE;
            default:
                return null;
        }
    }

    public Note getNote(ItemStack item) {
        String strippedName = ChatColor.stripColor(item.getItemMeta().getDisplayName());

        if (strippedName.equals("Gb1")) {
            return new Note(1, Note.Tone.F, true);
        } else if (strippedName.equals("G1")) {
            return new Note(1, Note.Tone.G, false);
        } else if (strippedName.equals("Ab1")) {
            return new Note(1, Note.Tone.G, true);
        } else if (strippedName.equals("A1")) {
            return new Note(1, Note.Tone.A, false);
        } else if (strippedName.equals("Bb1")) {
            return new Note(1, Note.Tone.A, true);
        } else if (strippedName.equals("B1")) {
            return new Note(1, Note.Tone.B, false);
        } else if (strippedName.equals("C1")) {
            return new Note(1, Note.Tone.C, false);
        } else if (strippedName.equals("Db1")) {
            return new Note(1, Note.Tone.C, true);
        } else if (strippedName.equals("D1")) {
            return new Note(1, Note.Tone.D, false);
        } else if (strippedName.equals("Eb1")) {
            return new Note(1, Note.Tone.D, true);
        } else if (strippedName.equals("E1")) {
            return new Note(1, Note.Tone.E, false);
        } else if (strippedName.equals("F1")) {
            return new Note(1, Note.Tone.F, false);
        } else if (strippedName.equals("Gb2")) {
            return new Note(1, Note.Tone.F, true);
        } else if (strippedName.equals("G2")) {
            return new Note(1, Note.Tone.G, false);
        } else if (strippedName.equals("Ab2")) {
            return new Note(1, Note.Tone.G, true);
        } else if (strippedName.equals("A2")) {
            return new Note(1, Note.Tone.A, false);
        } else if (strippedName.equals("Bb2")) {
            return new Note(1, Note.Tone.A, true);
        } else if (strippedName.equals("B2")) {
            return new Note(1, Note.Tone.B, false);
        } else if (strippedName.equals("C2")) {
            return new Note(1, Note.Tone.C, false);
        } else if (strippedName.equals("Db2")) {
            return new Note(1, Note.Tone.C, true);
        } else if (strippedName.equals("D2")) {
            return new Note(1, Note.Tone.D, false);
        } else if (strippedName.equals("Eb2")) {
            return new Note(1, Note.Tone.D, true);
        } else if (strippedName.equals("E2")) {
            return new Note(1, Note.Tone.E, false);
        } else if (strippedName.equals("F2")) {
            return new Note(1, Note.Tone.F, false);
        } else if (strippedName.equals("Gb3")) {
            return new Note(1, Note.Tone.F, true);
        } else {
            return null;
        }
    }

    public boolean getPhase() {
        return phase;
    }

    /*public boolean getPhase() {
        if (inventory.getItem(0) == null || inventory.getItem(0).getItemMeta() == null) {
            return false;
        }

        String stripped = ChatColor.stripColor(inventory.getItem(0).getItemMeta().getDisplayName()).toLowerCase();

        if ((stripped.startsWith("g") && !stripped.startsWith("gu"))
                    || stripped.startsWith("a")
                    || (stripped.startsWith("b") && !stripped.startsWith("ba")
                    || stripped.startsWith("c"))
                    || stripped.startsWith("d")
                    || stripped.startsWith("e")
                    || (stripped.startsWith("f") && !stripped.startsWith("fl"))) {
            return true;
        }

        return false;
    }*/
}
