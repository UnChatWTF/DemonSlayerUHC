package eu.kurai.uhc.demonslayer.role.defaults.slayer;

import eu.kurai.uhc.demonslayer.role.AbstractDSRole;
import eu.kurai.uhc.demonslayer.team.defaults.SlayerTeam;
import eu.unchat.uhc.role.Role;
import lombok.Getter;
import org.bukkit.Material;

@Getter
@Role(name = "Kiriya", identifier = "kiriya", material = Material.EYE_OF_ENDER, team = SlayerTeam.class)
public final class KiriyaRole extends AbstractDSRole {

    private final Gender gender;
    private final Rank rank;

    public KiriyaRole() {
        this.gender = Gender.MALE;
        this.rank = Rank.C;

        registerKnownRole(KagayaRole.class);
    }
}
