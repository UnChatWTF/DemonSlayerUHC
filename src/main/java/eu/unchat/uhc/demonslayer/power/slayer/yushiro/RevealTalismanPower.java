package eu.unchat.uhc.demonslayer.power.slayer.yushiro;

import eu.unchat.uhc.power.item.AbstractItemPower;
import eu.unchat.uhc.util.CC;
import eu.unchat.uhc.util.ItemBuilder;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@Getter
public final class RevealTalismanPower extends AbstractItemPower {

    private final String name;

    private final int initialCooldown, initialUses;

    private final ClickType clickType;

    public RevealTalismanPower() {
        this.name = "&c&lREVEAL TALISMAN";
        this.initialCooldown = 5 * 60;
        this.initialUses = -1;
        this.clickType = ClickType.RIGHT_CLICK;
    }

    @Override
    public ItemStack getIcon() {
        return new ItemBuilder(Material.EYE_OF_ENDER)
                .name(name)
                .asItemStack();
    }

    @Override
    public Result onClick(final Player player, final boolean right) {
        player.sendMessage(CC.info("Test"));
        return Result.SUCCESS;
    }
}
