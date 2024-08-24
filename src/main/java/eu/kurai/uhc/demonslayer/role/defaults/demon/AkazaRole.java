package eu.kurai.uhc.demonslayer.role.defaults.demon;

import eu.kurai.uhc.demonslayer.role.AbstractDSRole;
import eu.unchat.uhc.profile.IProfile;
import lombok.Getter;
import org.bukkit.entity.Player;

@Getter
public final class AkazaRole extends AbstractDSRole {
    private final Gender gender;
    private final Rank rank;

    public AkazaRole() {
        this.gender = Gender.MALE;
        this.rank = Rank.A;

        registerKnownRole(MuzanRole.class);
    }

    @Override
    public void onDistribute(Player player) {
        IProfile profile = IProfile.of(player.getUniqueId());
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
