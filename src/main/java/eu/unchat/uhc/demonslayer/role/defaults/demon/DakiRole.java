package eu.unchat.uhc.demonslayer.role.defaults.demon;

import eu.unchat.uhc.demonslayer.death.DakiGyutaroDeathOperation;
import eu.unchat.uhc.demonslayer.power.demon.daki.ObiSashPower;
import eu.unchat.uhc.demonslayer.team.defaults.DemonTeam;
import eu.unchat.uhc.profile.IProfile;
import eu.unchat.uhc.demonslayer.role.AbstractDSRole;
import eu.unchat.uhc.role.Role;
import lombok.Getter;
import lombok.SneakyThrows;
import org.bukkit.Material;
import org.bukkit.entity.Player;

@Getter
@Role(name = "Daki", identifier = "daki", team = DemonTeam.class, material = Material.BARRIER)
public final class DakiRole extends AbstractDSRole {

    private final Gender gender;

    public DakiRole() {
        this.gender = Gender.FEMALE;

        registerKnownRole(MuzanRole.class);
        registerKnownRole(GyutaroRole.class);

        registerPower(new ObiSashPower());
    }

    @Override
    @SneakyThrows
    public void onDistribute(Player player) {
        IProfile profile = IProfile.of(player.getUniqueId());
        DakiGyutaroDeathOperation deathOperation = new DakiGyutaroDeathOperation(profile);
        deathOperation.setAnnounceDelay(2 * 60);
        profile.setDeathOperation(deathOperation);
    }
}
