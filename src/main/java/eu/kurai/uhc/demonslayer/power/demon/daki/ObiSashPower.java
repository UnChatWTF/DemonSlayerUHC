package eu.kurai.uhc.demonslayer.power.demon.daki;

import eu.kurai.uhc.demonslayer.DSPlugin;
import eu.unchat.uhc.power.defaults.AbstractItemPower;
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
    private final String name, identifier;

    private final InteractionType interactionType = InteractionType.RIGHT_CLICK;

    public ObiSashPower() {
        this.name = "&c&lObi Sash";
        this.identifier = "obi_sash";

        this.setInitialCooldown(10 * 60);
        this.setInitialUses(-1);
    }

    @Override
    public ItemStack getIcon() {
        return new ItemBuilder(Material.FERMENTED_SPIDER_EYE)
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

        IProfile profile = IProfile.of(target.getUniqueId());
        profile.stun(8, false);
        profile.setSlownessBuffer(profile.getSlownessBuffer() + 0.03F);
        Bukkit.getScheduler().runTaskLater(DSPlugin.get(), () -> {
            profile.setSlownessBuffer(profile.getSlownessBuffer() - 0.03F);
        }, 30 * 20L);
        return Result.SUCCESSFUL;
    }
}
