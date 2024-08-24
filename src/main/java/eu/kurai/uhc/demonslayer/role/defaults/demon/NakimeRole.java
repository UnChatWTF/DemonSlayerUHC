package eu.kurai.uhc.demonslayer.role.defaults.demon;

import eu.kurai.uhc.demonslayer.role.AbstractDSRole;
import eu.kurai.uhc.demonslayer.team.defaults.DemonTeam;
import eu.unchat.uhc.power.defaults.AbstractItemPower;
import eu.unchat.uhc.role.Role;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@Getter
@Role(name = "Nakime", identifier = "nakime", team = DemonTeam.class, material = Material.MAGMA_CREAM)
public final class NakimeRole extends AbstractDSRole {

    private final Gender gender;
    private final Rank rank;

    public NakimeRole() {
        this.gender = Gender.FEMALE;
        this.rank = Rank.B;

        registerKnownRole(MuzanRole.class);
    }

    @Getter
    private static final class BiwaPower extends AbstractItemPower {
        private final String name, identifier;


        private final InteractionType interactionType = InteractionType.RIGHT_CLICK;

        public BiwaPower() {
            this.name = "&c&lBiwa";
            this.identifier = "biwa";

            this.setInitialCooldown(20 * 60);
            this.setInitialUses(-1);
        }

        @Override
        public ItemStack getIcon() {
            return null;
        }

        @Override
        public Result onInteract(final Player player, final InteractionType interactionType) {
            return Result.SUCCESSFUL;
        }
    }
}
