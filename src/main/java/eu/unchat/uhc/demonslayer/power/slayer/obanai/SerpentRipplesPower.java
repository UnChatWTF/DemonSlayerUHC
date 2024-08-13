package eu.unchat.uhc.demonslayer.power.slayer.obanai;

import eu.unchat.uhc.demonslayer.team.defaults.DemonTeam;
import eu.unchat.uhc.power.item.AbstractItemPower;
import eu.unchat.uhc.profile.IProfile;
import eu.unchat.uhc.util.ItemBuilder;
import eu.unchat.uhc.util.Utils;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

@Getter
public class SerpentRipplesPower extends AbstractItemPower {
    private final String name;
    private final int initialCooldown, initialUses;
    private final ClickType clickType;

    public SerpentRipplesPower() {
        this.name = "&a&lSERPENT RIPPLES";
        this.initialCooldown = 15 * 60;
        this.initialUses = -1;
        this.clickType = ClickType.RIGHT_CLICK;
    }

    @Override
    public ItemStack getIcon() {
        return new ItemBuilder(Material.INK_SACK)
                .name(name)
                .data(2)
                .asItemStack();
    }

    @Override
    public Result onClick(final Player player, final boolean right) {
        for (Player near : Utils.getNearbyPlayers(player, 20)) {
            if (player.getUniqueId().equals(near.getUniqueId())) {
                continue;
            }

            IProfile profile = IProfile.of(near.getUniqueId());
            if (profile.getTeam() == null) {
                continue;
            }

            if (profile.getTeam().getClass().equals(DemonTeam.class)) {
                profile.removeHealth(2);
                near.setMaxHealth(2);
                continue;
            }

            near.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 8 * 20, 1, false, false), true);
        }
        return Result.SUCCESS;
    }
}
