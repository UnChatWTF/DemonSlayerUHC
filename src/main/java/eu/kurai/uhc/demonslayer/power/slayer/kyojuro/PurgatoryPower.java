package eu.kurai.uhc.demonslayer.power.slayer.kyojuro;

import eu.kurai.uhc.demonslayer.DSPlugin;
import eu.unchat.uhc.power.defaults.AbstractItemPower;
import eu.unchat.uhc.profile.IProfile;
import eu.unchat.uhc.util.ItemBuilder;
import eu.unchat.uhc.util.Utils;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@Getter
public final class PurgatoryPower extends AbstractItemPower {

    private final String name, identifier;


    private final InteractionType interactionType = InteractionType.RIGHT_CLICK;

    public PurgatoryPower() {
        this.name = "&a&lPurgatoire";
        this.identifier = "purgatory";

        this.setInitialCooldown(15 * 60);
        this.setInitialUses(2);
    }

    @Override
    public ItemStack getIcon() {
        return new ItemBuilder(Material.BLAZE_POWDER)
                .name(formatName())
                .glowing(true)
                .asItemStack();
    }

    @Override
    public Result onInteract(final Player player, final InteractionType interactionType) {
        Location center = player.getLocation();

        IProfile profile = IProfile.of(player.getUniqueId());
        profile.setStrengthBuffer(profile.getStrengthBuffer() + 10);
        profile.setAttackDelay(profile.getAttackDelay() - 2);

        Bukkit.getScheduler().runTaskLater(DSPlugin.get(), () -> {
            profile.setStrengthBuffer(profile.getStrengthBuffer() - 10);
            profile.setAttackDelay(profile.getAttackDelay() + 2);
        }, (3 * 60) * 20L);

        for (Location location : Utils.createSphere(center, 20, false)) {
            location.getBlock().setType(Material.AIR);
        }
        return Result.SUCCESSFUL;
    }
}
