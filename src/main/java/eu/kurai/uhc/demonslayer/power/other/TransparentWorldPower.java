package eu.kurai.uhc.demonslayer.power.other;

import eu.kurai.uhc.demonslayer.DSPlugin;
import eu.kurai.uhc.demonslayer.role.AbstractDSRole;
import eu.kurai.uhc.demonslayer.role.defaults.solitary.YoriichiRole;
import eu.unchat.uhc.power.defaults.AbstractItemPower;
import eu.unchat.uhc.profile.IProfile;
import eu.unchat.uhc.util.ItemBuilder;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import java.util.concurrent.ThreadLocalRandom;

@Getter
public final class TransparentWorldPower extends AbstractItemPower implements Listener {

    private final String name, identifier;

    private final InteractionType interactionType = InteractionType.RIGHT_CLICK;

    private boolean enabled;

    public TransparentWorldPower() {
        this.name = "&3&lMonde transparent";
        this.identifier = "transparent_world";

        this.setInitialCooldown(15 * 60);
        this.setInitialUses(-1);
    }

    @Override
    public Result onInteract(final Player player, final InteractionType interactionType) {
        if (enabled) {
            return Result.FAILED;
        }

        // TODO: SEE PLAYERS HEALTH
        enabled = true;
        Bukkit.getScheduler().runTaskLaterAsynchronously(DSPlugin.get(), () -> enabled = false, (5 * 60) * 20L);
        return Result.SUCCESSFUL;
    }

    @Override
    public ItemStack getIcon() {
        return new ItemBuilder(Material.BLAZE_POWDER)
                .name(formatName())
                .asItemStack();
    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if (!(event.getEntity() instanceof Player) || !(event.getDamager() instanceof Player damager)) {
            return;
        }

        if (!AbstractDSRole.isRole(IProfile.of(damager.getUniqueId()), YoriichiRole.class) || !enabled) {
            return;
        }

        event.setCancelled(ThreadLocalRandom.current().nextInt(100) <= 5);
    }

}
