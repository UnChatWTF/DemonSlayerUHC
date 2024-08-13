package eu.unchat.uhc.demonslayer.role.defaults.slayer;

import eu.unchat.uhc.demonslayer.power.slayer.tanjiro.BlueParadisePower;
import eu.unchat.uhc.demonslayer.power.slayer.tanjiro.SmellPower;
import eu.unchat.uhc.demonslayer.power.slayer.tanjiro.SwirlPower;
import eu.unchat.uhc.demonslayer.team.defaults.SlayerTeam;
import eu.unchat.uhc.power.item.AbstractParentPower;
import eu.unchat.uhc.demonslayer.role.AbstractDSRole;
import eu.unchat.uhc.role.Role;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.entity.Player;

@Getter
@Role(name = "Tanjiro", identifier = "tanjiro", team = SlayerTeam.class, material = Material.BLAZE_ROD)
public final class TanjiroRole extends AbstractDSRole {

    private final Gender gender;

    public TanjiroRole() {
        this.gender = Gender.MALE;

        registerPower(new SmellPower());
        registerPower(new IntegralConcentrationBreathPower());
    }

    @Getter
    private static final class IntegralConcentrationBreathPower extends AbstractParentPower {
        private final String name;

        public IntegralConcentrationBreathPower() {
            this.name = "&a&lSOUFFLE";
            registerChild(new SwirlPower());
            registerChild(new BlueParadisePower());
        }
    }
}
