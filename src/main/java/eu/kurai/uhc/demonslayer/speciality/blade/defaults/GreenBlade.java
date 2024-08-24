package eu.kurai.uhc.demonslayer.speciality.blade.defaults;

import eu.kurai.uhc.demonslayer.speciality.blade.IBlade;
import eu.unchat.uhc.profile.IProfile;
import eu.unchat.uhc.util.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.function.Consumer;

public final class GreenBlade implements IBlade {
    @Override
    public ItemStack getDisplay() {
        return new ItemBuilder(Material.INK_SACK)
                .name("&2&lLame verte")
                .lore("&7Vous octroie un bonus de &e&l10% &7de &e&lSpeed&r.")
                .asItemStack();
    }

    @Override
    public Consumer<IProfile> apply() {
        return (profile -> {
            profile.setSpeedBuffer(profile.getSpeedBuffer() + 0.02F);
        });
    }
}
