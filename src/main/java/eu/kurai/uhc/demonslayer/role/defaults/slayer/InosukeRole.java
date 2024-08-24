package eu.kurai.uhc.demonslayer.role.defaults.slayer;

import eu.kurai.uhc.demonslayer.power.slayer.BreathPower;
import eu.kurai.uhc.demonslayer.power.slayer.inosuke.BoarChargePower;
import eu.kurai.uhc.demonslayer.power.slayer.inosuke.DetectionPower;
import eu.kurai.uhc.demonslayer.role.AbstractDSRole;
import eu.kurai.uhc.demonslayer.team.defaults.SlayerTeam;
import eu.unchat.uhc.profile.IProfile;
import eu.unchat.uhc.role.Role;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.entity.Player;


@Getter
@Role(name = "Inosuke", identifier = "inosuke", team = SlayerTeam.class, material = Material.PORK)
public final class InosukeRole extends AbstractDSRole {

    private final Gender gender;
    private final Rank rank;

    public InosukeRole() {
        this.gender = Gender.MALE;
        this.rank = Rank.S;

        registerPower(new BreathPower(this, new BoarChargePower(), new DetectionPower()));
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
