package eu.kurai.uhc.demonslayer.power.slayer.tanjiro;

import com.google.common.collect.Lists;
import eu.kurai.uhc.demonslayer.DSPlugin;
import eu.kurai.uhc.demonslayer.role.AbstractDSRole;
import eu.kurai.uhc.demonslayer.role.defaults.slayer.TanjiroRole;
import eu.unchat.uhc.power.defaults.AbstractItemPower;
import eu.unchat.uhc.profile.IProfile;
import eu.unchat.uhc.util.CC;
import eu.unchat.uhc.util.ItemBuilder;
import eu.unchat.uhc.util.Utils;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;

@Getter
public final class SwirlPower extends AbstractItemPower implements Listener {

    private final String name, identifier;

    private final InteractionType interactionType = InteractionType.RIGHT_CLICK;

    private Collection<Location> locations;
    private Location centerLocation;

    public SwirlPower() {
        this.name = "&a&lTourbillon";
        this.identifier = "swirl";

        this.setInitialCooldown(15 * 60);
        this.setInitialUses(-1);

        this.locations = Lists.newArrayList();
    }

    @Override
    public ItemStack getIcon() {
        return new ItemBuilder(Material.WATER_LILY)
                .name(formatName())
                .lore(
                        "&7Fait apparaître un &9tourbillon d'eau",
                        "&7d'un rayon de 15 blocs sur le joueur ciblé",
                        "&7Ce tourbillon dure 30 secondes."
                )
                .glowing(true)
                .asItemStack();
    }

    @Override
    public Result onInteract(final Player player, final InteractionType interactionType) {
        final Player target = Utils.getTargetingPlayer(player, 20);
        if (target == null) {
            player.sendMessage(CC.error("Vous devez viser un joueur."));
            return Result.FAILED;
        }

        final Location location = target.getLocation();

        final int xCenter, yCenter, zCenter;
        xCenter = location.getBlockX();
        yCenter = location.getBlockY();
        zCenter = location.getBlockZ();

        this.centerLocation = new Location(location.getWorld(), xCenter, yCenter, zCenter);

        for (Block block : Utils.createCircle(centerLocation, 15)) {
            setBlock(block, Material.WATER);
            this.locations.add(block.getLocation());
        }

        Bukkit.getScheduler().runTaskLater(DSPlugin.get(), () -> {
            this.locations.forEach(location1 -> setBlock(location1.getBlock(), Material.AIR));
            this.locations.clear();
        }, 30 * 20L);
        return Result.SUCCESSFUL;
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        final Player player = event.getPlayer();
        if (this.locations.isEmpty() || AbstractDSRole.isRole(IProfile.of(player.getUniqueId()), TanjiroRole.class)) {
            return;
        }

        if (player.getLocation().distance(centerLocation) > 15 || player.getLocation().distance(centerLocation) < 1) {
            return;
        }

        player.setVelocity(player.getLocation().toVector().subtract(this.centerLocation.toVector()).normalize().multiply(-1));
    }

    private void setBlock(final Block block, final Material material) {
        block.setType(material);
    }
}
