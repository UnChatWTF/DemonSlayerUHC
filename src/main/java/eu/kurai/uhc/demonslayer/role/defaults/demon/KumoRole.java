package eu.kurai.uhc.demonslayer.role.defaults.demon;

import eu.kurai.uhc.demonslayer.power.demon.SanguinaryPower;
import eu.kurai.uhc.demonslayer.power.demon.kumo.AcidCocoonPower;
import eu.kurai.uhc.demonslayer.power.demon.kumo.AcidManipulationPower;
import eu.kurai.uhc.demonslayer.role.AbstractDSRole;
import eu.kurai.uhc.demonslayer.team.defaults.DemonTeam;
import eu.unchat.uhc.role.Role;
import lombok.Getter;
import org.bukkit.Material;

@Getter
@Role(name = "Kumo", identifier = "kumo", team = DemonTeam.class, material = Material.STRING)
public final class KumoRole extends AbstractDSRole {

    private final Gender gender;
    private final Rank rank;

    public KumoRole() {
        this.gender = Gender.FEMALE;
        this.rank = Rank.C;

        registerKnownRole(MuzanRole.class);
        registerPower(new SanguinaryPower(this, new AcidCocoonPower(), new AcidManipulationPower()));
    }
}
