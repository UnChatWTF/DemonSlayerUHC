package eu.unchat.uhc.demonslayer.power.slayer.kyojuro;

import eu.unchat.uhc.power.AbstractItemPower;
import eu.unchat.uhc.util.ItemBuilder;
import eu.unchat.uhc.util.Utils;
import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.spigotmc.AsyncCatcher;

import java.util.concurrent.CompletableFuture;

@Getter
public final class PurgatoryPower extends AbstractItemPower {

    private final String name;
    private final int initialCooldown, initialUses;
    private final ClickType clickType;

    public PurgatoryPower() {
        this.name = "&a&lPURGATORY";
        this.initialCooldown = 15 * 60;
        this.initialUses = 2;
        this.clickType = ClickType.RIGHT_CLICK;
    }

    @Override
    public ItemStack getIcon() {
        return new ItemBuilder(Material.BLAZE_POWDER)
                .name(name)
                .glowing(true)
                .asItemStack();
    }

    @Override
    public boolean onClick(Player player) {
        Location center = player.getLocation();
        CompletableFuture.runAsync(() -> {
            for (Location location : Utils.createSphere(center, 10, false)) {
                AsyncCatcher.enabled = false;
                location.getBlock().breakNaturally();
                AsyncCatcher.enabled = true;
            }
        });
        return true;
    }
}
