package eu.kurai.uhc.demonslayer;

import dev.rollczi.litecommands.LiteCommands;
import dev.rollczi.litecommands.adventure.LiteAdventureExtension;
import dev.rollczi.litecommands.bukkit.LiteBukkitFactory;
import eu.kurai.uhc.demonslayer.command.DemonSlayerCommand;
import eu.kurai.uhc.demonslayer.command.permission.DSPermissionHandler;
import eu.kurai.uhc.demonslayer.command.resolver.ProfileResolver;
import eu.kurai.uhc.demonslayer.command.usage.DSUsageHandler;
import eu.kurai.uhc.demonslayer.command.validator.role.HasRole;
import eu.kurai.uhc.demonslayer.command.validator.role.HasRoleValidator;
import eu.kurai.uhc.demonslayer.command.validator.team.HasTeam;
import eu.kurai.uhc.demonslayer.command.validator.team.HasTeamValidator;
import eu.kurai.uhc.demonslayer.role.DSRoleHandler;
import eu.kurai.uhc.demonslayer.team.DSTeamHandler;
import eu.unchat.uhc.API;
import eu.unchat.uhc.profile.IProfile;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public final class DSPlugin extends JavaPlugin {

    private static DSPlugin instance;
    private final DSTeamHandler teamHandler;
    private final DSRoleHandler roleHandler;
    private LiteCommands<CommandSender> liteCommands;

    public DSPlugin() {
        instance = this;

        this.teamHandler = new DSTeamHandler();
        this.roleHandler = new DSRoleHandler();

        API.get().getGameHandler().getConfiguration().setName("&3&lDemon Slayer UHC");
    }

    public static DSPlugin get() {
        return instance;
    }

    @Override
    public void onEnable() {
        Bukkit.getScheduler().runTaskLater(this, () -> {
            this.liteCommands = LiteBukkitFactory.builder()
                    .commands(new DemonSlayerCommand())
                    .annotations(config -> {
                        config.validator(Player.class, HasRole.class, new HasRoleValidator());
                        config.validator(Player.class, HasTeam.class, new HasTeamValidator());
                    })
                    .extension(new LiteAdventureExtension<>(), configuration -> configuration
                            .miniMessage(true)
                            .colorizeArgument(true)
                            .legacyColor(true)
                    )
                    .argument(IProfile.class, new ProfileResolver())
                    .invalidUsage(new DSUsageHandler())
                    .missingPermission(new DSPermissionHandler())
                    .build();
        }, 5L);

        API.get().getModuleHandler().registerModule(DS.class);
        API.get().getModuleHandler().setCurrentModule(DS.class);
    }
}
