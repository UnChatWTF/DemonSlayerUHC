package eu.kurai.uhc.demonslayer.role.defaults.demon;

import eu.kurai.uhc.demonslayer.death.DakiGyutaroDeathOperation;
import eu.kurai.uhc.demonslayer.power.demon.daki.ObiSashPower;
import eu.kurai.uhc.demonslayer.role.AbstractDSRole;
import eu.kurai.uhc.demonslayer.team.defaults.DemonTeam;
import eu.unchat.uhc.profile.IProfile;
import eu.unchat.uhc.role.Role;
import lombok.Getter;
import lombok.SneakyThrows;
import org.bukkit.Material;
import org.bukkit.entity.Player;

@Getter
@Role(name = "Daki", identifier = "daki", team = DemonTeam.class, material = Material.BARRIER)
public final class DakiRole extends AbstractDSRole {

    private final Gender gender;
    private final Rank rank;

    public DakiRole() {
        this.gender = Gender.FEMALE;
        this.rank = Rank.A;

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
