package eu.unchat.uhc.demonslayer.role.defaults.slayer;

import eu.unchat.uhc.demonslayer.team.defaults.SlayerTeam;
import eu.unchat.uhc.role.AbstractRole;
import eu.unchat.uhc.role.Role;
import lombok.Getter;
import org.bukkit.Material;

@Getter
@Role(name = "Kiriya", identifier = "kiriya", material = Material.EYE_OF_ENDER, team = SlayerTeam.class)
public final class KiriyaRole extends AbstractRole {

    private final String tolgeeReference;

    public KiriyaRole() {
        this.tolgeeReference = "fr.unchat.demonslayer.role.kiriya";
    }
}
