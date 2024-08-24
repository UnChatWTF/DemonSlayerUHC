package eu.kurai.uhc.demonslayer.power.demon.doma;

import com.google.common.collect.Lists;
import eu.kurai.uhc.demonslayer.DSPlugin;
import eu.kurai.uhc.demonslayer.role.defaults.demon.DomaRole;
import eu.unchat.uhc.power.defaults.AbstractItemPower;
import eu.unchat.uhc.profile.IProfile;
import eu.unchat.uhc.role.AbstractRole;
import eu.unchat.uhc.util.ItemBuilder;
import eu.unchat.uhc.util.Utils;
import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.spigotmc.AsyncCatcher;

import java.util.Collection;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Getter
public final class FrostCloudPower extends AbstractItemPower implements Listener {

    private final String name, identifier;


    private final InteractionType interactionType = InteractionType.RIGHT_CLICK;

    private Collection<BlockData> oldBlocks;

    private Location centerLocation;

    private Collection<UUID> players;

    public FrostCloudPower() {
        this.name = "&c&lNuage de froid";
        this.identifier = "frost_cloud";

        this.setInitialCooldown(20 * 60);
        this.setInitialUses(-1);

        this.oldBlocks = Lists.newArrayList();
    }

    @Override
    public Result onInteract(Player player, InteractionType type) {
        if (!type.equals(InteractionType.RIGHT_CLICK)) {
            return Result.FAILED;
        }

        this.centerLocation = player.getLocation();

        CompletableFuture.runAsync(() -> {
            for (Block block : Utils.createCompleteHighestCircle(centerLocation, 30)) {
                AsyncCatcher.enabled = false;
                oldBlocks.add(new BlockData(block.getType(), block.getData(), block.getLocation()));
                block.setType(Material.PACKED_ICE);
                AsyncCatcher.enabled = true;
            }
        });

        new BukkitRunnable() {
            @Override
            public void run() {
                CompletableFuture.runAsync(() -> {
                    for (BlockData block : oldBlocks) {
                        AsyncCatcher.enabled = false;
                        block.location().getBlock().setType(block.material());
                        block.location().getBlock().setData((byte) block.data());
                        AsyncCatcher.enabled = true;
                    }
                    players.clear();
                    centerLocation = null;
                });
            }
        }.runTaskLaterAsynchronously(DSPlugin.get(), (5 * 60) * 20L);
        return Result.SUCCESSFUL;
    }

    @Override
    public ItemStack getIcon() {
        return new ItemBuilder(Material.PACKED_ICE)
                .name(formatName())
                .glowing(true)
                .asItemStack();
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (centerLocation == null) {
            return;
        }

        IProfile profile = IProfile.of(player.getUniqueId());

        if (event.getTo().distanceSquared(centerLocation) <= 30 * 30) {
            if (players.contains(player.getUniqueId())) {
                return;
            }

            players.add(player.getUniqueId());

            if (AbstractRole.isRole(profile, DomaRole.class)) {
                profile.setSpeedBuffer(profile.getSlownessBuffer() + 0.04F);
                return;
            }
            profile.setSlownessBuffer(profile.getSlownessBuffer() + 0.03F);
            return;
        }

        players.remove(player.getUniqueId());
        if (AbstractRole.isRole(profile, DomaRole.class)) {
            profile.setSpeedBuffer(profile.getSlownessBuffer() - 0.04F);
            return;
        }

        profile.setSlownessBuffer(profile.getSlownessBuffer() - 0.03F);
    }

    private record BlockData(Material material, int data, Location location) {

    }
}
