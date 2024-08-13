package eu.unchat.uhc.demonslayer.role.defaults.slayer;

import eu.unchat.uhc.demonslayer.power.slayer.obanai.SerpentRipplesPower;
import eu.unchat.uhc.demonslayer.team.defaults.SlayerTeam;
import eu.unchat.uhc.profile.IProfile;
import eu.unchat.uhc.demonslayer.role.AbstractDSRole;
import eu.unchat.uhc.role.Role;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

@Getter
@Role(name = "Obanai", identifier = "obanai", team = SlayerTeam.class, material = Material.INK_SACK, data = 2)
public final class ObanaiRole extends AbstractDSRole implements Listener {

    private final Gender gender;

    public ObanaiRole() {
        this.gender = Gender.MALE;

        registerPower(new SerpentRipplesPower());
    }

    @Override
    public void onDistribute(Player player) {
        IProfile profile = IProfile.of(player.getUniqueId());
        profile.setSpeedBuffer(profile.getSpeedBuffer() + 0.04F);
    }

    @Override
    public void onSecond(Player player) {
        IProfile profile = IProfile.of(player.getUniqueId());

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
}
