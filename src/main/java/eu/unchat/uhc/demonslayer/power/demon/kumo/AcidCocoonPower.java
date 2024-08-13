package eu.unchat.uhc.demonslayer.power.demon.kumo;

import eu.unchat.uhc.power.item.AbstractItemPower;
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
                .lore(
                        "&7Si un joueur se trouve à moins de &b15 blocs &7en face de vous,",
                        "&7il sera alors enfermé dans un cocon de toile d'araignée qui possède &c100❤&7.",
                        "&7Tant que le joueur n'a pas brisé le cocon, il perd &b5 &7de durabilité sur chacune",
                        "&7de ses pièces d'armures, et &c0.5❤ &7toutes les &b4 secondes&7.",
                        "",
                        "&c" + CC.DANGER + " Le joueur visé ne peut pas retirer son armure",
                        "&ctant qu'il est dans le cocon."
                )
                .glowing(true)
                .asItemStack();
    }

    @Override
    public Result onClick(final Player player, final boolean right) {
        Player target = Utils.getTargetingPlayer(player, 15);
        if (target == null) {
            player.sendMessage(CC.error("Vous devez viser un joueur."));
            return Result.FAILURE;
        }

        Location location = target.getLocation().clone().add(0, -2, 0);
        for (int i = 0; i < 3; i++) {
            for (Block block : Utils.createSquare(location.add(0, 1, 0), 1)) {
                block.setType(Material.WEB);
            }
        }
        return Result.SUCCESS;
    }
}
