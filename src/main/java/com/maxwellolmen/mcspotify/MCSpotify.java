package com.maxwellolmen.mcspotify;

import com.maxwellolmen.mcspotify.command.SongCommand;
import com.maxwellolmen.mcspotify.song.SongManager;
import com.maxwellolmen.mcspotify.ui.SongCreation;
import org.bukkit.plugin.java.JavaPlugin;

public class MCSpotify extends JavaPlugin {

    private static MCSpotify plugin;

    private SongManager songManager;

    @Override
    public void onEnable() {
        plugin = this;

        registerCommands();

        SongCreation.init();
        songManager = new SongManager();

        getServer().getPluginManager().registerEvents(songManager, this);
    }

    public void registerCommands() {
        getCommand("song").setExecutor(new SongCommand());
    }

    public SongManager getSongManager() {
        return songManager;
    }

    public static MCSpotify getInstance() {
        return plugin;
    }
}
