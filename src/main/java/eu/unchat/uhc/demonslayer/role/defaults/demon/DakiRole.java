package eu.unchat.uhc.demonslayer.role.defaults.demon;

import eu.unchat.uhc.demonslayer.death.DakiGyutaroDeathOperation;
import eu.unchat.uhc.demonslayer.team.defaults.DemonTeam;
import eu.unchat.uhc.profile.IProfile;
import eu.unchat.uhc.role.AbstractRole;
import eu.unchat.uhc.role.Role;
import org.bukkit.Material;
import org.bukkit.entity.Player;

@Role(name = "Daki", identifier = "daki", team = DemonTeam.class, material = Material.SPIDER_EYE)
public final class DakiRole extends AbstractRole {
    public DakiRole() {
        registerKnownRole(MuzanRole.class);
    }

    @Override
    public void onDistribute(Player player) {
        IProfile profile = IProfile.of(player.getUniqueId());
        DakiGyutaroDeathOperation deathOperation = new DakiGyutaroDeathOperation(profile);
        deathOperation.setAnnounceDelay(2 * 60);
        profile.setDeathOperation(deathOperation);
    }
}
