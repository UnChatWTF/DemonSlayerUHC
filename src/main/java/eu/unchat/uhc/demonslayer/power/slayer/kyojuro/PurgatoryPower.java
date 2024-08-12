package eu.unchat.uhc.demonslayer.power.slayer.kyojuro;

import eu.unchat.uhc.demonslayer.DSPlugin;
import eu.unchat.uhc.power.AbstractItemPower;
import eu.unchat.uhc.profile.IProfile;
import eu.unchat.uhc.util.ItemBuilder;
import eu.unchat.uhc.util.Utils;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

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

        IProfile profile = IProfile.of(player.getUniqueId());
        profile.setStrengthBuffer(profile.getStrengthBuffer() + 10);
        profile.setAttackDelay(profile.getAttackDelay() - 2);

        Bukkit.getScheduler().runTaskLater(DSPlugin.get(), () -> {
            profile.setStrengthBuffer(profile.getStrengthBuffer() - 10);
            profile.setAttackDelay(profile.getAttackDelay() + 2);
        }, (3 * 60) * 20L);

        for (Location location : Utils.createSphere(center, 20, false)) {
            location.getBlock().setType(Material.AIR);
        }
        return true;
    }
}
