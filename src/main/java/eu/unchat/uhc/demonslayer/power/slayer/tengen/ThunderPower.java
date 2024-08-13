package eu.unchat.uhc.demonslayer.power.slayer.tengen;

import eu.unchat.uhc.demonslayer.DSPlugin;
import eu.unchat.uhc.power.item.AbstractItemPower;
import eu.unchat.uhc.power.item.AbstractRightClickPower;
import eu.unchat.uhc.util.ItemBuilder;
import eu.unchat.uhc.util.Utils;
import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

@Getter
public final class ThunderPower extends AbstractRightClickPower implements Listener {

    private final String name;

    private final int initialCooldown, initialUses;

    public ThunderPower() {
        this.name = "&a&lTHUNDER";
        this.initialCooldown = 5 * 60;
        this.initialUses = -1;
    }

    @Override
    public Result onRightClick(Player player) {
        Snowball snowball = player.launchProjectile(Snowball.class);
        snowball.setMetadata("thunder", new FixedMetadataValue(DSPlugin.get(), "tengen_thunder"));
        player.updateInventory();
        return Result.SUCCESS;
    }

    @Override
    public ItemStack getIcon() {
        return new ItemBuilder(Material.SNOW_BALL)
                .name(name)
                .glowing(true)
                .asItemStack();
    }

    @EventHandler
    public void onProjectileHit(ProjectileHitEvent event) {
        if (!(event.getEntity() instanceof Snowball snowball) || !snowball.hasMetadata("thunder")) {
            return;
        }

        Location location = snowball.getLocation();
        for (Location block : Utils.createSphere(location, 5, false)) {
            block.getBlock().setType(Material.AIR);
        }

        Utils.fakeExplosion(location, 5, 4);
    }

}
