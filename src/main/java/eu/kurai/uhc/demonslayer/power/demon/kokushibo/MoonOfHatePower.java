package eu.kurai.uhc.demonslayer.power.demon.kokushibo;

import eu.unchat.uhc.power.defaults.AbstractItemPower;
import eu.unchat.uhc.util.ItemBuilder;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@Getter
public final class MoonOfHatePower extends AbstractItemPower {

    private final String name, identifier;


    private final InteractionType interactionType = InteractionType.RIGHT_CLICK;

    public MoonOfHatePower() {
        this.name = "&c&lLune de la haine";
        this.identifier = "moon_of_hate";

        this.setInitialCooldown(5 * 60);
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
