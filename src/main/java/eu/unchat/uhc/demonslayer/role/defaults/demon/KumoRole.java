package eu.unchat.uhc.demonslayer.role.defaults.demon;

import eu.unchat.uhc.demonslayer.power.demon.kumo.AcidCocoonPower;
import eu.unchat.uhc.demonslayer.power.demon.kumo.AcidManipulationPower;
import eu.unchat.uhc.demonslayer.team.defaults.DemonTeam;
import eu.unchat.uhc.power.AbstractParentPower;
import eu.unchat.uhc.role.AbstractRole;
import eu.unchat.uhc.role.Role;
import lombok.Getter;
import org.bukkit.Material;

@Getter
@Role(name = "Kumo", identifier = "kumo", team = DemonTeam.class, material = Material.STRING)
public final class KumoRole extends AbstractRole {
    private final String tolgeeReference;

    public KumoRole() {
        this.tolgeeReference = "fr.unchat.demonslayer.role.kumo";

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
