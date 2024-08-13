package eu.unchat.uhc.demonslayer.role.defaults.demon;

import eu.unchat.uhc.demonslayer.power.demon.kumo.AcidCocoonPower;
import eu.unchat.uhc.demonslayer.power.demon.kumo.AcidManipulationPower;
import eu.unchat.uhc.demonslayer.team.defaults.DemonTeam;
import eu.unchat.uhc.power.item.AbstractParentPower;
import eu.unchat.uhc.demonslayer.role.AbstractDSRole;
import eu.unchat.uhc.role.Role;
import lombok.Getter;
import org.bukkit.Material;

@Getter
@Role(name = "Kumo", identifier = "kumo", team = DemonTeam.class, material = Material.STRING)
public final class KumoRole extends AbstractDSRole {

    private final Gender gender;

    public KumoRole() {
        this.gender = Gender.FEMALE;

        registerKnownRole(MuzanRole.class);
        registerPower(new SanguinaryPower());
    }

    @Getter
    private static final class SanguinaryPower extends AbstractParentPower {
        private final String name;

        public SanguinaryPower() {
            this.name = "&c&lSANGUINARY POWER";
            this.registerChild(new AcidCocoonPower());
            this.registerChild(new AcidManipulationPower());
        }
    }
}
