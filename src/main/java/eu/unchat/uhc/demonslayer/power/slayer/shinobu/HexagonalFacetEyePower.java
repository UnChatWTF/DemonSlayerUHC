package eu.unchat.uhc.demonslayer.power.slayer.shinobu;

import eu.unchat.uhc.power.AbstractItemPower;
import eu.unchat.uhc.util.CC;
import eu.unchat.uhc.util.ItemBuilder;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@Getter
public final class HexagonalFacetEyePower extends AbstractItemPower {
    private final String name;
    private final int initialCooldown, initialUses;
    private final ClickType clickType;

    public HexagonalFacetEyePower() {
        this.name = "&a&lHEXAGONAL FACET EYE";
        this.initialCooldown = 20 * 60;
        this.initialUses = -1;
        this.clickType = ClickType.RIGHT_CLICK;
    }

    @Override
    public ItemStack getIcon() {
        return new ItemBuilder(Material.SPIDER_EYE)
                .name(name + " &8" + CC.SQUARE + " &7Clic-droit")
                .lore(
                        "&7Vous permet de placer une &dfleur de glycine",
                        "&7sur le joueur ciblé, s'il est &cdémon&7, ce dernier",
                        "&cexplosera&7, sinon, il subira &c2❤ &7de dégâts."
                )
                .glowing(true)
                .asItemStack();
    }

    @Override
    public boolean onClick(Player player) {
        return true;
    }
}
