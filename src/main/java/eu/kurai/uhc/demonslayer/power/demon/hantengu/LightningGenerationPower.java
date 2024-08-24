package eu.kurai.uhc.demonslayer.power.demon.hantengu;

import eu.unchat.uhc.power.defaults.AbstractItemPower;
import eu.unchat.uhc.util.ItemBuilder;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@Getter
public final class LightningGenerationPower extends AbstractItemPower {
    private final String name, identifier;

    private final InteractionType interactionType = InteractionType.RIGHT_CLICK;

    public LightningGenerationPower() {
        this.name = "&c&lGénération d'éclair";
        this.identifier = "lightning_generation";

        setInitialCooldown(30);
        setInitialUses(-1);
    }

    @Override
    public Result onInteract(Player player, InteractionType interactionType) {
        return Result.SUCCESSFUL;
    }

    @Override
    public ItemStack getIcon() {
        return new ItemBuilder(Material.INK_SACK, 1, 11)
                .name(name)
                .glowing(true)
                .asItemStack();
    }
}
