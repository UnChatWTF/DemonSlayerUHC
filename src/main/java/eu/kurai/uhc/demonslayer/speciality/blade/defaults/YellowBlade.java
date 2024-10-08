package eu.kurai.uhc.demonslayer.speciality.blade.defaults;

import eu.kurai.uhc.demonslayer.speciality.blade.IBlade;
import eu.unchat.uhc.profile.IProfile;
import eu.unchat.uhc.util.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.function.Consumer;

public final class YellowBlade implements IBlade {
    @Override
    public ItemStack getDisplay() {
        return new ItemBuilder(Material.INK_SACK)
                .name("&e&lLame jaune")
                .lore("&rVous octroie &e&l7% &7de chance de faire", "&7apparaître un &eéclair &7sur un joueur", "&7lorsque vous lui infligez des dégâts.")
                .asItemStack();
    }

    @Override
    public Consumer<IProfile> apply() {
        return (profile -> {
            profile.setData("lightningChance", 7);
        });
    }
}
