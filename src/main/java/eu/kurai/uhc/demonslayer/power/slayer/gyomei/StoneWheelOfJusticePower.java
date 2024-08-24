package eu.kurai.uhc.demonslayer.power.slayer.gyomei;

import eu.unchat.uhc.power.defaults.AbstractItemPower;
import eu.unchat.uhc.util.ItemBuilder;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@Getter
public final class StoneWheelOfJusticePower extends AbstractItemPower {
    private final String name, identifier;

    private final InteractionType interactionType = InteractionType.RIGHT_CLICK;

    public StoneWheelOfJusticePower() {
        this.name = "&a&lRoue en pierre de la justice";
        this.identifier = "stone_wheel_of_justice";

        this.setInitialCooldown(15 * 60);
        this.setInitialUses(2);
    }

    @Override
    public ItemStack getIcon() {
        return new ItemBuilder(Material.IRON_AXE)
                .name(formatName())
                .glowing(true)
                .asItemStack();
    }

    @Override
    public Result onInteract(final Player player, final InteractionType interactionType) {
        return Result.SUCCESSFUL;
    }
}
