package eu.kurai.uhc.demonslayer.power.slayer.kyojuro;

import eu.unchat.uhc.power.defaults.AbstractItemPower;
import eu.unchat.uhc.util.ItemBuilder;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

@Getter
public final class FireSeaPower extends AbstractItemPower {

    private final String name, identifier;

    private final InteractionType interactionType = InteractionType.RIGHT_CLICK;

    public FireSeaPower() {
        this.name = "&a&lMer de feu";
        this.identifier = "fire_sea";

        this.setInitialCooldown(10 * 60);
        this.setInitialUses(-1);
    }

    @Override
    public ItemStack getIcon() {
        return new ItemBuilder(Material.BLAZE_ROD)
                .name(formatName())
                .glowing(true)
                .asItemStack();
    }

    @Override
    public Result onInteract(final Player player, final InteractionType interactionType) {
        Vector vector = player.getEyeLocation().getDirection().multiply(2);
        player.setVelocity(vector);
        return Result.SUCCESSFUL;
    }
}
