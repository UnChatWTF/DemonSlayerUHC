package eu.unchat.uhc.demonslayer.power.demon.kumo;

import eu.unchat.uhc.power.AbstractItemPower;
import eu.unchat.uhc.util.CC;
import eu.unchat.uhc.util.ItemBuilder;
import eu.unchat.uhc.util.Utils;
import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@Getter
public final class AcidCocoonPower extends AbstractItemPower {
    private final String name;
    private final int initialCooldown, initialUses;
    private final ClickType clickType;

    public AcidCocoonPower() {
        this.name = "&c&lACID COCOON";
        this.initialCooldown = 15 * 60;
        this.initialUses = -1;
        this.clickType = ClickType.RIGHT_CLICK;
    }

    @Override
    public ItemStack getIcon() {
        return new ItemBuilder(Material.WEB)
                .name(this.name)
                .glowing(true)
                .asItemStack();
    }

    @Override
    public boolean onClick(Player player) {
        Player target = Utils.getTargetingPlayer(player, 15);
        if (target == null) {
            player.sendMessage(CC.error("Vous devez viser un joueur."));
            return false;
        }

        Location location = target.getLocation().clone().add(0, -2, 0);
        for (int i = 0; i < 3; i++) {
            for (Block block : Utils.createSquare(location.add(0, 1, 0), 1)) {
                block.setType(Material.WEB);
            }
        }
        return true;
    }
}
