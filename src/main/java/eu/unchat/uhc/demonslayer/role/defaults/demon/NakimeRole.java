package eu.unchat.uhc.demonslayer.role.defaults.demon;

import eu.unchat.uhc.demonslayer.team.defaults.DemonTeam;
import eu.unchat.uhc.power.item.AbstractItemPower;
import eu.unchat.uhc.demonslayer.role.AbstractDSRole;
import eu.unchat.uhc.role.Role;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@Getter
@Role(name = "Nakime", identifier = "nakime", team = DemonTeam.class, material = Material.MAGMA_CREAM)
public final class NakimeRole extends AbstractDSRole {

    private final Gender gender;

    public NakimeRole() {
        this.gender = Gender.FEMALE;

        registerKnownRole(MuzanRole.class);
    }

    @Getter
    private static final class BiwaPower extends AbstractItemPower {
        private final String name;

        private final int initialCooldown, initialUses;

        private final ClickType clickType;

        public BiwaPower() {
            this.name = "&c&lBiwa";

            this.initialCooldown = 20 * 60;
            this.initialUses = -1;

            this.clickType = ClickType.RIGHT_CLICK;
        }

        @Override
        public ItemStack getIcon() {
            return null;
        }

        @Override
        public Result onClick(final Player player, final boolean right) {
            return Result.SUCCESS;
        }
    }
}
