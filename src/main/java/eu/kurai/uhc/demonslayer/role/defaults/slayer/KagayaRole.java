package eu.kurai.uhc.demonslayer.role.defaults.slayer;

import eu.kurai.uhc.demonslayer.power.slayer.kagaya.CrowPower;
import eu.kurai.uhc.demonslayer.role.AbstractDSRole;
import eu.kurai.uhc.demonslayer.role.defaults.demon.MuzanRole;
import eu.kurai.uhc.demonslayer.team.defaults.SlayerTeam;
import eu.unchat.uhc.profile.IProfile;
import eu.unchat.uhc.role.Role;
import eu.unchat.uhc.util.CC;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Getter
@Role(name = "Kagaya", identifier = "kagaya", team = SlayerTeam.class, material = Material.CHEST)
public final class KagayaRole extends AbstractDSRole {

    private final Gender gender;
    private final Rank rank;

    public KagayaRole() {
        this.gender = Gender.MALE;
        this.rank = Rank.C;

        registerKnownRole(KiriyaRole.class);

        List<Class<? extends AbstractDSRole>> pillars = List.of(
                GiyuRole.class, KyojuroRole.class, MitsuriRole.class,
                ObanaiRole.class, ShinobuRole.class, TengenRole.class
        );

        Class<? extends AbstractDSRole> pillar = pillars.get(ThreadLocalRandom.current().nextInt(pillars.size()));

        registerKnownRole(pillar);

        onRoleDeath(MuzanRole.class, (profile -> {
            IProfile kagaya = AbstractDSRole.findPlayer(KagayaRole.class);
            if (kagaya == null) {
                return;
            }

            kagaya.setWeaknessBuffer(kagaya.getWeaknessBuffer() - 15);
            kagaya.sendMessage(CC.info("&cMuzan &bvient de mourir, vous perdez donc votre effet de &8Faiblesse&b."));
        }));

        registerPower(new CrowPower());
    }

    @Override
    public void onDistribute(Player player) {
        IProfile profile = IProfile.of(player.getUniqueId());
        profile.setWeaknessBuffer(profile.getWeaknessBuffer() + 15);
    }
}
