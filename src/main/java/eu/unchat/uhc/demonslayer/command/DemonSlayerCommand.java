package eu.unchat.uhc.demonslayer.command;import dev.rollczi.litecommands.annotations.command.Command;
import dev.rollczi.litecommands.annotations.context.Context;
import dev.rollczi.litecommands.annotations.execute.Execute;
import eu.unchat.uhc.API;
import eu.unchat.uhc.cycle.Cycle;
import eu.unchat.uhc.demonslayer.command.validator.role.HasRole;
import eu.unchat.uhc.demonslayer.role.defaults.slayer.TanjiroRole;
import eu.unchat.uhc.power.AbstractPower;
import eu.unchat.uhc.profile.IProfile;
import eu.unchat.uhc.role.AbstractRole;
import eu.unchat.uhc.util.CC;
import org.bukkit.entity.Player;

@Command(name = "demonslayer", aliases = {"ds"})
public final class DemonSlayerCommand {

    @Execute(name = "effect")
    void executeEffect(final @Context Player player) {
        player.performCommand("effect");
    }

    @Execute(name = "flair")
    void executeFlair(final @Context @HasRole(TanjiroRole.class) Player player) {
        IProfile profile = IProfile.of(player.getUniqueId());
        AbstractRole role = profile.getRole();
        AbstractPower power = role.getPower(TanjiroRole.FlairPower.class);

        if (power == null) {
            return;
        }

        if (power.getState() == AbstractPower.State.LOCKED) {
            player.sendMessage(CC.error("Ce pouvoir est bloqué."));
            return;
        }

        if (power.getUses() >= power.getInitialUses()) {
            player.sendMessage(CC.error("Vous ne pouvez plus utiliser ce pouvoir."));
            return;
        }

        if (power.getCooldown().isRunning()) {
            player.sendMessage(CC.error("Veuillez patienter " + power.getCooldown().getDuration() + "s avant de réutiliser ce pouvoir."));
            return;
        }

        if (API.get().getCycleHandler().getCurrentCycle() != Cycle.NIGHT) {
            player.sendMessage(CC.translate("&cCe pouvoir n'est utilisable que la nuit."));
            return;
        }

        if (power.getInitialUses() != -1) {
            power.setUses(power.getUses() + 1);
        }

        if (power.getInitialCooldown() != -1) {
            power.getCooldown().start(player);
        }

        player.sendMessage(CC.translate("&cTest !"));
    }

}
