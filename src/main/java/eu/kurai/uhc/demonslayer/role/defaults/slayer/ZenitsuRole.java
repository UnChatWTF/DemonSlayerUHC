package eu.kurai.uhc.demonslayer.role.defaults.slayer;

import eu.kurai.uhc.demonslayer.power.other.DivineSpeedPower;
import eu.kurai.uhc.demonslayer.power.slayer.BreathPower;
import eu.kurai.uhc.demonslayer.power.slayer.zenitsu.GodOfCelestialFirePower;
import eu.kurai.uhc.demonslayer.power.slayer.zenitsu.HearPower;
import eu.kurai.uhc.demonslayer.role.AbstractDSRole;
import eu.kurai.uhc.demonslayer.team.defaults.SlayerTeam;
import eu.unchat.uhc.profile.IProfile;
import eu.unchat.uhc.role.Role;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.entity.Player;

@Getter
@Role(name = "Zenitsu", identifier = "zenitsu", team = SlayerTeam.class, material = Material.GLOWSTONE_DUST)
public final class ZenitsuRole extends AbstractDSRole {

    private final Gender gender;
    private final Rank rank;

    public ZenitsuRole() {
        this.gender = Gender.MALE;
        this.rank = Rank.A;

        registerPower(new HearPower());
        registerPower(new BreathPower(this, new DivineSpeedPower(), new GodOfCelestialFirePower()));
    }

    @Override
    public String getDescription(Player player) {
        return "[\"\",{\"text\":\"»\",\"color\":\"dark_aqua\"},{\"text\":\"------------------------------\",\"strikethrough\":true,\"color\":\"dark_gray\"},{\"text\":\"«\",\"color\":\"dark_aqua\"},{\"text\":\"\\n\\n\"},{\"text\":\"\\u25aa\",\"color\":\"dark_gray\"},{\"text\":\" Demon Slayer UHC\",\"bold\":true,\"color\":\"dark_aqua\"},{\"text\":\"\\n\"},{\"text\":\" \\u2758\",\"color\":\"dark_gray\"},{\"text\":\" Rôle:\",\"color\":\"gray\"},{\"text\":\" Vous êtes \"},{\"text\":\"Zenitsu\",\"color\":\"dark_green\"},{\"text\":\".\\n\"},{\"text\":\" \\u2758\",\"color\":\"dark_gray\"},{\"text\":\" Camp:\",\"color\":\"gray\"},{\"text\":\" Slayers\",\"color\":\"dark_green\"},{\"text\":\".\\n\\n\"},{\"text\":\" \\u2758\",\"color\":\"dark_gray\"},{\"text\":\" Passifs:\",\"color\":\"gray\"},{\"text\":\" Vous possédez l'effet \"},{\"text\":\"Vitesse I\",\"color\":\"aqua\"},{\"text\":\".\\nVous obtenez l'effet \"},{\"text\":\"Force I\",\"color\":\"red\"},{\"text\":\" entre \"},{\"text\":\"7.5\\u2764\",\"color\":\"red\"},{\"text\":\" et \"},{\"text\":\"4.5\\u2764\",\"color\":\"red\"},{\"text\":\" ainsi que l'effet \"},{\"text\":\"Vitesse II\",\"color\":\"aqua\"},{\"text\":\" entre \"},{\"text\":\"4\\u2764\",\"color\":\"red\"},{\"text\":\" et \"},{\"text\":\"0.5\\u2764\",\"color\":\"red\"},{\"text\":\".\\n\\n\"},{\"text\":\" \\u2758\",\"color\":\"dark_gray\"},{\"text\":\" \"},{\"text\":\"DIVINE SPEED\",\"color\":\"dark_green\",\"hoverEvent\":{\"action\":\"show_text\",\"value\":[\"\",{\"text\":\"DIVINE SPEED\",\"color\":\"dark_green\"},{\"text\":\" \\u226b\",\"color\":\"dark_gray\"},{\"text\":\" Capacité\",\"color\":\"gray\"},{\"text\":\"\\n\\n\"},{\"text\":\"[\",\"color\":\"dark_gray\"},{\"text\":\"Clic-droit\",\"color\":\"gray\"},{\"text\":\"] :\",\"color\":\"dark_gray\"},{\"text\":\" Vous permet d'obtenir l'effet \"},{\"text\":\"Vitesse III\",\"color\":\"aqua\"},{\"text\":\" pendant une minute.\\nCependant, vous ne pourrez plus recevoir l'effet \"},{\"text\":\"Force I\",\"color\":\"red\"},{\"text\":\".\\nA la fin de ce laps de temps, vous obtenez l'effet \"},{\"text\":\"Lenteur II\",\"color\":\"dark_gray\"},{\"text\":\" durant \"},{\"text\":\"2 minutes\",\"color\":\"gray\"},{\"text\":\".\\n\\u2022 \"},{\"text\":\"Délai d'activation\",\"color\":\"gray\"},{\"text\":\" : \"},{\"text\":\"20 minutes\",\"color\":\"dark_aqua\"},{\"text\":\"\\n\\u2022 \"},{\"text\":\"Utilisations\",\"color\":\"gray\"},{\"text\":\" : \"},{\"text\":\"2\",\"color\":\"dark_aqua\"}]}},{\"text\":\" (\",\"color\":\"gray\"},{\"text\":\"«\",\"color\":\"dark_aqua\"},{\"text\":\" Survole\",\"color\":\"gray\"},{\"text\":\" »\",\"color\":\"dark_aqua\"},{\"text\":\")\",\"color\":\"gray\"},{\"text\":\"\\n\"},{\"text\":\" \\u2758\",\"color\":\"dark_gray\"},{\"text\":\" \"},{\"text\":\"GOD OF CELESTIAL FIRE\",\"color\":\"dark_green\",\"hoverEvent\":{\"action\":\"show_text\",\"value\":[\"\",{\"text\":\"GOD OF CELESTIAL FIRE\",\"color\":\"dark_green\"},{\"text\":\" \\u226b\",\"color\":\"dark_gray\"},{\"text\":\" Capacité\",\"color\":\"gray\"},{\"text\":\"\\n\\n\"},{\"text\":\"[\",\"color\":\"dark_gray\"},{\"text\":\"Clic-droit\",\"color\":\"gray\"},{\"text\":\"]\",\"color\":\"dark_gray\"},{\"text\":\" : Vous faites tomber des \"},{\"text\":\"éclairs\",\"color\":\"yellow\"},{\"text\":\" sur tous les joueurs dans un rayon de \"},{\"text\":\"30 blocs\",\"color\":\"gray\"},{\"text\":\",\\ninfligeant \"},{\"text\":\"2\\u2764\",\"color\":\"red\"},{\"text\":\" de dégâts et les \"},{\"text\":\"enflammant\",\"color\":\"gold\"},{\"text\":\" pendant \"},{\"text\":\"8 secondes\",\"color\":\"gray\"},{\"text\":\".\\n\\n\\u2022 \"},{\"text\":\"Utilisations\",\"color\":\"gray\"},{\"text\":\" : \"},{\"text\":\"1\",\"color\":\"dark_aqua\"}]}},{\"text\":\" (\",\"color\":\"gray\"},{\"text\":\"«\",\"color\":\"dark_aqua\"},{\"text\":\" Survole\",\"color\":\"gray\"},{\"text\":\" »\",\"color\":\"dark_aqua\"},{\"text\":\")\",\"color\":\"gray\"},{\"text\":\"\\n\\n\"},{\"text\":\"»\",\"color\":\"dark_aqua\"},{\"text\":\"------------------------------\",\"strikethrough\":true,\"color\":\"dark_gray\"},{\"text\":\"«\",\"color\":\"dark_aqua\"}]";
    }

    @Override
    public void onDistribute(Player player) {
        IProfile profile = IProfile.of(player.getUniqueId());
        profile.setSpeedBuffer(profile.getSpeedBuffer() + 0.04F);
    }
}
