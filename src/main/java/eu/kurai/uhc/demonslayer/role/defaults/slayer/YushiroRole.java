package eu.kurai.uhc.demonslayer.role.defaults.slayer;

import eu.kurai.uhc.demonslayer.power.slayer.yushiro.*;
import eu.kurai.uhc.demonslayer.role.AbstractDSRole;
import eu.kurai.uhc.demonslayer.team.defaults.SlayerTeam;
import eu.unchat.uhc.power.defaults.AbstractParentPower;
import eu.unchat.uhc.profile.IProfile;
import eu.unchat.uhc.role.Role;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.entity.Player;

@Getter
@Role(name = "Yushiro", identifier = "yushiro", team = SlayerTeam.class, material = Material.SAPLING)
public final class YushiroRole extends AbstractDSRole {

    private final Gender gender;
    private final Rank rank;

    public YushiroRole() {
        this.gender = Gender.MALE;
        this.rank = Rank.B;

        registerKnownRole(TamayoRole.class);
        registerPower(new TalismanPower());
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

    @Getter
    private static final class TalismanPower extends AbstractParentPower {

        private final String name, identifier;

        public TalismanPower() {
            this.name = "&c&lSANGUINARY POWER";
            this.identifier = "sanguinary_power_yushiro";

            registerChild(new RevealTalismanPower());
            registerChild(new ControlTalismanPower());
            registerChild(new StrengthTalismanPower());
            registerChild(new ResistanceTalismanPower());
            registerChild(new SpeedTalismanPower());
        }
    }
}
