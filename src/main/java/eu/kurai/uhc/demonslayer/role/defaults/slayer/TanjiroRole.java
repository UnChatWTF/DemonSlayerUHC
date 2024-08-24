package eu.kurai.uhc.demonslayer.role.defaults.slayer;

import eu.kurai.uhc.demonslayer.power.slayer.BreathPower;
import eu.kurai.uhc.demonslayer.power.slayer.tanjiro.BlueParadisePower;
import eu.kurai.uhc.demonslayer.power.slayer.tanjiro.SmellPower;
import eu.kurai.uhc.demonslayer.power.slayer.tanjiro.SwirlPower;
import eu.kurai.uhc.demonslayer.role.AbstractDSRole;
import eu.kurai.uhc.demonslayer.team.defaults.SlayerTeam;
import eu.unchat.uhc.role.Role;
import lombok.Getter;
import org.bukkit.Material;

@Getter
@Role(name = "Tanjiro", identifier = "tanjiro", team = SlayerTeam.class, material = Material.BLAZE_ROD)
public final class TanjiroRole extends AbstractDSRole {

    private final Gender gender;
    private final Rank rank;

    public TanjiroRole() {
        this.gender = Gender.MALE;
        this.rank = Rank.A;

        registerPower(new SmellPower());
        registerPower(new BreathPower(this, new BlueParadisePower(), new SwirlPower()));
    }
}
