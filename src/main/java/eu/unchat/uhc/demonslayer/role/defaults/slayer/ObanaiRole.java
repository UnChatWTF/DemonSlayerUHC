package eu.unchat.uhc.demonslayer.role.defaults.slayer;

import eu.unchat.uhc.demonslayer.power.slayer.obanai.SerpentRipplesPower;
import eu.unchat.uhc.demonslayer.team.defaults.SlayerTeam;
import eu.unchat.uhc.role.AbstractRole;
import eu.unchat.uhc.role.Role;
import lombok.Getter;
import org.bukkit.Material;

@Getter
@Role(name = "Obanai", identifier = "obanai", team = SlayerTeam.class, material = Material.INK_SACK, data = 2)
public final class ObanaiRole extends AbstractRole {
    public ObanaiRole() {
        registerPower(new SerpentRipplesPower());
    }
}
