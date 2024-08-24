package eu.kurai.uhc.demonslayer.power.slayer.yushiro;

import eu.unchat.uhc.power.defaults.AbstractItemPower;
import eu.unchat.uhc.profile.IProfile;
import eu.unchat.uhc.util.CC;
import eu.unchat.uhc.util.ItemBuilder;
import eu.unchat.uhc.util.Utils;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@Getter
public final class StrengthTalismanPower extends AbstractItemPower {

    private final String name, identifier;


    private final InteractionType interactionType = InteractionType.RIGHT_CLICK;

    public StrengthTalismanPower() {
        this.name = "&c&lTalisman de force";
        this.identifier = "strength_talisman";

        this.setInitialCooldown(7 * 60);
        this.setInitialUses(-1);
    }

    @Override
    public ItemStack getIcon() {
        return new ItemBuilder(Material.BLAZE_POWDER)
                .name(formatName())
                .asItemStack();
    }

    @Override
    public Result onInteract(final Player player, final InteractionType interactionType) {
        Player target = Utils.getTargetingPlayer(player, 30);
        if (target == null) {
            player.sendMessage(CC.error("Vous devez viser un joueur."));
            return Result.FAILED;
        }

        IProfile profile = IProfile.of(target.getUniqueId());
        profile.setStrengthBuffer(profile.getStrengthBuffer() + 10);
        player.sendMessage(CC.success("Vous avez donné un talisman de force à &3" + target.getName() + "&b."));
        target.sendMessage(CC.success("Vous avez reçu un talisman de force de &cYushiro&b."));
        return Result.SUCCESSFUL;
    }
}
