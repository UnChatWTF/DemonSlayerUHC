package eu.kurai.uhc.demonslayer.power.slayer.tanjiro;

import eu.unchat.uhc.API;
import eu.unchat.uhc.cycle.Cycle;
import eu.unchat.uhc.power.defaults.AbstractCommandPower;
import eu.unchat.uhc.util.CC;
import lombok.Getter;
import org.bukkit.entity.Player;

@Getter
public final class SmellPower extends AbstractCommandPower {
    private final String name, identifier, commandName;


    public SmellPower() {
        this.name = "&a&lSentir";
        this.identifier = "smell";
        this.commandName = "ds smell";

        this.setInitialCooldown(20 * 60);
        this.setInitialUses(3);
    }

    @Override
    public Result onCommand(final Player player, final String[] args) {
        if (API.get().getCycleHandler().getCurrentCycle() != Cycle.NIGHT) {
            player.sendMessage(CC.translate("&cCe pouvoir n'est utilisable que la nuit."));
            return Result.FAILED;
        }

        return Result.SUCCESSFUL;
    }
}
