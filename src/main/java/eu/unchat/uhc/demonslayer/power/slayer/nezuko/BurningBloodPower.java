package eu.unchat.uhc.demonslayer.power.slayer.nezuko;

import eu.unchat.uhc.power.item.AbstractItemPower;
import eu.unchat.uhc.util.CC;
import eu.unchat.uhc.util.ItemBuilder;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@Getter
public final class BurningBloodPower extends AbstractItemPower {
    private final String name;
    private final int initialCooldown, initialUses;
    private final ClickType clickType;

    public BurningBloodPower() {
        this.name = "&c&lBURNING BLOOD";
        this.initialCooldown = 15 * 60;
        this.initialUses = 2;
        this.clickType = ClickType.RIGHT_CLICK;
    }

    @Override
    public ItemStack getIcon() {
        return new ItemBuilder(Material.BLAZE_POWDER)
                .name(name + " &8" + CC.SQUARE + " &7Clic-droit")
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
    public Result onClick(final Player player, final boolean right) {
        return Result.SUCCESS;
    }
}
