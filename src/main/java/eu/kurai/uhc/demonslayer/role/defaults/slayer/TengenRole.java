package eu.kurai.uhc.demonslayer.role.defaults.slayer;

import eu.kurai.uhc.demonslayer.power.slayer.tengen.ScorePower;
import eu.kurai.uhc.demonslayer.power.slayer.tengen.ThunderPower;
import eu.kurai.uhc.demonslayer.role.AbstractDSRole;
import eu.kurai.uhc.demonslayer.team.defaults.SlayerTeam;
import eu.unchat.uhc.role.Role;
import lombok.Getter;
import org.bukkit.Material;

@Getter
@Role(name = "Tengen", identifier = "tengen", team = SlayerTeam.class, material = Material.SNOW_BALL)
public final class TengenRole extends AbstractDSRole {

    private final Gender gender;
    private final Rank rank;

    public TengenRole() {
        this.gender = Gender.MALE;
        this.rank = Rank.B;

        registerPower(new ThunderPower());
        registerPower(new ScorePower());
    }
}
