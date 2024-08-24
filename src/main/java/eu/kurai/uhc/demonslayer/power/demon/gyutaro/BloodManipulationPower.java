package eu.kurai.uhc.demonslayer.power.demon.gyutaro;

import eu.unchat.uhc.power.defaults.AbstractItemPower;
import eu.unchat.uhc.util.CC;
import eu.unchat.uhc.util.ItemBuilder;
import eu.unchat.uhc.util.Utils;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

@Getter
public final class BloodManipulationPower extends AbstractItemPower {
    private final String name, identifier;


    private final InteractionType interactionType = InteractionType.RIGHT_CLICK;

    public BloodManipulationPower() {
        this.name = "&c&lManipulation de sang";
        this.identifier = "blood_manipulation";

        this.setInitialCooldown(7 * 60);
        this.setInitialUses(-1);
    }

    @Override
    public ItemStack getIcon() {
        return new ItemBuilder(Material.DIAMOND_HOE)
                .name(formatName())
                .asItemStack();
    }

    @Override
    public Result onInteract(final Player player, final InteractionType interactionType) {
        Player target = Utils.getTargetingPlayer(player, 15);
        if (target == null) {
            player.sendMessage(CC.error("Vous devez viser un joueur."));
            return Result.FAILED;
        }

        target.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 8 * 20, 1, false, false), true);
        player.sendMessage(CC.info("Vous manipulez le &csang &bde &3" + target.getName() + "&b."));
        target.sendMessage(CC.info("&cGyutaro &bmanipule votre &csang&b."));
        return Result.SUCCESSFUL;
    }
}
