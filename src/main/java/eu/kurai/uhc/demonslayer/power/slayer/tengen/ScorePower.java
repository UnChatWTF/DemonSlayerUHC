package eu.kurai.uhc.demonslayer.power.slayer.tengen;

import eu.kurai.uhc.demonslayer.DSPlugin;
import eu.kurai.uhc.demonslayer.role.AbstractDSRole;
import eu.kurai.uhc.demonslayer.team.defaults.DemonTeam;
import eu.unchat.uhc.API;
import eu.unchat.uhc.power.defaults.AbstractCommandPower;
import eu.unchat.uhc.profile.IProfile;
import eu.unchat.uhc.util.CC;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

@Getter
public final class ScorePower extends AbstractCommandPower {

    private final String name, identifier, commandName;

    public ScorePower() {
        this.name = "&a&lScore";
        this.identifier = "score";
        this.commandName = "ds score";

        this.setInitialCooldown(10 * 60);
        this.setInitialUses(-1);
    }

    @Override
    public Result onCommand(Player player, String[] args) {
        if (args.length < 1) {
            player.sendMessage(CC.error("/ds score <pseudo>"));
            return Result.FAILED;
        }

        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            player.sendMessage(CC.error("Ce joueur n'existe pas."));
            return Result.FAILED;
        }

        IProfile profile = IProfile.of(target.getUniqueId());
        if (!profile.getState().equals(IProfile.State.ALIVE)) {
            player.sendMessage(CC.error("Ce joueur n'est pas en vie."));
            return Result.FAILED;
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
        return Result.SUCCESSFUL;
    }
}
