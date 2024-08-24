package eu.kurai.uhc.demonslayer.role.defaults.slayer;

import eu.kurai.uhc.demonslayer.power.slayer.nezuko.BurningBloodPower;
import eu.kurai.uhc.demonslayer.role.AbstractDSRole;
import eu.kurai.uhc.demonslayer.team.defaults.SlayerTeam;
import eu.unchat.uhc.profile.IProfile;
import eu.unchat.uhc.role.Role;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.entity.Player;

@Getter
@Role(name = "Nezuko", identifier = "nezuko", team = SlayerTeam.class, material = Material.FERMENTED_SPIDER_EYE)
public final class NezukoRole extends AbstractDSRole {

    private final Gender gender;
    private final Rank rank;

    public NezukoRole() {
        this.gender = Gender.FEMALE;
        this.rank = Rank.A;

        registerKnownRole(TanjiroRole.class);
        registerPower(new BurningBloodPower());
    }

    @Override
    public void onDay(Player player) {
        IProfile profile = IProfile.of(player.getUniqueId());
        profile.setStrengthBuffer(profile.getStrengthBuffer() - 15);
    }

    @Override
    public void onNight(Player player) {
        IProfile profile = IProfile.of(player.getUniqueId());
        profile.setStrengthBuffer(profile.getStrengthBuffer() + 15);
    }
}
