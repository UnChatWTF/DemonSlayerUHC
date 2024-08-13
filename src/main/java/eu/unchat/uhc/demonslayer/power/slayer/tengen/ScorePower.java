package eu.unchat.uhc.demonslayer.power.slayer.tengen;

import eu.unchat.uhc.API;
import eu.unchat.uhc.demonslayer.DSPlugin;
import eu.unchat.uhc.demonslayer.role.AbstractDSRole;
import eu.unchat.uhc.demonslayer.team.defaults.DemonTeam;
import eu.unchat.uhc.power.AbstractCommandPower;
import eu.unchat.uhc.profile.IProfile;
import eu.unchat.uhc.util.CC;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Arrays;

@Getter
public final class ScorePower extends AbstractCommandPower {

    private final String name, commandName;

    private final int initialCooldown, initialUses;

    public ScorePower() {
        this.name = "&a&lSCORE";
        this.commandName = "ds score";

        this.initialCooldown = 10 * 60;
        this.initialUses = -1;
    }

    @Override
    public boolean onRun(Player player, String[] args) {
        if (args.length < 2) {
            player.sendMessage(CC.error("/ds score <pseudo>"));
            return false;
        }

        Player target = Bukkit.getPlayer(args[1]);
        if (target == null) {
            player.sendMessage(CC.error("Ce joueur n'existe pas."));
            return false;
        }

        IProfile profile = IProfile.of(target.getUniqueId());
        if (!profile.getState().equals(IProfile.State.ALIVE)) {
            player.sendMessage(CC.error("Ce joueur n'est pas en vie."));
            return false;
        }

        player.sendMessage(CC.info("Vous écoutez " + profile.getName() + "."));

        int beats = API.get().getTeamHandler().isInTeam(profile, DemonTeam.class) ? 5 : 4;
        int pitch = ((AbstractDSRole) profile.getRole()).getGender().equals(AbstractDSRole.Gender.FEMALE) ? 2 : 0;

        new BukkitRunnable() {
            private int i = 0;

            @Override
            public void run() {
                i++;

                player.playSound(player.getLocation(), Sound.NOTE_PLING, 1, pitch);

                if (i == beats) {
                    cancel();
                }
            }
        }.runTaskTimerAsynchronously(DSPlugin.get(), 0, 30);
        return true;
    }
}
