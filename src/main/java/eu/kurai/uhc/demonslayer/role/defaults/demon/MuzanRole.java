package eu.kurai.uhc.demonslayer.role.defaults.demon;

import com.google.common.collect.Lists;
import eu.kurai.uhc.demonslayer.DSPlugin;
import eu.kurai.uhc.demonslayer.role.AbstractDSRole;
import eu.kurai.uhc.demonslayer.role.defaults.slayer.NezukoRole;
import eu.kurai.uhc.demonslayer.team.defaults.DemonTeam;
import eu.unchat.uhc.API;
import eu.unchat.uhc.power.defaults.AbstractCommandPower;
import eu.unchat.uhc.profile.IProfile;
import eu.unchat.uhc.role.Role;
import lombok.Getter;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.List;

@Getter
@Role(name = "Muzan", identifier = "muzan", team = DemonTeam.class, material = Material.REDSTONE)
public final class MuzanRole extends AbstractDSRole {

    private final Gender gender;
    private final Rank rank;

    public MuzanRole() {
        this.gender = Gender.MALE;
        this.rank = Rank.S;

        registerPower(new BloodGiftPower());
    }

    @Override
    public void onDescriptionSent(Player player) {
        List<IProfile> list = Lists.newArrayList();
        list.addAll(DSPlugin.get().getRoleHandler().getTeamList(DemonTeam.class));
        list.add(findPlayer(NezukoRole.class));
        Collections.shuffle(list);

        Bukkit.getScheduler().runTaskLater(DSPlugin.get(), () -> {
            for (IProfile profile : list) {
                if (profile == null) {
                    continue;
                }

                Audience audience = API.get().getAdventure().player(player);
                audience.sendMessage(MiniMessage.miniMessage().deserialize(" <gray>-</gray> <dark_aqua>" + profile.getName() + "</dark_aqua>"));
            }
        }, 30L);
    }

    @Override
    public void onDistribute(Player player) {
        IProfile profile = IProfile.of(player.getUniqueId());
        profile.setResistanceBuffer(profile.getResistanceBuffer() + 20);
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

    @Getter
    public static final class BloodGiftPower extends AbstractCommandPower {
        private final String name, identifier, commandName;

        public BloodGiftPower() {
            this.name = "&c&lDon de sang";
            this.identifier = "blood_gift";
            this.commandName = "ds blood";

            this.setInitialCooldown(0);
            this.setInitialUses(2);
        }

        @Override
        public Result onCommand(final Player player, final String[] args) {
            return Result.SUCCESSFUL;
        }
    }
}
