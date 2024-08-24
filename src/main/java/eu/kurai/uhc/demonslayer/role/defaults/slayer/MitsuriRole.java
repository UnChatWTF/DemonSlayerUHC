package eu.kurai.uhc.demonslayer.role.defaults.slayer;

import eu.kurai.uhc.demonslayer.role.AbstractDSRole;
import eu.kurai.uhc.demonslayer.team.defaults.SlayerTeam;
import eu.unchat.uhc.role.Role;
import lombok.Getter;
import org.bukkit.Material;

@Getter
@Role(name = "Mitsuri", identifier = "mitsuri", team = SlayerTeam.class, material = Material.RED_ROSE)
public final class MitsuriRole extends AbstractDSRole {

    private final Gender gender;
    private final Rank rank;

    public MitsuriRole() {
        this.gender = Gender.FEMALE;
        this.rank = Rank.A;
    }
}
