package eu.kurai.uhc.demonslayer.power.slayer;

import eu.unchat.uhc.power.defaults.AbstractItemPower;
import eu.unchat.uhc.power.defaults.AbstractParentPower;
import eu.unchat.uhc.role.AbstractRole;
import lombok.Getter;

@Getter
public final class BreathPower extends AbstractParentPower {

    private final String name, identifier;

    public BreathPower(final AbstractRole role, final AbstractItemPower... powers) {
        this.name = "&a&lSouffle";
        this.identifier = "souffle_" + role.getIdentifier();

        for (AbstractItemPower power : powers) {
            registerChild(power);
        }
    }
}
