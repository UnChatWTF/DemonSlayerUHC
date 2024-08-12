package eu.unchat.uhc.demonslayer.power.demon.kumo;

import eu.unchat.uhc.power.AbstractItemPower;
import eu.unchat.uhc.util.CC;
import eu.unchat.uhc.util.ItemBuilder;
import eu.unchat.uhc.util.Utils;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

@Getter
public final class AcidManipulationPower extends AbstractItemPower {
    private final String name;
    private final int initialCooldown, initialUses;
    private final ClickType clickType;

    public AcidManipulationPower() {
        this.name = "&c&lACID MANIPULATION";
        this.initialCooldown = 10 * 60;
        this.initialUses = -1;
        this.clickType = ClickType.RIGHT_CLICK;
    }

    @Override
    public ItemStack getIcon() {
        return new ItemBuilder(Material.SPIDER_EYE)
                .name(name)
                .lore(
                        "&7Si un joueur se trouve à moins de &b15 blocs &7en face de vous,",
                        "&7vous pouvez alors lui jeter de l'&bacide &7dessus.",
                        "&7Le joueur perd alors &b40 &7de durabilité sur ses pièces d'armure et",
                        "&7obtient un effet de &2Poison II &7durant &b4 secondes&7."
                )
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

        target.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 20 * 5, 1, false, false), true);
        for (ItemStack stack : target.getInventory().getArmorContents()) {
            if (stack == null) {
                continue;
            }
            stack.setDurability((short) (stack.getDurability() + 40));
        }
        return true;
    }
}
