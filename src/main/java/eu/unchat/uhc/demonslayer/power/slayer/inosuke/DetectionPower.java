package eu.unchat.uhc.demonslayer.power.slayer.inosuke;

import eu.unchat.uhc.power.AbstractItemPower;
import eu.unchat.uhc.util.ItemBuilder;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@Getter
public final class DetectionPower extends AbstractItemPower {
    private final String name;
    private final int initialCooldown, initialUses;
    private final ClickType clickType;

    public DetectionPower() {
        this.name = "&a&lDETECTION";
        this.initialCooldown = 20 * 60;
        this.initialUses = 3;
        this.clickType = ClickType.RIGHT_CLICK;
    }

    @Override
    public ItemStack getIcon() {
        return new ItemBuilder(Material.YELLOW_FLOWER, 1, 4)
                .name(name)
                .lore(
                        "&7Vous affiche des particules",
                        "&7pointant les joueurs dans un",
                        "&7rayon de 75 blocs."
                )
                .glowing(true)
                .asItemStack();
    }

    @Override
    public boolean onClick(Player player) {
        return true;
    }
}
