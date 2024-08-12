package eu.unchat.uhc.demonslayer.role.defaults.slayer;

import eu.unchat.uhc.demonslayer.power.slayer.yushiro.*;
import eu.unchat.uhc.demonslayer.team.defaults.SlayerTeam;
import eu.unchat.uhc.power.AbstractParentPower;
import eu.unchat.uhc.profile.IProfile;
import eu.unchat.uhc.role.AbstractRole;
import eu.unchat.uhc.role.Role;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.entity.Player;

@Getter
@Role(name = "Yushiro", identifier = "yushiro", team = SlayerTeam.class, material = Material.SAPLING)
public final class YushiroRole extends AbstractRole {

    private final String tolgeeReference;

    public YushiroRole() {
        this.tolgeeReference = "fr.unchat.demonslayer.role.yushiro";
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

        private final String name;

        public TalismanPower() {
            this.name = "&c&lSANGUINARY POWER";
            registerChild(new RevealTalismanPower());
            registerChild(new ControlTalismanPower());
            registerChild(new StrengthTalismanPower());
            registerChild(new ResistanceTalismanPower());
            registerChild(new SpeedTalismanPower());
        }
    }
}
