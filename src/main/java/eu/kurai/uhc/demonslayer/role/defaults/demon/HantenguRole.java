package eu.kurai.uhc.demonslayer.role.defaults.demon;

import com.google.common.collect.Maps;
import eu.kurai.uhc.demonslayer.power.demon.hantengu.ClonePower;
import eu.kurai.uhc.demonslayer.role.AbstractDSRole;
import eu.kurai.uhc.demonslayer.team.defaults.DemonTeam;
import eu.unchat.uhc.profile.IProfile;
import eu.unchat.uhc.role.Role;
import eu.unchat.uhc.util.TimeUtil;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Map;

@Getter
@Role(name = "Hantengu", identifier = "hantengu", team = DemonTeam.class, material = Material.BLAZE_ROD)
public final class HantenguRole extends AbstractDSRole {

    private final Gender gender;
    private final Rank rank;

    private final Map<Clone, Integer> clones;

    @Setter
    private Clone clone;

    public HantenguRole() {
        this.gender = Gender.MALE;
        this.rank = Rank.B;

        this.clone = Clone.NONE;
        this.clones = Maps.newHashMap();
        for (Clone value : Clone.values()) {
            if (value.equals(Clone.NONE)) {
                continue;
            }
            this.clones.put(value, 20 * 60);
        }

        registerKnownRole(MuzanRole.class);
        registerPower(new ClonePower());
    }

    @Override
    public void onDistribute(Player player) {
        IProfile profile = IProfile.of(player.getUniqueId());
        profile.setWeaknessBuffer(profile.getWeaknessBuffer() + 15);
    }

    @Override
    public void onSecond(Player player) {
        IProfile profile = IProfile.of(player.getUniqueId());

        if (!clone.equals(Clone.NONE)) {
            if (clones.get(clone) > 0) {
                clones.put(clone, clones.get(clone) - 1);
                profile.getActionBar().addActionBar("clone_" + clone.name().toLowerCase(), "&c&l" + clone.name() + " : " + TimeUtil.niceTime(clones.get(clone) * 1000L));
            } else {
                clone = Clone.NONE;
                profile.getActionBar().removeActionBar("clone_" + clone.name().toLowerCase());
            }
        }

        if (hasArmor(player)) {
            if (!player.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
                return;
            }

            profile.getInvulnerabilityClauses().remove(EntityDamageEvent.DamageCause.FALL);
            profile.setSpeedBuffer(profile.getSpeedBuffer() - 0.04F);
            player.removePotionEffect(PotionEffectType.INVISIBILITY);
            return;
        }

        if (player.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
            return;
        }

        profile.getInvulnerabilityClauses().add(EntityDamageEvent.DamageCause.FALL);
        profile.setSpeedBuffer(profile.getSpeedBuffer() + 0.04F);
        player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 0, false, false), true);
    }

    private boolean hasArmor(final Player player) {
        return player.getInventory().getHelmet() != null
                || player.getInventory().getChestplate() != null
                || player.getInventory().getLeggings() != null
                || player.getInventory().getBoots() != null;
    }

    public enum Clone {
        NONE,
        SEKIDO,
        KARAKU,
        AIZETSU,
        UROGI,
        ZOHAKUTEN;
    }
}
