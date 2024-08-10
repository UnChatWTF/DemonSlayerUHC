package eu.unchat.uhc.demonslayer.power.slayer.tanjiro;

import eu.unchat.uhc.power.AbstractPower;
import lombok.Getter;

@Getter
public final class SmellPower extends AbstractPower {
    private final String name;
    private final int initialCooldown, initialUses;

    public SmellPower() {
        this.name = "&a&lSMELL";
        this.initialCooldown = 20 * 60;
        this.initialUses = 3;
    }
}
