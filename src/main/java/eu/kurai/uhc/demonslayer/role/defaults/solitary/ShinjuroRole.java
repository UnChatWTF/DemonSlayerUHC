package eu.kurai.uhc.demonslayer.role.defaults.solitary;

import eu.kurai.uhc.demonslayer.power.solitary.shinjuro.AlcoholPower;
import eu.kurai.uhc.demonslayer.role.AbstractDSRole;
import eu.kurai.uhc.demonslayer.team.defaults.SolitaryTeam;
import eu.unchat.uhc.actionbar.IActionBar;
import eu.unchat.uhc.profile.IProfile;
import eu.unchat.uhc.role.Role;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

@Getter
@Role(name = "Shinjuro", identifier = "shinjuro", material = Material.POTION, team = SolitaryTeam.class)
public final class ShinjuroRole extends AbstractDSRole {

    private final Gender gender;
    private final Rank rank;

    public ShinjuroRole() {
        this.gender = Gender.MALE;
        this.rank = Rank.A;
        registerPower(new AlcoholPower());
    }

    @Override
    public void onDistribute(final Player player) {
        final IProfile profile = IProfile.of(player.getUniqueId());
        profile.getRegenerationData().setValue(1);
        profile.getRegenerationData().setDelay(20);
        profile.setStrengthBuffer(profile.getStrengthBuffer() + 15);
        player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, Integer.MAX_VALUE, 0, false, false), true);
    }

    @Override
    public void onSecond(final Player player) {
        final IProfile profile = IProfile.of(player.getUniqueId());
        if (player.getFireTicks() > 0) {
            profile.getRegenerationData().setDelay(10);
        }

        AlcoholPower power = (AlcoholPower) profile.getPower(AlcoholPower.class);
        if (power == null) {
            return;
        }

        power.setAlcohol(Math.max(power.getAlcohol() - 2, 0));
        int alcohol = power.getAlcohol();
        AlcoholPower.Stade stade = alcohol < 25
                ? AlcoholPower.Stade.FIRST : (alcohol < 50
                ? AlcoholPower.Stade.SECOND : (alcohol < 75
                ? AlcoholPower.Stade.THIRD : AlcoholPower.Stade.FOURTH));

        if (power.getStade().equals(stade)) {
            return;
        }

        power.setStade(stade);

        if (stade == AlcoholPower.Stade.FIRST) {
            profile.setSpeedBuffer(profile.getSpeedBuffer() - 0.02F);
        } else if (stade == AlcoholPower.Stade.SECOND) {
            profile.setResistanceBuffer(profile.getResistanceBuffer() - 20);
        }
        final IActionBar actionBar = profile.getActionBar();
        actionBar.addActionBar("alcohol", "&3Alcool: &b" + power.getAlcohol() + "%");
    }
}
