package eu.unchat.uhc.demonslayer.power.slayer.zenitsu;

import eu.unchat.uhc.demonslayer.DSPlugin;
import eu.unchat.uhc.power.item.AbstractItemPower;
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

    private final String name;
    private final int initialCooldown, initialUses;
    private final ClickType clickType;

    public DivineSpeedPower() {
        this.name = "&a&lDIVINE SPEED";
        this.initialCooldown = 20 * 60;
        this.initialUses = 2;
        this.clickType = ClickType.RIGHT_CLICK;
    }

    @Override
    public ItemStack getIcon() {
        return new ItemBuilder(Material.GLOWSTONE_DUST)
                .name(name)
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
    public Result onClick(final Player player, final boolean right) {
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
        return Result.SUCCESS;
    }
}
