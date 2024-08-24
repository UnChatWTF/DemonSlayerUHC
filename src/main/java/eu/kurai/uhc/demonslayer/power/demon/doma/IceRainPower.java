package eu.kurai.uhc.demonslayer.power.demon.doma;

import eu.kurai.uhc.demonslayer.DSPlugin;
import eu.unchat.uhc.power.defaults.AbstractItemPower;
import eu.unchat.uhc.profile.IProfile;
import eu.unchat.uhc.util.ItemBuilder;
import eu.unchat.uhc.util.Utils;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.Contract;

import java.util.concurrent.ThreadLocalRandom;

@Getter
public final class IceRainPower extends AbstractItemPower {

    private final String name, identifier;


    private final AbstractItemPower.InteractionType interactionType = AbstractItemPower.InteractionType.RIGHT_CLICK;

    public IceRainPower() {
        this.name = "&c&lPluie de glace";
        this.identifier = "ice_rain";

        this.setInitialCooldown(10 * 60);
        this.setInitialUses(-1);
    }

    @Override
    public Result onInteract(Player player, InteractionType type) {
        if (!type.equals(InteractionType.RIGHT_CLICK)) {
            return Result.FAILED;
        }

        new BukkitRunnable() {
            private int attempts = 0;

            @Override
            public void run() {
                attempts++;

                for (int i = 0; i < 5; i++) {
                    final int centerX, centerZ, minX, maxX, minZ, maxZ;
                    centerX = player.getLocation().getBlockX();
                    centerZ = player.getLocation().getBlockZ();

                    minX = centerX - 30;
                    maxX = centerX + 30;

                    minZ = centerZ - 30;
                    maxZ = centerZ + 30;

                    final int x = ThreadLocalRandom.current().nextInt(minX, maxX);
                    final int z = ThreadLocalRandom.current().nextInt(minZ, maxZ);

                    Block highestBlock = Utils.getHighestBlock(player.getWorld(), x, z);
                    if (highestBlock == null) {
                        continue;
                    }

                    int y = highestBlock.getY() + 20;

                    FallingBlock fallingBlock = player.getWorld().spawnFallingBlock(
                            new Location(player.getWorld(), x, y, z),
                            Material.PACKED_ICE,
                            (byte) 0
                    );

                    fallingBlock.setDropItem(false);
                    getFallRunnable(fallingBlock).runTaskTimerAsynchronously(DSPlugin.get(), 0, 1);
                }

                if (attempts == 15) {
                    cancel();
                    return;
                }
            }
        }.runTaskTimerAsynchronously(DSPlugin.get(), 0, 20 * 4);
        return Result.SUCCESSFUL;
    }

    @Override
    public ItemStack getIcon() {
        return new ItemBuilder(Material.PACKED_ICE)
                .name(formatName())
                .glowing(true)
                .asItemStack();
    }

    @Contract("_ -> new")
    public BukkitRunnable getFallRunnable(final FallingBlock block) {
        return new BukkitRunnable() {
            @Override
            public void run() {
                if (block.isOnGround()) {
                    for (Player nearbyPlayer : Utils.getNearbyPlayers(block.getLocation(), 5)) {
                        IProfile profile = IProfile.of(nearbyPlayer.getUniqueId());
                        profile.removeHealth(2);
                        profile.setSlownessBuffer(profile.getSlownessBuffer() + 0.03F);
                        Bukkit.getScheduler().runTaskLaterAsynchronously(DSPlugin.get(), () -> {
                            profile.setSlownessBuffer(profile.getSlownessBuffer() - 0.03F);
                        }, 20 * 10);
                    }
                    cancel();
                }
            }
        };
    }
}
