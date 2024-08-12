package eu.unchat.uhc.demonslayer.role.defaults.solitary;

import eu.unchat.uhc.demonslayer.team.defaults.SolitaryTeam;
import eu.unchat.uhc.power.AbstractItemPower;
import eu.unchat.uhc.role.AbstractRole;
import eu.unchat.uhc.role.Role;
import eu.unchat.uhc.util.ItemBuilder;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@Role(name = "Kyogai", identifier = "kyogai", team = SolitaryTeam.class, material = Material.STICK)
public final class KyogaiRole extends AbstractRole {
    @Getter
    private static final class DrumPower extends AbstractItemPower {
        private final String name;

        private final int initialCooldown, initialUses;

        private final ClickType clickType;

        public DrumPower() {
            this.name = "&6&lDRUM";
            this.initialCooldown = 5 * 60;
            this.initialUses = -1;
            this.clickType = ClickType.RIGHT_CLICK;
        }

        @Override
        public ItemStack getIcon() {
            return new ItemBuilder(Material.STICK)
                    .name(name)
                    .glowing(true)
                    .asItemStack();
        }

        @Override
        public boolean onClick(Player player) {
            return false;
        }
    }
}
