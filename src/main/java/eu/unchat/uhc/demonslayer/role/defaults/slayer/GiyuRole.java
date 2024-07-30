package eu.unchat.uhc.demonslayer.role.defaults.slayer;

import eu.unchat.uhc.demonslayer.team.defaults.SlayerTeam;
import eu.unchat.uhc.power.AbstractItemPower;
import eu.unchat.uhc.profile.IProfile;
import eu.unchat.uhc.role.AbstractRole;
import eu.unchat.uhc.role.Role;
import eu.unchat.uhc.util.ItemBuilder;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;

@Role(name = "Giyu", identifier = "giyu", team = SlayerTeam.class)
public final class GiyuRole extends AbstractRole {
    public GiyuRole() {
        registerPower(new AccalmiePower());
        registerPower(new RisingTidePower());
    }

    @Override
    public void onDistribute(Player player) {
        IProfile profile = IProfile.of(player.getUniqueId());
        profile.setSpeedBuffer(profile.getSpeedBuffer() + 0.04F);
        ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
        EnchantmentStorageMeta meta = (EnchantmentStorageMeta) item.getItemMeta();
        meta.addStoredEnchant(Enchantment.DEPTH_STRIDER, 3, false);
        item.setItemMeta(meta);
        player.getInventory().addItem(item);
    }

    @Getter
    private static final class AccalmiePower extends AbstractItemPower {
        private final String name;

        private final int initialCooldown, initialUses;

        private final ClickType clickType;

        public AccalmiePower() {
            this.name = "&a&lAccalmie";
            this.initialCooldown = 0;
            this.initialUses = 1;
            this.clickType = ClickType.RIGHT_CLICK;
        }

        @Override
        public ItemStack getIcon() {
            return new ItemBuilder(Material.WATER_LILY)
                    .name(name)
                    .glowing(true)
                    .asItemStack();
        }

        @Override
        public boolean onClick(Player player) {
            return true;
        }
    }

    @Getter
    private static final class RisingTidePower extends AbstractItemPower {
        private final String name;

        private final int initialCooldown, initialUses;

        private final ClickType clickType;

        public RisingTidePower() {
            this.name = "&a&lRising Tide";
            this.initialCooldown = 10 * 60;
            this.initialUses = -1;
            this.clickType = ClickType.RIGHT_CLICK;
        }

        @Override
        public ItemStack getIcon() {
            return new ItemBuilder(Material.NETHER_STAR)
                    .name(name)
                    .asItemStack();
        }

        @Override
        public boolean onClick(Player player) {
            return true;
        }
    }
}
