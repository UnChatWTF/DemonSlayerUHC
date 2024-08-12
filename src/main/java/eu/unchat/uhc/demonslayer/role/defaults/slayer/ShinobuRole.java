package eu.unchat.uhc.demonslayer.role.defaults.slayer;

import eu.unchat.uhc.demonslayer.power.slayer.shinobu.HexagonalFacetEyePower;
import eu.unchat.uhc.demonslayer.team.defaults.SlayerTeam;
import eu.unchat.uhc.profile.IProfile;
import eu.unchat.uhc.role.AbstractRole;
import eu.unchat.uhc.role.Role;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.entity.Player;

@Getter
@Role(name = "Shinobu", identifier = "shinobu", team = SlayerTeam.class, material = Material.RED_ROSE)
public final class ShinobuRole extends AbstractRole {
    private final String tolgeeReference;

    public ShinobuRole() {
        this.tolgeeReference = "fr.unchat.demonslayer.role.shinobu";

        registerPower(new HexagonalFacetEyePower());
    }

    @Override
    public void onDistribute(Player player) {
        IProfile profile = IProfile.of(player.getUniqueId());
        profile.setWeaknessBuffer(profile.getWeaknessBuffer() + 15);
    }
}
