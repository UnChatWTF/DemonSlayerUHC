package eu.kurai.uhc.demonslayer.role.defaults.demon;

import eu.kurai.uhc.demonslayer.role.AbstractDSRole;
import eu.kurai.uhc.demonslayer.team.defaults.DemonTeam;
import eu.unchat.uhc.role.Role;
import lombok.Getter;
import org.bukkit.Material;

@Getter
@Role(name = "Kaigaku", identifier = "kaigaku", team = DemonTeam.class, material = Material.GLOWSTONE_DUST)
public final class KaigakuRole extends AbstractDSRole {

    private final Gender gender;
    private final Rank rank;

    public KaigakuRole() {
        this.gender = Gender.MALE;
        this.rank = Rank.A;
    }
}
