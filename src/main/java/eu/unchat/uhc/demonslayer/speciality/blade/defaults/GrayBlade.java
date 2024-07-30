package eu.unchat.uhc.demonslayer.speciality.blade.defaults;

import eu.unchat.uhc.demonslayer.speciality.blade.IBlade;
import eu.unchat.uhc.profile.IProfile;
import eu.unchat.uhc.util.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.function.Consumer;

public final class GrayBlade implements IBlade {
    @Override
    public ItemStack getDisplay() {
        return new ItemBuilder(Material.INK_SACK)
                .name("&7&lLame grise")
                .lore("&rVous octroie un bonus de &l10% &rde &7&lRésistance&r.")
                .asItemStack();
    }

    @Override
    public Consumer<IProfile> apply() {
        return (profile -> {
            profile.setResistanceBuffer(profile.getResistanceBuffer() + 10);
        });
    }
}
