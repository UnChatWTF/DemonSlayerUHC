package eu.unchat.uhc.demonslayer.speciality.blade;

import eu.unchat.uhc.profile.IProfile;
import org.bukkit.inventory.ItemStack;

import java.util.function.Consumer;

public interface IBlade {
    ItemStack getDisplay();

    Consumer<IProfile> apply();
}
