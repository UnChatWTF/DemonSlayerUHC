package eu.kurai.uhc.demonslayer.power.solitary.yoriichi;

import com.google.common.collect.Maps;
import eu.kurai.uhc.demonslayer.DSPlugin;
import eu.kurai.uhc.demonslayer.role.AbstractDSRole;
import eu.kurai.uhc.demonslayer.role.defaults.solitary.YoriichiRole;
import eu.unchat.uhc.power.defaults.AbstractCommandPower;
import eu.unchat.uhc.profile.IProfile;
import eu.unchat.uhc.util.CC;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.Contract;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Getter
public final class SunBreathPower extends AbstractCommandPower implements Listener {

    private final String name, commandName, identifier;
    private final Map<UUID, Integer> slowRegeneration;
    private boolean active;

    public SunBreathPower() {
        this.name = "&6&lSouffle du soleil";
        this.commandName = "ds sun";
        this.identifier = "sun_breath";

        this.setInitialCooldown(3);
        this.setInitialUses(-1);

        this.slowRegeneration = Maps.newHashMap();
    }

    @Override
    public Result onCommand(final Player player, final String[] args) {
        active = !active;
        player.sendMessage(CC.info("Vous avez " + (active ? "activé" : "désactivé") + " votre souffle du soleil."));
        if (!active) {
            this.slowRegeneration.clear();
            return Result.SUCCESSFUL;
        }

        getSlowRegenerationRunnable().runTaskTimerAsynchronously(DSPlugin.get(), 0, 20);
        return Result.SUCCESSFUL;
    }

    @EventHandler
    public void onEntityDamageByEntity(final EntityDamageByEntityEvent event) {
        if (!(event.getEntity() instanceof final Player victim) || !(event.getDamager() instanceof final Player damager)) {
            return;
        }

        if (!AbstractDSRole.isRole(IProfile.of(damager.getUniqueId()), YoriichiRole.class) || !active) {
            return;
        }

        this.slowRegeneration.put(victim.getUniqueId(), 8);
        if (ThreadLocalRandom.current().nextInt(100) > 10) {
            return;
        }
        victim.setFireTicks(20 * 8);
    }

    @EventHandler
    public void onPlayerItemConsume(final PlayerItemConsumeEvent event) {
        final Player player = event.getPlayer();
        if (!event.getItem().getType().equals(Material.GOLDEN_APPLE)) {
            return;
        }

        if (!getSlowRegeneration().containsKey(player.getUniqueId())) {
            return;
        }

        player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 8 * 20, 0, false, false), true);
    }

    @Contract("-> new")
    public BukkitRunnable getSlowRegenerationRunnable() {
        return new BukkitRunnable() {
            @Override
            public void run() {
                for (final Map.Entry<UUID, Integer> entry : slowRegeneration.entrySet()) {
                    final UUID uuid = entry.getKey();
                    final int seconds = entry.getValue();

                    if (seconds == 0) {
                        slowRegeneration.remove(uuid);
                        continue;
                    }

                    slowRegeneration.put(uuid, seconds - 1);
                }
            }
        };
    }
}
