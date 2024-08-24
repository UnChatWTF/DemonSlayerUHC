package eu.kurai.uhc.demonslayer.power.slayer.zenitsu;

import eu.unchat.uhc.power.defaults.AbstractItemPower;
import eu.unchat.uhc.util.ItemBuilder;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@Getter
public final class GodOfCelestialFirePower extends AbstractItemPower {
    private final String name, identifier;


    private final InteractionType interactionType = InteractionType.RIGHT_CLICK;

    public GodOfCelestialFirePower() {
        this.name = "&a&lDieu du feu céleste";
        this.identifier = "god_of_celestial_fire";

        this.setInitialCooldown(0);
        this.setInitialUses(1);
    }

    @Override
    public ItemStack getIcon() {
        return new ItemBuilder(Material.BLAZE_POWDER)
                .name(formatName())
                .lore(
                        "&7Des &eéclairs &7apparaissent sur les joueurs dans",
                        "&7un rayon de 30 blocs, infligeant &c2❤ &7de dégâts",
                        "&7les &6enflammant &7également durant 8 secondes."
                )
                .glowing(true)
                .asItemStack();
    }

    @Override
    public Result onInteract(final Player player, final InteractionType interactionType) {
        return Result.SUCCESSFUL;
    }
}
