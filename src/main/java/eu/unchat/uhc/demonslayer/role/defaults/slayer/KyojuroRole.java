package eu.unchat.uhc.demonslayer.role.defaults.slayer;

import eu.unchat.uhc.demonslayer.power.slayer.kyojuro.FireSeaPower;
import eu.unchat.uhc.demonslayer.power.slayer.kyojuro.PurgatoryPower;
import eu.unchat.uhc.demonslayer.team.defaults.SlayerTeam;
import eu.unchat.uhc.power.AbstractParentPower;
import eu.unchat.uhc.role.AbstractRole;
import eu.unchat.uhc.role.Role;
import eu.unchat.uhc.util.ItemBuilder;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

@Getter
@Role(name = "Kyojuro", identifier = "kyojuro", team = SlayerTeam.class, material = Material.FLINT)
public final class KyojuroRole extends AbstractRole {
    private final String tolgeeReference;

    public KyojuroRole() {
        this.tolgeeReference = "fr.unchat.demonslayer.role.kyojuro";

        registerPower(new IntegralConcentrationBreathPower());
    }

    @Override
    public void onDistribute(Player player) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, Integer.MAX_VALUE, 0, false, false), true);
        player.getInventory().addItem(new ItemBuilder(Material.ENCHANTED_BOOK).enchant(Enchantment.FIRE_ASPECT, 1).asItemStack());
        player.getInventory().addItem(new ItemBuilder(Material.ENCHANTED_BOOK).enchant(Enchantment.ARROW_FIRE, 1).asItemStack());
    }

    @Getter
    private static final class IntegralConcentrationBreathPower extends AbstractParentPower {
        private final String name;

        public IntegralConcentrationBreathPower() {
            this.name = "&a&lSOUFFLE";
            registerChild(new FireSeaPower());
            registerChild(new PurgatoryPower());
        }
    }
}
