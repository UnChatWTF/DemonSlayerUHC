package eu.unchat.uhc.demonslayer.power.slayer.gyomei;

import eu.unchat.uhc.power.AbstractItemPower;
import eu.unchat.uhc.util.CC;
import eu.unchat.uhc.util.ItemBuilder;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@Getter
public final class StoneWheelOfJusticePower extends AbstractItemPower {

    private final String name;
    private final int initialCooldown, initialUses;
    private final ClickType clickType;

    public StoneWheelOfJusticePower() {
        this.name = "&a&lSTONE WHEEL OF JUSTICE";
        this.initialCooldown = 15 * 60;
        this.initialUses = 2;
        this.clickType = ClickType.RIGHT_CLICK;
    }

    @Override
    public ItemStack getIcon() {
        return new ItemBuilder(Material.IRON_AXE)
                .name(name + " &8" + CC.SQUARE + " &7Clic-droit")
                .glowing(true)
                .asItemStack();
    }

    @Override
    public boolean onClick(Player player) {
        return true;
    }
}
