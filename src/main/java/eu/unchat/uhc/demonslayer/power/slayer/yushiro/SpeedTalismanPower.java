package eu.unchat.uhc.demonslayer.power.slayer.yushiro;

import eu.unchat.uhc.power.item.AbstractItemPower;
import eu.unchat.uhc.profile.IProfile;
import eu.unchat.uhc.util.CC;
import eu.unchat.uhc.util.ItemBuilder;
import eu.unchat.uhc.util.Utils;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@Getter
public final class SpeedTalismanPower extends AbstractItemPower {

    private final String name;

    private final int initialCooldown, initialUses;

    private final ClickType clickType;

    public SpeedTalismanPower() {
        this.name = "&c&lSPEED TALISMAN";
        this.initialCooldown = 7 * 60;
        this.initialUses = -1;
        this.clickType = ClickType.RIGHT_CLICK;
    }

    @Override
    public ItemStack getIcon() {
        return new ItemBuilder(Material.BLAZE_ROD)
                .name(name)
                .asItemStack();
    }

    @Override
    public Result onClick(final Player player, final boolean right) {
        Player target = Utils.getTargetingPlayer(player, 30);
        if (target == null) {
            player.sendMessage(CC.error("Vous devez viser un joueur."));
            return Result.FAILURE;
        }

        IProfile profile = IProfile.of(target.getUniqueId());
        profile.setSpeedBuffer(profile.getSpeedBuffer() + 0.02F);
        player.sendMessage(CC.success("Vous avez donné un talisman de vitesse à &3" + target.getName() + "&b."));
        target.sendMessage(CC.success("Vous avez reçu un talisman de vitesse de &cYushiro&b."));
        return Result.SUCCESS;
    }
}
