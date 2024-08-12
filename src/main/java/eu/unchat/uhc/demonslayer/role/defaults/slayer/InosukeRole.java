package eu.unchat.uhc.demonslayer.role.defaults.slayer;

import eu.unchat.uhc.demonslayer.team.defaults.SlayerTeam;
import eu.unchat.uhc.profile.IProfile;
import eu.unchat.uhc.role.AbstractRole;
import eu.unchat.uhc.role.Role;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.entity.Player;


@Getter
@Role(name = "Inosuke", identifier = "inosuke", team = SlayerTeam.class, material = Material.PORK)
public final class InosukeRole extends AbstractRole {
    private final String tolgeeReference;

    public InosukeRole() {
        this.tolgeeReference = "fr.unchat.demonslayer.role.inosuke";
    }

    @Override
    public void onDay(Player player) {
        IProfile profile = IProfile.of(player.getUniqueId());
        profile.setResistanceBuffer(profile.getResistanceBuffer() - 20);
    }

    @Override
    public void onNight(Player player) {
        IProfile profile = IProfile.of(player.getUniqueId());
        profile.setResistanceBuffer(profile.getResistanceBuffer() + 20);
    }
}
