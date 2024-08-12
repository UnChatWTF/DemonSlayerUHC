package eu.unchat.uhc.demonslayer.role.defaults.slayer;

import eu.unchat.uhc.demonslayer.power.slayer.nezuko.BurningBloodPower;
import eu.unchat.uhc.demonslayer.team.defaults.SlayerTeam;
import eu.unchat.uhc.profile.IProfile;
import eu.unchat.uhc.role.AbstractRole;
import eu.unchat.uhc.role.Role;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.entity.Player;

@Getter
@Role(name = "Nezuko", identifier = "nezuko", team = SlayerTeam.class, material = Material.FERMENTED_SPIDER_EYE)
public final class NezukoRole extends AbstractRole {
    private final String tolgeeReference;

    public NezukoRole() {
        this.tolgeeReference = "fr.unchat.demonslayer.role.nezuko";

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
