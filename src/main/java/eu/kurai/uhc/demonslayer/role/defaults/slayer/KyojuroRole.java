package eu.kurai.uhc.demonslayer.role.defaults.slayer;

import eu.kurai.uhc.demonslayer.power.slayer.BreathPower;
import eu.kurai.uhc.demonslayer.power.slayer.kyojuro.FireSeaPower;
import eu.kurai.uhc.demonslayer.power.slayer.kyojuro.PurgatoryPower;
import eu.kurai.uhc.demonslayer.role.AbstractDSRole;
import eu.kurai.uhc.demonslayer.team.defaults.SlayerTeam;
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
public final class KyojuroRole extends AbstractDSRole {

    private final Gender gender;
    private final Rank rank;

    public KyojuroRole() {
        this.gender = Gender.MALE;
        this.rank = Rank.A;

        registerPower(new BreathPower(this, new FireSeaPower(), new PurgatoryPower()));
    }

    @Override
    public void onDistribute(Player player) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, Integer.MAX_VALUE, 0, false, false), true);
        player.getInventory().addItem(new ItemBuilder(Material.ENCHANTED_BOOK).enchant(Enchantment.FIRE_ASPECT, 1).asItemStack());
        player.getInventory().addItem(new ItemBuilder(Material.ENCHANTED_BOOK).enchant(Enchantment.ARROW_FIRE, 1).asItemStack());
    }
}
