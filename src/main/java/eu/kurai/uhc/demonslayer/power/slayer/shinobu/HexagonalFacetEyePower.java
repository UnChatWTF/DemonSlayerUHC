package eu.kurai.uhc.demonslayer.power.slayer.shinobu;

import eu.unchat.uhc.power.defaults.AbstractItemPower;
import eu.unchat.uhc.util.ItemBuilder;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@Getter
public final class HexagonalFacetEyePower extends AbstractItemPower {

    private final String name, identifier;

    private final InteractionType interactionType = InteractionType.RIGHT_CLICK;

    public HexagonalFacetEyePower() {
        this.name = "&a&lHEXAGONAL FACET EYE";
        this.identifier = "hexagonal_facet_eye";

        this.setInitialCooldown(20 * 60);
        this.setInitialUses(-1);
    }

    @Override
    public ItemStack getIcon() {
        return new ItemBuilder(Material.SPIDER_EYE)
                .name(formatName())
                .lore(
                        "&7Vous permet de placer une &dfleur de glycine",
                        "&7sur le joueur ciblé, s'il est &cdémon&7, ce dernier",
                        "&cexplosera&7, sinon, il subira &c2❤ &7de dégâts."
                )
                .glowing(true)
                .asItemStack();
    }

    @Override
    public Result onInteract(final Player player, final InteractionType interactionType) {
        return Result.SUCCESSFUL;
    }
}
