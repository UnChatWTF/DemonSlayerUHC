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

@Role(name = "Zenitsu", identifier = "zenitsu", team = SlayerTeam.class, material = Material.BLAZE_ROD)
public final class ZenitsuRole extends AbstractRole {
    public ZenitsuRole() {
        registerPower(new SoufflePower());
    }

    @Override
    public void onDistribute(Player player) {
        IProfile profile = IProfile.of(player.getUniqueId());
        profile.setSpeedBuffer(profile.getSpeedBuffer() + 0.04F);
    }

    @Override
    public String getDescription(Player player) {
        return "<dark_aqua>»</dark_aqua><st><dark_gray>------------------------------</dark_gray></st><dark_aqua>«</dark_aqua><newline><newline>▪ <dark_aqua><b>Demon Slayer UHC</b></dark_aqua><newline> <dark_gray>❘</dark_gray> <gray>Rôle:</gray> Vous êtes <dark_green>Zenitsu</dark_green>.<newline> <dark_gray>❘</dark_gray> <gray>Camp:</gray> <dark_green>Slayers</dark_green>.<newline><newline> <dark_gray>❘</dark_gray> <gray>Passifs:</gray> Vous possédez l'effet <aqua>Vitesse I</aqua>.<newline>  Vous obtenez l'effet <red>Force I</red> entre <red>7.5❤</red> et <red>4.5❤</red> ainsi que l'effet <aqua>Vitesse II</aqua> entre <red>4❤</red> et <red>0.5❤</red>.<newline><newline> <dark_gray>❘</dark_gray> <dark_green><hover:show_text:\"<dark_green>DIVINE SPEED</dark_green> <dark_gray>≫</dark_gray> <gray>Capacité</gray><newline><newline><dark_gray>[</dark_gray><gray>Clic-droit</gray><dark_gray>]</dark_gray> : Vous permet d'obtenir l'effet <aqua>Vitesse III</aqua> pendant une minute.<newline>Cependant, vous ne pourrez plus recevoir l'effet <red>Force I</red>.<newline>A la fin de ce laps de temps, vous obtenez l'effet <dark_gray>Lenteur II</dark_gray> durant <gray>2 minutes</gray>.<newline><newline>• <gray>Délai d'activation</gray> : <dark_aqua>20 minutes</dark_aqua><newline>• <gray>Utilisations</gray> : <dark_aqua>2</dark_aqua>\">DIVINE SPEED</hover></dark_green> <gray>(</gray><dark_aqua>«</dark_aqua> <gray>Survole</gray> <dark_aqua>»</dark_aqua><gray>)</gray><newline> <dark_gray>❘</dark_gray> <dark_green><hover:show_text:\"<dark_green>GOD OF CELESTIAL FIRE</dark_green> <dark_gray>≫</dark_gray> <gray>Capacité</gray><newline><newline><dark_gray>[</dark_gray><gray>Clic-droit</gray><dark_gray>]</dark_gray> : Vous faites tomber des <yellow>éclairs</yellow> sur tous les joueurs dans un rayon de <gray>30 blocs</gray>,<newline> infligeant <red>2❤</red> de dégâts et les <gold>enflammant</gold> pendant <gray>8 secondes</gray>.<newline><newline>• <gray>Utilisations</gray> : <dark_aqua>1</dark_aqua>\">GOD OF CELESTIAL FIRE</hover></dark_green> <gray>(</gray><dark_aqua>«</dark_aqua> <gray>Survole</gray> <dark_aqua>»</dark_aqua><gray>)</gray><newline><newline><dark_aqua>»</dark_aqua><st><dark_gray>------------------------------</dark_gray></st><dark_aqua>«</dark_aqua>";
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
