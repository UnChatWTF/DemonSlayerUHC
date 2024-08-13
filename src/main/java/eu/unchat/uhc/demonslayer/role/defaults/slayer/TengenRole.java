package eu.unchat.uhc.demonslayer.role.defaults.slayer;

import eu.unchat.uhc.demonslayer.power.slayer.tengen.ScorePower;
import eu.unchat.uhc.demonslayer.power.slayer.tengen.ThunderPower;
import eu.unchat.uhc.demonslayer.team.defaults.SlayerTeam;
import eu.unchat.uhc.demonslayer.role.AbstractDSRole;
import eu.unchat.uhc.role.Role;
import lombok.Getter;
import org.bukkit.Material;

@Getter
@Role(name = "Tengen", identifier = "tengen", team = SlayerTeam.class, material = Material.SNOW_BALL)
public final class TengenRole extends AbstractDSRole {

    private final Gender gender;

    public TengenRole() {
        this.gender = Gender.MALE;

        registerPower(new ThunderPower());
        registerPower(new ScorePower());
    }
}
