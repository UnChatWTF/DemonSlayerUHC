package eu.kurai.uhc.demonslayer.power.slayer.kagaya;

import eu.kurai.uhc.demonslayer.DSPlugin;
import eu.unchat.uhc.power.defaults.AbstractCommandPower;
import eu.unchat.uhc.profile.IProfile;
import eu.unchat.uhc.util.CC;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

@Getter
public final class CrowPower extends AbstractCommandPower {

    private final String name, commandName, identifier;

    public CrowPower() {
        this.name = "&a&lCorbeau";
        this.commandName = "ds crow";
        this.identifier = "crow";

        setInitialCooldown(10 * 60);
        setInitialUses(-1);
    }

    @Override
    public Result onCommand(Player player, String[] args) {
        if (args.length < 2) {
            player.sendMessage(CC.error("/ds crow <player> <message>"));
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

        String message = String.join(" ", args).replace(args[0] + " ", "");
        Bukkit.getScheduler().runTaskLaterAsynchronously(DSPlugin.get(), () -> {
            target.sendMessage(CC.translate("&8[&a&lCorbeau de Kagaya&8] &7" + message));
            player.sendMessage(CC.info("Le joueur " + target.getName() + " a bien reçu votre message."));
        }, getTime(player.getLocation(), target.getLocation()));
        return Result.SUCCESSFUL;
    }

    private int getTime(final Location a, final Location b) {
        int i = (int) (a.distance(b) * 20 / 10);
        System.out.println("Kagaya crow time is " + i + " ticks");
        return i;
    }
}
