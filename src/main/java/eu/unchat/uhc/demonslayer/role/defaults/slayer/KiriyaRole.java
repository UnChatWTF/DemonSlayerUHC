package eu.unchat.uhc.demonslayer.role.defaults.slayer;

import eu.unchat.uhc.demonslayer.team.defaults.SlayerTeam;
import eu.unchat.uhc.demonslayer.role.AbstractDSRole;
import eu.unchat.uhc.role.Role;
import lombok.Getter;
import org.bukkit.Material;

@Getter
@Role(name = "Kiriya", identifier = "kiriya", material = Material.EYE_OF_ENDER, team = SlayerTeam.class)
public final class KiriyaRole extends AbstractDSRole {

    private final Gender gender;

    public KiriyaRole() {
        this.gender = Gender.MALE;
    }
}
