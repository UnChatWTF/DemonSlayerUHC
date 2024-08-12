package eu.unchat.uhc.demonslayer.role.defaults.slayer;

import eu.unchat.uhc.demonslayer.power.slayer.zenitsu.DivineSpeedPower;
import eu.unchat.uhc.demonslayer.power.slayer.zenitsu.GodOfCelestialFirePower;
import eu.unchat.uhc.demonslayer.team.defaults.SlayerTeam;
import eu.unchat.uhc.power.AbstractParentPower;
import eu.unchat.uhc.profile.IProfile;
import eu.unchat.uhc.role.AbstractRole;
import eu.unchat.uhc.role.Role;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.entity.Player;

@Getter
@Role(name = "Zenitsu", identifier = "zenitsu", team = SlayerTeam.class, material = Material.GLOWSTONE_DUST)
public final class ZenitsuRole extends AbstractRole {
    private final String tolgeeReference;

    public ZenitsuRole() {
        this.tolgeeReference = "fr.unchat.demonslayer.role.zenitsu";
        registerPower(new SoufflePower());
    }

    @Override
    public void onDistribute(Player player) {
        IProfile profile = IProfile.of(player.getUniqueId());
        profile.setSpeedBuffer(profile.getSpeedBuffer() + 0.04F);
    }

    @Getter
    private static final class SoufflePower extends AbstractParentPower {
        private final String name;

        public SoufflePower() {
            this.name = "&a&lSOUFFLE";
            registerChild(new DivineSpeedPower());
            registerChild(new GodOfCelestialFirePower());
        }
    }
}
