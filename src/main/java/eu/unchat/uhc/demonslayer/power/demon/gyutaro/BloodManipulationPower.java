package eu.unchat.uhc.demonslayer.power.demon.gyutaro;

import eu.unchat.uhc.power.item.AbstractItemPower;
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
    private final String name;

    private final int initialCooldown, initialUses;

    private final ClickType clickType;

    public BloodManipulationPower() {
        this.name = "&c&lBLOOD MANIPULATION";
        this.initialCooldown = 7 * 60;
        this.initialUses = -1;
        this.clickType = ClickType.RIGHT_CLICK;
    }

    @Override
    public ItemStack getIcon() {
        return new ItemBuilder(Material.DIAMOND_HOE)
                .name(name)
                .asItemStack();
    }

    @Override
    public Result onClick(Player player, final boolean right) {
        Player target = Utils.getTargetingPlayer(player, 15);
        if (target == null) {
            player.sendMessage(CC.error("Vous devez viser un joueur."));
            return Result.FAILURE;
        }

        target.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 8 * 20, 1, false, false), true);
        player.sendMessage(CC.info("Vous manipulez le &csang &bde &3" + target.getName() + "&b."));
        target.sendMessage(CC.info("&cGyutaro &bmanipule votre &csang&b."));
        return Result.SUCCESS;
    }
}
