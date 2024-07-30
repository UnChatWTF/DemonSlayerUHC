package eu.unchat.uhc.demonslayer.role.defaults.demon;

import eu.unchat.uhc.demonslayer.team.defaults.SlayerTeam;
import eu.unchat.uhc.power.AbstractPower;
import eu.unchat.uhc.role.AbstractRole;
import eu.unchat.uhc.role.Role;
import lombok.Getter;

@Role(name = "Muzan", identifier = "muzan", team = SlayerTeam.class)
public final class MuzanRole extends AbstractRole {
    public MuzanRole() {
    }

    @Getter
    public static final class BloodGiftPower extends AbstractPower {
        private final String name;

        private final int initialCooldown, initialUses;

        public BloodGiftPower() {
            this.name = "&c&lDon de sang";

            this.initialCooldown = 0;
            this.initialUses = 2;
        }
    }
}
