package eu.kurai.uhc.demonslayer.power.demon;

import eu.unchat.uhc.power.defaults.AbstractItemPower;
import eu.unchat.uhc.power.defaults.AbstractParentPower;
import eu.unchat.uhc.role.AbstractRole;
import lombok.Getter;

@Getter
public final class SanguinaryPower extends AbstractParentPower {

    private final String name, identifier;

    public SanguinaryPower(final AbstractRole role, final AbstractItemPower... powers) {
        this.name = "&c&lPouvoir sanguinaire";
        this.identifier = "sanguinary_power_" + role.getIdentifier();

        for (AbstractItemPower power : powers) {
            registerChild(power);
        }
    }
}
