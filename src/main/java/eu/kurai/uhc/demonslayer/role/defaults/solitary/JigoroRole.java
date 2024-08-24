package eu.kurai.uhc.demonslayer.role.defaults.solitary;

import eu.kurai.uhc.demonslayer.power.other.DivineSpeedPower;
import eu.kurai.uhc.demonslayer.power.solitary.jigoro.PactPower;
import eu.kurai.uhc.demonslayer.role.AbstractDSRole;
import eu.kurai.uhc.demonslayer.team.defaults.SolitaryTeam;
import eu.unchat.uhc.power.defaults.AbstractItemPower;
import eu.unchat.uhc.profile.IProfile;
import eu.unchat.uhc.role.Role;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.entity.Player;

@Getter
@Role(name = "Jigoro", identifier = "jigoro", team = SolitaryTeam.class, material = Material.GLOWSTONE_DUST)
public final class JigoroRole extends AbstractDSRole {

    private final Gender gender;
    private final Rank rank;

    public JigoroRole() {
        this.gender = Gender.MALE;
        this.rank = Rank.A;
        registerPower(new DivineSpeedPower());
        ((AbstractItemPower) getPower(DivineSpeedPower.class)).setInitialUses(-1);
        registerPower(new PactPower());
    }

    @Override
    public void onDistribute(Player player) {
        IProfile profile = IProfile.of(player.getUniqueId());
        profile.setResistanceBuffer(profile.getResistanceBuffer() + 20);
        profile.setSpeedBuffer(profile.getSpeedBuffer() + 0.04F);
    }
}
