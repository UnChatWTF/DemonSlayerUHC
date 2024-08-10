package eu.unchat.uhc.demonslayer.speciality.blade.defaults;

import eu.unchat.uhc.demonslayer.speciality.blade.IBlade;
import eu.unchat.uhc.profile.IProfile;
import eu.unchat.uhc.util.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.function.Consumer;

public final class OrangeBlade implements IBlade {
    @Override
    public ItemStack getDisplay() {
        return new ItemBuilder(Material.INK_SACK)
                .name("&6&lLame orange")
                .lore("&7Vous octroie une réduction de &6&l50% &7des dégâts de &6&lfeu&r.")
                .asItemStack();
    }

    @Override
    public Consumer<IProfile> apply() {
        return (profile -> {
            profile.setData("fireReduction", 50);
        });
    }
}
