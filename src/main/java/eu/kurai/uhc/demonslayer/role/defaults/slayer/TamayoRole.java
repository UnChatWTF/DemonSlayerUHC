package eu.kurai.uhc.demonslayer.role.defaults.slayer;

import com.google.common.collect.Maps;
import eu.kurai.uhc.demonslayer.role.AbstractDSRole;
import eu.kurai.uhc.demonslayer.team.defaults.DemonTeam;
import eu.kurai.uhc.demonslayer.team.defaults.SlayerTeam;
import eu.unchat.uhc.API;
import eu.unchat.uhc.actionbar.IActionBar;
import eu.unchat.uhc.profile.IProfile;
import eu.unchat.uhc.role.AbstractRole;
import eu.unchat.uhc.role.Role;
import eu.unchat.uhc.util.CC;
import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.UUID;

@Getter
@Role(name = "Tamayo", identifier = "tamayo", material = Material.BREWING_STAND_ITEM, team = SlayerTeam.class)
public final class TamayoRole extends AbstractDSRole {

    private final Gender gender;
    private final Rank rank;
    private final Map<UUID, Location> demonBloods;
    private int blood;

    public TamayoRole() {
        this.gender = Gender.FEMALE;
        this.rank = Rank.B;

        this.blood = 0;

        this.demonBloods = Maps.newHashMap();

        registerKnownRole(YushiroRole.class);

        onTeamDeath(DemonTeam.class, (demon) -> {
            IProfile profile = AbstractRole.findPlayer(TamayoRole.class);
            if (!profile.isOnline()) {
                return;
            }

            profile.sendMessage(CC.info("Un démon est mort. Récupérez son sang en vous rendant sur son lieu de mort."));
            this.demonBloods.put(demon.getUniqueId(), demon.getPlayer().getLocation());
        });
    }

    @Override
    public void onSecond(Player player) {
        IActionBar actionBar = API.get().getActionBarHandler().getActionBar(player);

        for (Map.Entry<UUID, Location> entry : this.demonBloods.entrySet()) {
            actionBar.addActionBar("blood_" + entry.getKey(), getArrow(player, entry.getValue()) + " " + IProfile.of(entry.getKey()).getName());

            if (player.getLocation().distance(entry.getValue()) <= 1) {
                player.sendMessage(CC.info("Vous avez récupéré le sang de " + IProfile.of(entry.getKey()).getName() + "."));
                actionBar.removeActionBar("blood_" + entry.getKey());
                this.demonBloods.remove(entry.getKey());
                this.blood++;
            }
        }
    }

    @Override
    public void onDay(Player player) {
        IProfile profile = IProfile.of(player.getUniqueId());
        profile.setStrengthBuffer(profile.getStrengthBuffer() - 15);
    }

    @Override
    public void onNight(Player player) {
        IProfile profile = IProfile.of(player.getUniqueId());
        profile.setStrengthBuffer(profile.getStrengthBuffer() + 15);
    }

    private String getArrow(final @NotNull Player player, final Location centerLocation) {
        final Location location = player.getLocation();
        String[] arrows = {"⬆", "⬈", "➡", "⬊", "⬇", "⬋", "⬅", "⬉", "⬆"};
        Vector vector = location.getDirection();
        Vector normalize = centerLocation.subtract(location).toVector().normalize();
        double degrees = Math.toDegrees(Math.atan2(normalize.getZ(), normalize.getX()));
        degrees -= Math.toDegrees(Math.atan2(vector.getZ(), vector.getX()));
        degrees = ((int) (degrees + 22.5D) % 360);
        if (degrees < 0.0D) {
            degrees += 360.0D;
        }
        return arrows[(int) Math.round(degrees / 45.0D) % 8];
    }
}
