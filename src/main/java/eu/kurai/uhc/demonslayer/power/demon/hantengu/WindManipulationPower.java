package eu.kurai.uhc.demonslayer.power.demon.hantengu;

import eu.unchat.uhc.power.defaults.AbstractItemPower;
import eu.unchat.uhc.util.ItemBuilder;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@Getter
public final class WindManipulationPower extends AbstractItemPower {
    private final String name, identifier;

    private final InteractionType interactionType = InteractionType.RIGHT_CLICK;

    public WindManipulationPower() {
        this.name = "&c&lManipulation du vent";
        this.identifier = "wind_manipulation";

        setInitialCooldown(5 * 60);
        setInitialUses(-1);
    }

    @Override
    public Result onInteract(Player player, InteractionType interactionType) {
        return Result.SUCCESSFUL;
    }

    @Override
    public ItemStack getIcon() {
        return new ItemBuilder(Material.INK_SACK, 1, 2)
                .name(name)
                .glowing(true)
                .asItemStack();
    }
}
