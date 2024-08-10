package eu.unchat.uhc.demonslayer.speciality.blade;

import com.google.common.collect.Lists;
import eu.unchat.uhc.demonslayer.speciality.blade.defaults.*;
import eu.unchat.uhc.util.ItemBuilder;
import lombok.SneakyThrows;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

import java.util.List;

public final class DSBladeHandler {
    public static ItemStack ITEM;
    private static DSBladeHandler instance;
    private final List<IBlade> blades;

    public DSBladeHandler() {
        instance = this;
        this.blades = Lists.newArrayList();
        init();

        /*
        IBlade blade = DSBladeHandler.get().blades.get(ThreadLocalRandom.current().nextInt(DSBladeHandler.get().blades.size()));
        blade.apply().accept(IProfile.of(player.getUniqueId()));
        player.getInventory().remove(getIcon(player));
        player.getInventory().addItem(blade.getDisplay());
         */

        ITEM = new ItemBuilder(Material.NETHER_STAR)
                .name("§2&lLame de Nichirin")
                .lore("&7Clic-droit pour recevoir votre lame")
                .asItemStack();

        ShapedRecipe recipe = new ShapedRecipe(ITEM);
        recipe.shape("OEO", "DED", "ISI");

        recipe.setIngredient('O', Material.OBSIDIAN);
        recipe.setIngredient('E', Material.EMERALD);
        recipe.setIngredient('D', Material.DIAMOND);
        recipe.setIngredient('I', Material.IRON_BLOCK);
        recipe.setIngredient('S', Material.IRON_SWORD);

        Bukkit.addRecipe(recipe);
    }

    public static DSBladeHandler get() {
        return instance;
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
}
