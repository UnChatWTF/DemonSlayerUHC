package eu.kurai.uhc.demonslayer.speciality.blade;

import com.google.common.collect.Lists;
import eu.kurai.uhc.demonslayer.DSPlugin;
import eu.kurai.uhc.demonslayer.speciality.blade.defaults.*;
import eu.unchat.uhc.profile.IProfile;
import eu.unchat.uhc.util.ItemBuilder;
import lombok.SneakyThrows;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.potion.PotionEffectType;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public final class DSBladeHandler implements Listener {
    public static ItemStack ITEM;
    private static DSBladeHandler instance;
    private final List<IBlade> blades;

    public DSBladeHandler() {
        instance = this;
        this.blades = Lists.newArrayList();
        init();

        ITEM = new ItemBuilder(Material.NETHER_STAR)
                .name("&3&lLame de Nichirin")
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

        Bukkit.getPluginManager().registerEvents(this, DSPlugin.get());
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

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (event.getItem() == null || !event.getItem().isSimilar(ITEM)) {
            return;
        }

        List<IBlade> bladeList = Lists.newArrayList();
        bladeList.addAll(blades);
        IBlade blade = bladeList.get(ThreadLocalRandom.current().nextInt(bladeList.size()));

        if (player.hasPotionEffect(PotionEffectType.FIRE_RESISTANCE)) {
            bladeList.removeIf(OrangeBlade.class::isInstance);
        }

        blade.apply().accept(IProfile.of(player.getUniqueId()));
        player.getInventory().remove(ITEM);
        player.getInventory().addItem(blade.getDisplay());
    }

    @SneakyThrows
    public void registerBlade(final Class<? extends IBlade> blade) {
        this.blades.add(blade.getDeclaredConstructor().newInstance());
    }
}
