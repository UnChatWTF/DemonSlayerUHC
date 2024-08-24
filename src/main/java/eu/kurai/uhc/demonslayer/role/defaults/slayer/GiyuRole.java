package eu.kurai.uhc.demonslayer.role.defaults.slayer;

import eu.kurai.uhc.demonslayer.role.AbstractDSRole;
import eu.kurai.uhc.demonslayer.team.defaults.SlayerTeam;
import eu.unchat.uhc.power.defaults.AbstractItemPower;
import eu.unchat.uhc.profile.IProfile;
import eu.unchat.uhc.role.Role;
import eu.unchat.uhc.util.ItemBuilder;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@Getter
@Role(name = "Giyu", identifier = "giyu", team = SlayerTeam.class, material = Material.WATER_BUCKET)
public final class GiyuRole extends AbstractDSRole {

    private final Gender gender;
    private final Rank rank;

    public GiyuRole() {
        this.gender = Gender.MALE;
        this.rank = Rank.A;

        registerPower(new DeadCalmPower());
        registerPower(new RisingTidePower());
    }

    @Override
    public void onDistribute(Player player) {
        IProfile profile = IProfile.of(player.getUniqueId());
        profile.setSpeedBuffer(profile.getSpeedBuffer() + 0.04F);
        player.getInventory().addItem(new ItemBuilder(Material.ENCHANTED_BOOK)
                .enchant(Enchantment.DEPTH_STRIDER, 3)
                .asItemStack());
    }

    @Getter
    private static final class DeadCalmPower extends AbstractItemPower {
        private final String name, identifier;

        private final InteractionType interactionType = InteractionType.RIGHT_CLICK;

        public DeadCalmPower() {
            this.name = "&a&lDEAD CALM";
            this.identifier = "dead_calm";

            this.setInitialCooldown(0);
            this.setInitialUses(1);
        }

        @Override
        public ItemStack getIcon() {
            return new ItemBuilder(Material.WATER_LILY)
                    .name(formatName())
                    .glowing(true)
                    .asItemStack();
        }

        @Override
        public Result onInteract(final Player player, final InteractionType interactionType) {
            return Result.SUCCESSFUL;
        }
    }

    @Getter
    private static final class RisingTidePower extends AbstractItemPower {
        private final String name, identifier;


        private final InteractionType interactionType = InteractionType.RIGHT_CLICK;

        public RisingTidePower() {
            this.name = "&a&lRISING TIDE";
            this.identifier = "rising_tide";

            this.setInitialCooldown(10 * 60);
            this.setInitialUses(-1);
        }

        @Override
        public ItemStack getIcon() {
            return new ItemBuilder(Material.NETHER_STAR)
                    .name(formatName())
                    .asItemStack();
        }

        @Override
        public Result onInteract(final Player player, final InteractionType interactionType) {
            return Result.SUCCESSFUL;
        }
    }
}
