package eu.kurai.uhc.demonslayer.power.solitary.shinjuro;

import eu.unchat.uhc.actionbar.IActionBar;
import eu.unchat.uhc.power.defaults.AbstractItemPower;
import eu.unchat.uhc.profile.IProfile;
import eu.unchat.uhc.util.ItemBuilder;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.concurrent.ThreadLocalRandom;

@Getter
@Setter
public final class AlcoholPower extends AbstractItemPower {

    private final String name, identifier;

    private final InteractionType interactionType = InteractionType.CONSUME;

    private Stade stade = Stade.FIRST;

    private int alcohol = 0;

    public AlcoholPower() {
        this.name = "&6&lAlcool";
        this.identifier = "alcohol";

        this.setInitialCooldown(0);
        this.setInitialUses(-1);
    }

    @Override
    public Result onInteract(final Player player, final InteractionType interactionType) {
        if (!interactionType.equals(InteractionType.CONSUME)) {
            return Result.FAILED;
        }

        IProfile profile = IProfile.of(player.getUniqueId());
        final IActionBar actionBar = profile.getActionBar();
        actionBar.addActionBar("alcohol", "&3Alcool: &b" + getAlcohol() + "%");
        actionBar.addActionBar("stade", "&3Stade: &b" + stade.name());
        alcohol = Math.min(alcohol + ThreadLocalRandom.current().nextInt(5, 12), 100);
        Stade oldStade = stade;
        stade = alcohol < 25 ? Stade.FIRST : alcohol < 50 ? Stade.SECOND : alcohol < 75 ? Stade.THIRD : Stade.FOURTH;

        if (oldStade.equals(stade)) {
            return Result.SUCCESSFUL;
        }

        if (stade == Stade.SECOND) {
            profile.setSpeedBuffer(profile.getSpeedBuffer() + 0.02F);
        } else if (stade == Stade.THIRD) {
            profile.setResistanceBuffer(profile.getResistanceBuffer() + 20);
        }
        return Result.SUCCESSFUL;
    }

    @Override
    public ItemStack getIcon() {
        return new ItemBuilder(Material.POTION)
                .name(formatName())
                .asItemStack();
    }

    public enum Stade {
        FIRST,
        SECOND,
        THIRD,
        FOURTH,
        ;
    }
}
