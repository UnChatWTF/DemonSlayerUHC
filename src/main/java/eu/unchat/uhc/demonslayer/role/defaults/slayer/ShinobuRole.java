package eu.unchat.uhc.demonslayer.role.defaults.slayer;

import eu.unchat.uhc.demonslayer.power.slayer.shinobu.HexagonalFacetEyePower;
import eu.unchat.uhc.demonslayer.team.defaults.SlayerTeam;
import eu.unchat.uhc.role.AbstractRole;
import eu.unchat.uhc.role.Role;
import org.bukkit.Material;

@Role(name = "Shinobu", identifier = "shinobu", team = SlayerTeam.class, material = Material.RED_ROSE)
public final class ShinobuRole extends AbstractRole {
    public ShinobuRole() {
        registerPower(new HexagonalFacetEyePower());
    }
}
