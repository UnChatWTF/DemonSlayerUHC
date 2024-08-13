package eu.unchat.uhc.demonslayer.power.slayer.kyojuro;

import eu.unchat.uhc.power.item.AbstractItemPower;
import eu.unchat.uhc.util.ItemBuilder;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

@Getter
public final class FireSeaPower extends AbstractItemPower {

    private final String name;
    private final int initialCooldown, initialUses;
    private final ClickType clickType;

    public FireSeaPower() {
        this.name = "&a&lFIRE SEA";
        this.initialCooldown = 10 * 60;
        this.initialUses = -1;
        this.clickType = ClickType.RIGHT_CLICK;
    }

    @Override
    public ItemStack getIcon() {
        return new ItemBuilder(Material.BLAZE_ROD)
                .name(name)
                .glowing(true)
                .asItemStack();
    }

    @Override
    public Result onClick(final Player player, final boolean right) {
        Vector vector = player.getEyeLocation().getDirection().multiply(2);
        player.setVelocity(vector);
        return Result.SUCCESS;
    }
}
