package eu.unchat.uhc.demonslayer;

import dev.rollczi.litecommands.LiteCommands;
import dev.rollczi.litecommands.adventure.LiteAdventureExtension;
import dev.rollczi.litecommands.bukkit.LiteBukkitFactory;
import eu.unchat.tolgee.TolgeeClient;
import eu.unchat.tolgee.translation.TranslationHandler;
import eu.unchat.uhc.API;
import eu.unchat.uhc.demonslayer.command.DemonSlayerCommand;
import eu.unchat.uhc.demonslayer.command.permission.DSPermissionHandler;
import eu.unchat.uhc.demonslayer.command.resolver.ProfileResolver;
import eu.unchat.uhc.demonslayer.command.usage.DSUsageHandler;
import eu.unchat.uhc.demonslayer.command.validator.role.HasRole;
import eu.unchat.uhc.demonslayer.command.validator.role.HasRoleValidator;
import eu.unchat.uhc.demonslayer.command.validator.team.HasTeam;
import eu.unchat.uhc.demonslayer.command.validator.team.HasTeamValidator;
import eu.unchat.uhc.demonslayer.role.DSRoleHandler;
import eu.unchat.uhc.demonslayer.team.DSTeamHandler;
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
        saveDefaultConfig();

        TolgeeClient tolgeeClient = API.get().getTolgeeClient();
        tolgeeClient.login(getConfig().getString("tolgee-api", ""));
        TranslationHandler translationHandler = tolgeeClient.getTranslationHandler();
        translationHandler.loadKeys("8997");

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
