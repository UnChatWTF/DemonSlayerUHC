package eu.kurai.uhc.demonslayer.role.defaults.demon;

import eu.kurai.uhc.demonslayer.power.demon.SanguinaryPower;
import eu.kurai.uhc.demonslayer.power.demon.doma.DivineCrystalChildPower;
import eu.kurai.uhc.demonslayer.power.demon.doma.FrostCloudPower;
import eu.kurai.uhc.demonslayer.power.demon.doma.IceRainPower;
import eu.kurai.uhc.demonslayer.role.AbstractDSRole;
import eu.kurai.uhc.demonslayer.team.defaults.DemonTeam;
import eu.unchat.uhc.profile.IProfile;
import eu.unchat.uhc.role.Role;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.entity.Player;

@Getter
@Role(name = "Doma", identifier = "doma", team = DemonTeam.class, material = Material.PACKED_ICE)
public final class DomaRole extends AbstractDSRole {

    private final Gender gender;
    private final Rank rank;

    public DomaRole() {
        this.gender = Gender.MALE;
        this.rank = Rank.A;
        registerKnownRole(MuzanRole.class);
        registerPower(new SanguinaryPower(this, new DivineCrystalChildPower(), new FrostCloudPower(), new IceRainPower()));
    }

    @Override
    public void onDistribute(Player player) {
        final IProfile profile = IProfile.of(player.getUniqueId());
        profile.getRegenerationData().setDelay(20);
        profile.getRegenerationData().setValue(1);
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
