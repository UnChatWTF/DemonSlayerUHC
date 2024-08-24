package eu.kurai.uhc.demonslayer.power.slayer.yushiro;

import eu.unchat.uhc.power.defaults.AbstractItemPower;
import eu.unchat.uhc.util.CC;
import eu.unchat.uhc.util.ItemBuilder;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@Getter
public final class RevealTalismanPower extends AbstractItemPower {

    private final String name, identifier;


    private final InteractionType interactionType = InteractionType.RIGHT_CLICK;

    public RevealTalismanPower() {
        this.name = "&c&lTalisman de révélation";
        this.identifier = "reveal_talisman";

        this.setInitialCooldown(5 * 60);
        this.setInitialUses(-1);
    }

    @Override
    public ItemStack getIcon() {
        return new ItemBuilder(Material.EYE_OF_ENDER)
                .name(formatName())
                .asItemStack();
    }

    @Override
    public Result onInteract(final Player player, final InteractionType interactionType) {
        player.sendMessage(CC.info("Test"));
        return Result.SUCCESSFUL;
    }
}
