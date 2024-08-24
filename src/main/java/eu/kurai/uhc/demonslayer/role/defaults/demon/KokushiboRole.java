package eu.kurai.uhc.demonslayer.role.defaults.demon;

import eu.kurai.uhc.demonslayer.role.AbstractDSRole;
import eu.kurai.uhc.demonslayer.team.defaults.DemonTeam;
import eu.unchat.uhc.profile.IProfile;
import eu.unchat.uhc.role.Role;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.entity.Player;

@Getter
@Role(name = "Kokushibo", identifier = "kokushibo", team = DemonTeam.class, material = Material.STRING)
public final class KokushiboRole extends AbstractDSRole {

    private final Gender gender;
    private final Rank rank;

    public KokushiboRole() {
        this.gender = Gender.MALE;
        this.rank = Rank.S;
        this.registerKnownRole(MuzanRole.class);
    }

    @Override
    public void onDistribute(Player player) {
        IProfile profile = IProfile.of(player.getUniqueId());
        profile.getRegenerationData().setDelay(15);
        profile.getRegenerationData().setValue(1);
    }
}
