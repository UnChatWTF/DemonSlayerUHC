package eu.unchat.uhc.demonslayer.power.demon.daki;

import eu.unchat.uhc.demonslayer.DSPlugin;
import eu.unchat.uhc.power.item.AbstractItemPower;
import eu.unchat.uhc.profile.IProfile;
import eu.unchat.uhc.util.CC;
import eu.unchat.uhc.util.ItemBuilder;
import eu.unchat.uhc.util.Utils;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@Getter
public final class ObiSashPower extends AbstractItemPower {
    private final String name;

    private final int initialCooldown, initialUses;

    private final ClickType clickType;

    public ObiSashPower() {
        this.name = "&c&lOBI SASH";
        this.initialCooldown = 10 * 60;
        this.initialUses = -1;
        this.clickType = ClickType.RIGHT_CLICK;
    }

    @Override
    public ItemStack getIcon() {
        return new ItemBuilder(Material.FERMENTED_SPIDER_EYE)
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

        IProfile profile = IProfile.of(target.getUniqueId());
        profile.stun(8, false);
        profile.setSlownessBuffer(profile.getSlownessBuffer() + 0.03F);
        Bukkit.getScheduler().runTaskLater(DSPlugin.get(), () -> {
            profile.setSlownessBuffer(profile.getSlownessBuffer() - 0.03F);
        }, 30 * 20L);
        return Result.SUCCESS;
    }
}
