package eu.kurai.uhc.demonslayer.power.demon.kokushibo;

import eu.unchat.uhc.power.defaults.AbstractItemPower;
import eu.unchat.uhc.util.ItemBuilder;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@Getter
public final class EvilMirrorPower extends AbstractItemPower {

    private final String name, identifier;

    private final InteractionType interactionType = InteractionType.RIGHT_CLICK;

    public EvilMirrorPower() {
        this.name = "&c&lMiroir maléfique";
        this.identifier = "evil_mirror";

        this.setInitialCooldown(10 * 60);
        this.setInitialUses(-1);
    }

    @Override
    public Result onInteract(Player player, InteractionType interactionType) {
        return Result.SUCCESSFUL;
    }

    @Override
    public ItemStack getIcon() {
        return new ItemBuilder(Material.BLAZE_POWDER)
                .name(formatName())
                .asItemStack();
    }
}
