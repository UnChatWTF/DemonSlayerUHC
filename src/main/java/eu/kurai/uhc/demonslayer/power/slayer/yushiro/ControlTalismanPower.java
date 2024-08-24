package eu.kurai.uhc.demonslayer.power.slayer.yushiro;

import eu.unchat.uhc.power.defaults.AbstractItemPower;
import eu.unchat.uhc.util.ItemBuilder;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@Getter
public final class ControlTalismanPower extends AbstractItemPower {

    private final String name, identifier;

    private final InteractionType interactionType = InteractionType.RIGHT_CLICK;

    public ControlTalismanPower() {
        this.name = "&c&lTalisman de contrôle";
        this.identifier = "control_talisman";

        this.setInitialCooldown(10 * 60);
        this.setInitialUses(-1);
    }

    @Override
    public ItemStack getIcon() {
        return new ItemBuilder(Material.BARRIER)
                .name(formatName())
                .asItemStack();
    }

    @Override
    public Result onInteract(final Player player, final InteractionType interactionType) {
        return Result.SUCCESSFUL;
    }
}
