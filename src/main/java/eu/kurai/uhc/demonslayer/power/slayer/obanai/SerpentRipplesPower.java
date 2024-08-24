package eu.kurai.uhc.demonslayer.power.slayer.obanai;

import eu.kurai.uhc.demonslayer.team.defaults.DemonTeam;
import eu.unchat.uhc.power.defaults.AbstractItemPower;
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
    private final String name, identifier;


    private final InteractionType interactionType = InteractionType.RIGHT_CLICK;

    public SerpentRipplesPower() {
        this.name = "&a&lSERPENT RIPPLES";
        this.identifier = "serpent_ripples";

        this.setInitialCooldown(15 * 60);
        this.setInitialUses(-1);
    }

    @Override
    public ItemStack getIcon() {
        return new ItemBuilder(Material.INK_SACK)
                .name(formatName())
                .data(2)
                .asItemStack();
    }

    @Override
    public Result onInteract(final Player player, final InteractionType interactionType) {
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
        return Result.SUCCESSFUL;
    }
}
