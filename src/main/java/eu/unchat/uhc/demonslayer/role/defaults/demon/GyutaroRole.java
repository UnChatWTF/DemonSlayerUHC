package eu.unchat.uhc.demonslayer.role.defaults.demon;

import eu.unchat.uhc.demonslayer.death.DakiGyutaroDeathOperation;
import eu.unchat.uhc.demonslayer.power.demon.gyutaro.BloodManipulationPower;
import eu.unchat.uhc.demonslayer.team.defaults.DemonTeam;
import eu.unchat.uhc.profile.IProfile;
import eu.unchat.uhc.role.AbstractRole;
import eu.unchat.uhc.role.Role;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.entity.Player;

@Getter
@Role(name = "Gyutaro", identifier = "gyutaro", team = DemonTeam.class, material = Material.DIAMOND_HOE)
public final class GyutaroRole extends AbstractRole {
    private final String tolgeeReference;

    public GyutaroRole() {
        this.tolgeeReference = "fr.unchat.demonslayer.role.gyutaro";

        registerKnownRole(MuzanRole.class);
        registerKnownRole(DakiRole.class);
        registerPower(new BloodManipulationPower());
    }

    @Override
    public void onDistribute(Player player) {
        IProfile profile = IProfile.of(player.getUniqueId());
        DakiGyutaroDeathOperation deathOperation = new DakiGyutaroDeathOperation(profile);
        deathOperation.setAnnounceDelay(2 * 60);
        profile.setDeathOperation(deathOperation);
    }
}
