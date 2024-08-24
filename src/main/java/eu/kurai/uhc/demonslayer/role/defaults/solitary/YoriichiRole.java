package eu.kurai.uhc.demonslayer.role.defaults.solitary;

import eu.kurai.uhc.demonslayer.power.other.TransparentWorldPower;
import eu.kurai.uhc.demonslayer.power.solitary.yoriichi.SunBreathPower;
import eu.kurai.uhc.demonslayer.role.AbstractDSRole;
import eu.kurai.uhc.demonslayer.team.defaults.SolitaryTeam;
import eu.unchat.uhc.profile.IProfile;
import eu.unchat.uhc.role.Role;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.entity.Player;

@Getter
@Role(name = "Yoriichi", identifier = "yoriichi", material = Material.GOLD_SWORD, team = SolitaryTeam.class)
public final class YoriichiRole extends AbstractDSRole {

    private final Gender gender;
    private final Rank rank;

    public YoriichiRole() {
        this.gender = Gender.MALE;
        this.rank = Rank.S;
        this.registerPower(new TransparentWorldPower());
        this.registerPower(new SunBreathPower());
    }

    @Override
    public void onDistribute(Player player) {
        IProfile profile = IProfile.of(player.getUniqueId());
        profile.setStrengthBuffer(profile.getStrengthBuffer() + 15);
        profile.setSpeedBuffer(profile.getSpeedBuffer() + 0.04F);
    }

    @Override
    public void onDay(Player player) {
        IProfile profile = IProfile.of(player.getUniqueId());
        profile.setResistanceBuffer(profile.getResistanceBuffer() - 15);
    }

    @Override
    public void onNight(Player player) {
        IProfile profile = IProfile.of(player.getUniqueId());
        profile.setResistanceBuffer(profile.getResistanceBuffer() + 15);
    }
}
