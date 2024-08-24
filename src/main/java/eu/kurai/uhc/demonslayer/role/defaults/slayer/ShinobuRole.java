package eu.kurai.uhc.demonslayer.role.defaults.slayer;

import eu.kurai.uhc.demonslayer.power.slayer.shinobu.HexagonalFacetEyePower;
import eu.kurai.uhc.demonslayer.role.AbstractDSRole;
import eu.kurai.uhc.demonslayer.team.defaults.SlayerTeam;
import eu.unchat.uhc.profile.IProfile;
import eu.unchat.uhc.role.Role;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.entity.Player;

@Getter
@Role(name = "Shinobu", identifier = "shinobu", team = SlayerTeam.class, material = Material.RED_ROSE)
public final class ShinobuRole extends AbstractDSRole {

    private final Gender gender;
    private final Rank rank;

    public ShinobuRole() {
        this.gender = Gender.FEMALE;
        this.rank = Rank.B;

        registerPower(new HexagonalFacetEyePower());
    }

    @Override
    public void onDistribute(Player player) {
        IProfile profile = IProfile.of(player.getUniqueId());
        profile.setWeaknessBuffer(profile.getWeaknessBuffer() + 15);
    }
}
