package eu.kurai.uhc.demonslayer.power.slayer.inosuke;

import eu.kurai.uhc.demonslayer.util.particles.ColoredParticles;
import eu.unchat.uhc.power.defaults.AbstractItemPower;
import eu.unchat.uhc.util.ItemBuilder;
import lombok.Getter;
import net.minecraft.server.v1_8_R3.EnumParticle;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.awt.*;

@Getter
public final class DetectionPower extends AbstractItemPower {
    private final String name, identifier;

    private final InteractionType interactionType = InteractionType.RIGHT_CLICK;

    public DetectionPower() {
        this.name = "&a&lDétection";
        this.identifier = "detection";

        this.setInitialCooldown(20 * 60);
        this.setInitialUses(3);
    }

    @Override
    public ItemStack getIcon() {
        return new ItemBuilder(Material.RED_ROSE, 1, 4)
                .name(formatName())
                .lore(
                        "&7Vous affiche des &bparticules",
                        "&7pointant les joueurs dans un",
                        "&7rayon de &b75 blocs&7."
                )
                .glowing(true)
                .asItemStack();
    }

    @Override
    public Result onInteract(final Player player, final InteractionType interactionType) {
        for (Player online : player.getWorld().getPlayers()) {
            if (online.getUniqueId().equals(player.getUniqueId())) {
                continue;
            }

            if (player.getLocation().distance(online.getLocation()) <= 75) {
                drawLine(player, player.getLocation(), online.getLocation());
            }
        }
        return Result.SUCCESSFUL;
    }

    public void drawLine(final Player player, final Location a, final Location b) {
        Vector direction = b.toVector().setY(a.getY()).subtract(a.toVector()).normalize();
        for (double i = 0; i < a.distance(b); i += 0.5) {
            Location current = a.clone().add(direction.clone().multiply(i));
            new ColoredParticles(EnumParticle.REDSTONE, current, Color.RED).send(player);
        }
    }
}
