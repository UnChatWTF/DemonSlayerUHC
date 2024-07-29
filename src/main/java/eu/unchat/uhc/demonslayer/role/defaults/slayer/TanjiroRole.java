package eu.unchat.uhc.demonslayer.role.defaults.slayer;

import eu.unchat.uhc.API;
import eu.unchat.uhc.actionbar.IActionBar;
import eu.unchat.uhc.demonslayer.DSPlugin;
import eu.unchat.uhc.demonslayer.team.defaults.SlayerTeam;
import eu.unchat.uhc.power.AbstractCommandPower;
import eu.unchat.uhc.power.AbstractItemPower;
import eu.unchat.uhc.power.AbstractPower;
import eu.unchat.uhc.profile.IProfile;
import eu.unchat.uhc.role.AbstractRole;
import eu.unchat.uhc.role.Role;
import eu.unchat.uhc.util.ItemBuilder;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@Role(name = "Tanjiro", identifier = "tanjiro", team = SlayerTeam.class)
public final class TanjiroRole extends AbstractRole {
    public TanjiroRole() {
        registerPower(new FlairPower());
    }

    @Getter
    public static final class FlairPower extends AbstractPower {
        private final String name;
        private final int initialCooldown, initialUses;

        public FlairPower() {
            this.name = "&a&lFlair";
            this.initialCooldown = 20 * 60;
            this.initialUses = 3;
        }
    }
}
