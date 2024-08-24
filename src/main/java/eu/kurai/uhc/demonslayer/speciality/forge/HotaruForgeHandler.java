package eu.kurai.uhc.demonslayer.speciality.forge;

import eu.unchat.uhc.API;
import eu.unchat.uhc.util.CC;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.io.File;

public final class HotaruForgeHandler {
    private final File schematicFile;

    public HotaruForgeHandler() {
        this.schematicFile = new File(API.get().getPlugin().getDataFolder(), "schematics/hotaru.schematic");
    }

    public void place(final Location location) {
        Bukkit.broadcastMessage(CC.info("La forge d'Hotaru vient d'apparaître !"));
    }
}
