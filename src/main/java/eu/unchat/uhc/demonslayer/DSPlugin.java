package eu.unchat.uhc.demonslayer;

import dev.rollczi.litecommands.LiteCommands;
import dev.rollczi.litecommands.bukkit.LiteBukkitFactory;
import eu.unchat.uhc.API;
import eu.unchat.uhc.demonslayer.command.DemonSlayerCommand;
import eu.unchat.uhc.demonslayer.command.permission.DSPermissionHandler;
import eu.unchat.uhc.demonslayer.command.usage.DSUsageHandler;
import eu.unchat.uhc.demonslayer.command.validator.role.HasRole;
import eu.unchat.uhc.demonslayer.command.validator.role.HasRoleValidator;
import eu.unchat.uhc.demonslayer.role.DSRoleHandler;
import eu.unchat.uhc.demonslayer.team.DSTeamHandler;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class DSPlugin extends JavaPlugin {

    private static DSPlugin instance;

    private LiteCommands<CommandSender> liteCommands;

    public DSPlugin() {
        instance = this;

        new DSTeamHandler();
        new DSRoleHandler();
    }

    @Override
    public void onEnable() {
        Bukkit.getScheduler().runTaskLater(this, () -> {
            this.liteCommands = LiteBukkitFactory.builder()
                    .commands(new DemonSlayerCommand())
                    .annotations(config ->
                            config.validator(Player.class, HasRole.class, new HasRoleValidator())
                    )
                    .invalidUsage(new DSUsageHandler())
                    .missingPermission(new DSPermissionHandler())
                    .build();
        }, 5L);

        API.get().getModuleHandler().registerModule(DS.class);
        API.get().getModuleHandler().setCurrentModule(DS.class);
    }

    public static DSPlugin get() {
        return instance;
    }
}
