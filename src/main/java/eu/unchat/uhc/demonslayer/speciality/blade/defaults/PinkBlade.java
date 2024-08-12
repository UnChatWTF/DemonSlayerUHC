package eu.unchat.uhc.demonslayer.speciality.blade.defaults;

import eu.unchat.uhc.demonslayer.speciality.blade.IBlade;
import eu.unchat.uhc.profile.IProfile;
import eu.unchat.uhc.util.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.function.Consumer;

public final class PinkBlade implements IBlade {
    @Override
    public ItemStack getDisplay() {
        return new ItemBuilder(Material.INK_SACK)
                .name("&d&lLame rose")
                .lore("&7Vous octroie &d&l2❤ &7supplémentaires.")
                .asItemStack();
    }

    @Override
    public Consumer<IProfile> apply() {
        return (profile -> {
            if (!profile.isOnline()) {
                return;
            }

            Player player = profile.getPlayer();
            player.setMaxHealth(player.getMaxHealth() + 4);
            profile.addHealth(4);
        });
    }
}
