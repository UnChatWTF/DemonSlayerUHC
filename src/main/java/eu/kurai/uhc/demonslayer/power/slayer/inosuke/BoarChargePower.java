package eu.kurai.uhc.demonslayer.power.slayer.inosuke;

import eu.unchat.uhc.power.defaults.AbstractItemPower;
import eu.unchat.uhc.util.ItemBuilder;
import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@Getter
public final class BoarChargePower extends AbstractItemPower {

    private final String name, identifier;

    private final InteractionType interactionType = InteractionType.RIGHT_CLICK;

    public BoarChargePower() {
        this.name = "&a&lCharge du sanglier";
        this.identifier = "boar_charge";

        this.setInitialCooldown(10 * 60);
        this.setInitialUses(-1);
    }

    @Override
    public ItemStack getIcon() {
        return new ItemBuilder(Material.GRILLED_PORK)
                .name(this.name)
                .lore(
                        "&7Vous effectuez un dash en",
                        "&7avant de &b20 blocs&7.",
                        "&7Les joueurs sur votre trajectoire sont",
                        "&7repoussés et perdent &c2❤ &7de dégâts."
                )
                .glowing(true)
                .asItemStack();
    }

    @Override
    public Result onInteract(final Player player, final InteractionType interactionType) {
        Location loc = player.getLocation();
        loc.add(loc.getDirection().multiply(3));
        player.setVelocity(loc.toVector());
        return Result.SUCCESSFUL;
    }
}
