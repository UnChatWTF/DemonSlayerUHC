package eu.kurai.uhc.demonslayer.power.demon.hantengu;

import eu.unchat.uhc.power.defaults.AbstractItemPower;
import eu.unchat.uhc.util.ItemBuilder;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@Getter
public final class HandlingSpearsPower extends AbstractItemPower {

    private final String name, identifier;

    private final InteractionType interactionType = InteractionType.RIGHT_CLICK;

    public HandlingSpearsPower() {
        this.name = "&c&lManipulation des lances";
        this.identifier = "handling_spears";
    }

    @Override
    public Result onInteract(Player player, InteractionType interactionType) {
        return Result.SUCCESSFUL;
    }

    @Override
    public ItemStack getIcon() {
        return new ItemBuilder(Material.STICK)
                .name(name)
                .asItemStack();
    }
}
