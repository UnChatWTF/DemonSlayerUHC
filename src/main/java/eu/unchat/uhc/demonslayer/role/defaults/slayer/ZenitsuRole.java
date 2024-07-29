package eu.unchat.uhc.demonslayer.role.defaults.slayer;

import eu.unchat.uhc.API;
import eu.unchat.uhc.actionbar.IActionBar;
import eu.unchat.uhc.demonslayer.DSPlugin;
import eu.unchat.uhc.demonslayer.team.defaults.SlayerTeam;
import eu.unchat.uhc.power.AbstractItemPower;
import eu.unchat.uhc.profile.IProfile;
import eu.unchat.uhc.role.AbstractRole;
import eu.unchat.uhc.role.Role;
import eu.unchat.uhc.util.ItemBuilder;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@Role(name = "Zenitsu", identifier = "zenitsu", team = SlayerTeam.class)
public final class ZenitsuRole extends AbstractRole {
    public ZenitsuRole() {
        registerPower(new DivineSpeedPower());
        registerPower(new GodOfCelestialFirePower());
    }

    @Override
    public void onDistribute(Player player) {
        IProfile profile = IProfile.of(player.getUniqueId());
        profile.setSpeedBuffer(profile.getSpeedBuffer() + 0.04F);
    }

    @Override
    public void onSecond(Player player) {
        IActionBar actionBar = API.get().getActionBarHandler().getActionBar(player.getUniqueId());
        actionBar.addActionBar("debug", "Walk Speed: " + player.getWalkSpeed());
    }

    @Getter
    private static final class DivineSpeedPower extends AbstractItemPower {

        private final String name;
        private final int initialCooldown, initialUses;
        private final ClickType clickType;

        public DivineSpeedPower() {
            this.name = "&a&lDivine Speed";
            this.initialCooldown = 20 * 60;
            this.initialUses = 2;
            this.clickType = ClickType.RIGHT_CLICK;
        }

        @Override
        public ItemStack getIcon() {
            return new ItemBuilder(Material.GLOWSTONE_DUST)
                    .name(name)
                    .glowing(true)
                    .asItemStack();
        }

        @Override
        public boolean onClick(Player player) {
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
            return true;
        }
    }

    @Getter
    private static final class GodOfCelestialFirePower extends AbstractItemPower {

        private final String name;
        private final int initialCooldown, initialUses;
        private final ClickType clickType;

        public GodOfCelestialFirePower() {
            this.name = "&a&lGod of celestial fire";
            this.initialCooldown = 0;
            this.initialUses = 1;
            this.clickType = ClickType.RIGHT_CLICK;
        }

        @Override
        public ItemStack getIcon() {
            return new ItemBuilder(Material.BLAZE_POWDER)
                    .name(name)
                    .glowing(true)
                    .asItemStack();
        }

        @Override
        public boolean onClick(Player player) {
            return true;
        }
    }
}
