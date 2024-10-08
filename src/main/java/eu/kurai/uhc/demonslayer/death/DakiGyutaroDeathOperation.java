package eu.kurai.uhc.demonslayer.death;

import eu.kurai.uhc.demonslayer.DSPlugin;
import eu.kurai.uhc.demonslayer.role.defaults.demon.GyutaroRole;
import eu.unchat.uhc.API;
import eu.unchat.uhc.profile.IProfile;
import eu.unchat.uhc.profile.operation.IProfileDeathOperation;
import eu.unchat.uhc.util.CC;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.scheduler.BukkitTask;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Consumer;

@Getter
@Setter
@RequiredArgsConstructor
public final class DakiGyutaroDeathOperation implements IProfileDeathOperation {
    private final IProfile profile;
    private Player killer;
    private boolean disconnection;
    private int announceDelay;
    private BukkitTask task;

    @Override
    public void start() {
        if (profile.isOnline()) {
            IProfile gyutaro = API.get().getRoleHandler().getProfile(GyutaroRole.class);
            profile.sendMessage(gyutaro == null ? "Gyutaro est mort" : "Gyutaro est en vie");
            profile.sendMessage(CC.info("Vous êtes mort, cependant, vous possédez " + this.announceDelay + "s pour être ressuscité."));
        }

        task = getDeathRunnable().runTaskTimer(DSPlugin.get(), 0, 20);

        API.get()
                .getTimerHandler()
                .getTimers()
                .stream()
                .filter(timer -> timer.getIdentifier().equalsIgnoreCase("pvp"))
                .findFirst()
                .ifPresent(timer -> {
                    if (!timer.isRunning()) {
                        return;
                    }

                    respawn(null);
                });
    }

    @Override
    public void respawn(@Nullable Location location) {
        task.cancel();
        task = null;

        final World gameWorld = API.get().getWorldHandler().getGameWorld();
        final int borderSize = (int) (gameWorld.getWorldBorder().getSize() / 2) - 50;
        final int x = ThreadLocalRandom.current().nextInt(-borderSize, borderSize);
        final int z = ThreadLocalRandom.current().nextInt(-borderSize, borderSize);
        final Location loc = location == null ? new Location(gameWorld, x, 200, z) : location;

        if (this.profile.getPlayer().isEmpty()) {
            return;
        }

        final Player player = this.profile.getPlayer();
        this.profile.addInvulnerabilityClause(EntityDamageEvent.DamageCause.FALL);
        player.teleport(loc);
        player.sendMessage(CC.info("Vous avez été ressuscité !"));
        Bukkit.getScheduler().runTaskLater(API.get().getPlugin(), () -> {
            this.profile.removeInvulnerabilityClause(EntityDamageEvent.DamageCause.FALL);
        }, 15 * 20);
    }

    @Override
    public void end() {
        API.get().getGameHandler().getPlayers().remove(this.profile);
        this.task = null;
        this.profile.setState(IProfile.State.SPECTATING);
        this.profile.sendDeathMessage(disconnection, 0);

        if (!profile.isOnline()) {
            return;
        }

        profile.sendMessage(CC.error("Vous êtes mort définitivement, vous ne pouvez être ressuscité qu'à l'aide de commandes administratives."));

        Collection<IProfile> profiles = API.get().getProfileHandler().getProfiles(IProfile.State.ALIVE);
        int groups = profiles.size() > 18 ? 5 : profiles.size() > 12 ? 4 : 3;

        API.get().getGameHandler().getConfiguration().setGroups(groups);

        IProfile killer = IProfile.of(this.profile.getPlayer().getKiller().getUniqueId());

        for (IProfile alive : profiles) {
            assert alive.getRole() != null;

            Consumer<IProfile> roleConsumer = alive.getRole().getRoleDeathHandlers().get(this.profile.getRole().getClass());
            if (roleConsumer != null) {
                roleConsumer.accept(killer);
            }

            Consumer<IProfile> teamConsumer = alive.getRole().getTeamDeathHandlers().get(this.profile.getTeam().getClass());
            if (teamConsumer == null) {
                return;
            }

            teamConsumer.accept(killer);
        }
    }
}
