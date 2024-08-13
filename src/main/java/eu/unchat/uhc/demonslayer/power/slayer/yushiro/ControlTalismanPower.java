package eu.unchat.uhc.demonslayer.power.slayer.yushiro;

import eu.unchat.uhc.power.item.AbstractItemPower;
import eu.unchat.uhc.util.ItemBuilder;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@Getter
public final class ControlTalismanPower extends AbstractItemPower {

    private final String name;

    private final int initialCooldown, initialUses;

    private final ClickType clickType;

    public ControlTalismanPower() {
        this.name = "&c&lCONTROL TALISMAN";
        this.initialCooldown = 10 * 60;
        this.initialUses = -1;
        this.clickType = ClickType.RIGHT_CLICK;
    }

    @Override
    public ItemStack getIcon() {
        return new ItemBuilder(Material.BARRIER)
                .name(name)
                .asItemStack();
    }

    @Override
    public Result onClick(final Player player, final boolean right) {
        return Result.SUCCESS;
    }
}
