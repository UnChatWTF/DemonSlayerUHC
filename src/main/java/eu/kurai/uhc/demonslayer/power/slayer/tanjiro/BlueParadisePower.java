package eu.kurai.uhc.demonslayer.power.slayer.tanjiro;

import eu.unchat.uhc.power.defaults.AbstractItemPower;
import eu.unchat.uhc.util.ItemBuilder;
import eu.unchat.uhc.util.Utils;
import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;

@Getter
public final class BlueParadisePower extends AbstractItemPower implements Listener {

    private final String name, identifier;


    private final InteractionType interactionType = InteractionType.RIGHT_CLICK;

    private Location centerLocation;

    public BlueParadisePower() {
        this.name = "&a&lParadis bleu";
        this.identifier = "blue_paradise";

        this.setInitialCooldown(10 * 60);
        this.setInitialUses(-1);
    }

    @Override
    public ItemStack getIcon() {
        return new ItemBuilder(Material.BLAZE_POWDER)
                .name(formatName())
                .lore(
                        "&7Crée une zone de &6feu &7d'un rayon de 20 blocs",
                        "&7autour de vous, les joueurs à l'intérieur prendront &6feu&7."
                )
                .glowing(true)
                .asItemStack();
    }

    @Override
    public Result onInteract(final Player player, final InteractionType interactionType) {
        this.centerLocation = player.getLocation();

        for (Block block : Utils.createCompleteHighestCircle(this.centerLocation, 20)) {
            block.setType(Material.NETHERRACK);
        }

        return Result.SUCCESSFUL;
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (centerLocation == null) {
            return;
        }

        if (player.getLocation().distanceSquared(centerLocation) <= 20 * 20) {
            player.setFireTicks(20 * 8);
        }
    }
}
