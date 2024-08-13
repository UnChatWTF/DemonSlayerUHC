package eu.unchat.uhc.demonslayer.power.slayer.zenitsu;

import eu.unchat.uhc.power.item.AbstractItemPower;
import eu.unchat.uhc.util.ItemBuilder;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@Getter
public final class GodOfCelestialFirePower extends AbstractItemPower {
    private final String name;
    private final int initialCooldown, initialUses;
    private final ClickType clickType;

    public GodOfCelestialFirePower() {
        this.name = "&a&lGOD OF CELESTIAL FIRE";
        this.initialCooldown = 0;
        this.initialUses = 1;
        this.clickType = ClickType.RIGHT_CLICK;
    }

    @Override
    public ItemStack getIcon() {
        return new ItemBuilder(Material.BLAZE_POWDER)
                .name(name)
                .lore(
                        "&7Des &eéclairs &7apparaissent sur les joueurs dans",
                        "&7un rayon de 30 blocs, infligeant &c2❤ &7de dégâts",
                        "&7les &6enflammant &7également durant 8 secondes."
                )
                .glowing(true)
                .asItemStack();
    }

    @Override
    public Result onClick(final Player player, final boolean right) {
        return Result.SUCCESS;
    }
}
