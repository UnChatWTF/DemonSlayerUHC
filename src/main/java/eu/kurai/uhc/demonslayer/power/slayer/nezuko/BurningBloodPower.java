package eu.kurai.uhc.demonslayer.power.slayer.nezuko;

import eu.unchat.uhc.power.defaults.AbstractItemPower;
import eu.unchat.uhc.util.ItemBuilder;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@Getter
public final class BurningBloodPower extends AbstractItemPower {
    private final String name, identifier;


    private final InteractionType interactionType = InteractionType.RIGHT_CLICK;

    public BurningBloodPower() {
        this.name = "&c&lSang enflammé";
        this.identifier = "burning_blood";

        this.setInitialCooldown(15 * 60);
        this.setInitialUses(2);
    }

    @Override
    public ItemStack getIcon() {
        return new ItemBuilder(Material.BLAZE_POWDER)
                .name(formatName())
                .lore(
                        "",
                        "&7Vos coups sont &6enflammés&7, cependant,",
                        "&7si vous &6enflammez &7un &aslayer&7, il sera alors",
                        "&dsoigné &7du même nombre de coeurs qu'il était",
                        "&7censé perdre.",
                        "&7Si vous enflammez &aTanjiro&7, il obtiendra",
                        "&c+10% &7de &cForce &7supplémentaire."
                )
                .glowing(true)
                .asItemStack();
    }

    @Override
    public Result onInteract(final Player player, final InteractionType interactionType) {
        return Result.SUCCESSFUL;
    }
}
