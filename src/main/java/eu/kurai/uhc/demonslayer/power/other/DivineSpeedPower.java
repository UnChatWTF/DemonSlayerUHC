package eu.kurai.uhc.demonslayer.power.other;

import eu.kurai.uhc.demonslayer.DSPlugin;
import eu.unchat.uhc.power.defaults.AbstractItemPower;
import eu.unchat.uhc.profile.IProfile;
import eu.unchat.uhc.util.CC;
import eu.unchat.uhc.util.ItemBuilder;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@Getter
public final class DivineSpeedPower extends AbstractItemPower {

    private final String name, identifier;

    private final InteractionType interactionType = InteractionType.RIGHT_CLICK;

    public DivineSpeedPower() {
        this.name = "&3&lVitesse Divine";
        this.identifier = "divine_speed";

        setInitialCooldown(15 * 60);
        setInitialUses(2);
    }

    @Override
    public ItemStack getIcon() {
        return new ItemBuilder(Material.GLOWSTONE_DUST)
                .name(formatName())
                .lore(
                        "&7Vous octroie l'effet &bVitesse III &7durant 1 minute",
                        "&7A la fin du temps imparti, vous aurez l'effet &8Lenteur II",
                        "&7durant 2 minutes.",
                        "&c" + CC.DANGER + " Vous ne pourrez plus recevoir l'effet de &cForce&7."
                )
                .glowing(true)
                .asItemStack();
    }

    @Override
    public Result onInteract(final Player player, final InteractionType interactionType) {
        IProfile profile = IProfile.of(player.getUniqueId());
        profile.setSpeedBuffer(profile.getSpeedBuffer() + 0.08F);
        Bukkit.getScheduler().runTaskLater(DSPlugin.get(), () -> {
            profile.setSlownessBuffer(0.06F);
            profile.setSpeedBuffer(profile.getSpeedBuffer() - 0.12F);
            Bukkit.getScheduler().runTaskLater(DSPlugin.get(), () -> {
                profile.setSlownessBuffer(0.0F);
                profile.setSpeedBuffer(profile.getSpeedBuffer() + 0.04F);
            }, 15 * 20L);
        }, 15 * 20L);
        return Result.SUCCESSFUL;
    }
}
