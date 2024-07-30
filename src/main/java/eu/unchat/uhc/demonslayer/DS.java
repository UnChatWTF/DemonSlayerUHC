package eu.unchat.uhc.demonslayer;

import eu.unchat.uhc.demonslayer.speciality.blade.DSBladeHandler;
import eu.unchat.uhc.module.AbstractModule;
import eu.unchat.uhc.module.Module;
import org.bukkit.ChatColor;

@Module(
        name = "Demon Slayer",
        version = "0.0.1",
        authors = {"UnChat_"},
        gameDesigners = "UnChat_",
        developers = "UnChat_",
        primaryColor = ChatColor.DARK_GREEN,
        secondaryColor = ChatColor.DARK_RED
)
public final class DS extends AbstractModule {
    @Override
    public void onRoles() {
        new DSBladeHandler();
    }
}
