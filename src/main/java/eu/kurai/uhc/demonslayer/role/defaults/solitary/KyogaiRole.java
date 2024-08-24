package eu.kurai.uhc.demonslayer.role.defaults.solitary;

import eu.kurai.uhc.demonslayer.role.AbstractDSRole;
import eu.kurai.uhc.demonslayer.team.defaults.SolitaryTeam;
import eu.unchat.uhc.power.defaults.AbstractItemPower;
import eu.unchat.uhc.role.Role;
import eu.unchat.uhc.util.ItemBuilder;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@Getter
@Role(name = "Kyogai", identifier = "kyogai", team = SolitaryTeam.class, material = Material.STICK)
public final class KyogaiRole extends AbstractDSRole {

    private final Gender gender;
    private final Rank rank;

    public KyogaiRole() {
        this.gender = Gender.MALE;
        this.rank = Rank.B;
        registerPower(new DrumPower());
    }

    @Getter
    private static final class DrumPower extends AbstractItemPower {
        private final String name, identifier;

        private final InteractionType interactionType = InteractionType.RIGHT_CLICK;

        public DrumPower() {
            this.name = "&6&lDRUM";
            this.identifier = "drum";

            this.setInitialCooldown(5 * 60);
            this.setInitialUses(-1);
        }

        @Override
        public ItemStack getIcon() {
            return new ItemBuilder(Material.STICK)
                    .name(formatName())
                    .glowing(true)
                    .asItemStack();
        }

        @Override
        public Result onInteract(final Player player, final InteractionType interactionType) {
            return Result.SUCCESSFUL;
        }
    }
}
