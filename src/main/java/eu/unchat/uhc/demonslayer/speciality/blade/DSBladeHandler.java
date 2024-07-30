package eu.unchat.uhc.demonslayer.speciality.blade;

import com.google.common.collect.Lists;
import eu.unchat.uhc.demonslayer.speciality.blade.defaults.*;
import eu.unchat.uhc.item.Item;
import eu.unchat.uhc.profile.IProfile;
import eu.unchat.uhc.util.ItemBuilder;
import lombok.SneakyThrows;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public final class DSBladeHandler {
    private static DSBladeHandler instance;

    private final List<IBlade> blades;

    public static Item ITEM;

    public DSBladeHandler() {
        instance = this;
        this.blades = Lists.newArrayList();
        init();

        ITEM = new BladeItem();

        ShapedRecipe recipe = new ShapedRecipe(ITEM.getIcon(null));
        recipe.shape("OEO", "DED", "ISI");

        recipe.setIngredient('O', Material.OBSIDIAN);
        recipe.setIngredient('E', Material.EMERALD);
        recipe.setIngredient('D', Material.DIAMOND);
        recipe.setIngredient('I', Material.IRON_BLOCK);
        recipe.setIngredient('S', Material.IRON_SWORD);

        Bukkit.addRecipe(recipe);
    }

    private void init() {
        registerBlade(BlackBlade.class);
        registerBlade(GrayBlade.class);
        registerBlade(YellowBlade.class);
        registerBlade(PinkBlade.class);
        registerBlade(GreenBlade.class);
        registerBlade(BlueBlade.class);
        registerBlade(OrangeBlade.class);
    }

    @SneakyThrows
    public void registerBlade(final Class<? extends IBlade> blade) {
        this.blades.add(blade.getDeclaredConstructor().newInstance());
    }

    public static DSBladeHandler get() {
        return instance;
    }

    private static final class BladeItem implements Item {
        @Override
        public ItemStack getIcon(Player player) {
            return new ItemBuilder(Material.NETHER_STAR)
                    .name("§2&lLame de Nichirin")
                    .lore("&7Clic-droit pour recevoir votre lame")
                    .asItemStack();
        }

        @Override
        public boolean onInteract(Player player, boolean right) {
            IBlade blade = DSBladeHandler.get().blades.get(ThreadLocalRandom.current().nextInt(DSBladeHandler.get().blades.size()));
            blade.apply().accept(IProfile.of(player.getUniqueId()));
            player.getInventory().remove(getIcon(player));
            player.getInventory().addItem(blade.getDisplay());
            return true;
        }
    }
}
