package eu.kurai.uhc.demonslayer;

import eu.kurai.uhc.demonslayer.speciality.blade.DSBladeHandler;
import eu.unchat.uhc.module.AbstractModule;
import eu.unchat.uhc.module.Module;
import org.bukkit.ChatColor;

@Module(
        name = "Demon Slayer",
        version = "0.0.1",
        authors = {"Kurai"},
        gameDesigners = "Kurai",
        developers = "Kurai",
        primaryColor = ChatColor.DARK_AQUA,
        secondaryColor = ChatColor.AQUA,
        glassColor = 9
)
public final class DS extends AbstractModule {
    @Override
    public void onRoles() {
        new DSBladeHandler();
    }
}
