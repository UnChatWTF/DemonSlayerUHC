package eu.unchat.uhc.demonslayer.power.slayer.inosuke;

import eu.unchat.uhc.power.AbstractItemPower;
import eu.unchat.uhc.util.ItemBuilder;
import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@Getter
public final class BoarChargePower extends AbstractItemPower {

    private final String name;
    private final int initialCooldown, initialUses;
    private final ClickType clickType;

    public BoarChargePower() {
        this.name = "&a&lBOAR CHARGE";
        this.initialCooldown = 10 * 60;
        this.initialUses = -1;
        this.clickType = ClickType.RIGHT_CLICK;
    }

    @Override
    public ItemStack getIcon() {
        return new ItemBuilder(Material.GRILLED_PORK)
                .name(this.name)
                .lore(
                        "&7Vous effectuez un dash en",
                        "&7avant de 20 blocs.",
                        "&7Les joueurs sur votre trajectoire sont",
                        "&7repoussés et perdent &c2❤ &7de dégâts."
                )
                .glowing(true)
                .asItemStack();
    }

    @Override
    public boolean onClick(Player player) {
        Location loc = player.getLocation();
        loc.add(loc.getDirection().multiply(3));
        player.setVelocity(loc.toVector());
        return true;
    }
}
