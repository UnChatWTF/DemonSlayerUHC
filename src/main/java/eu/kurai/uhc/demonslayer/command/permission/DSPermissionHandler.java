package eu.kurai.uhc.demonslayer.command.permission;

import dev.rollczi.litecommands.handler.result.ResultHandlerChain;
import dev.rollczi.litecommands.invocation.Invocation;
import dev.rollczi.litecommands.permission.MissingPermissions;
import dev.rollczi.litecommands.permission.MissingPermissionsHandler;
import eu.unchat.uhc.util.CC;
import org.bukkit.command.CommandSender;

public final class DSPermissionHandler implements MissingPermissionsHandler<CommandSender> {
    @Override
    public void handle(Invocation<CommandSender> invocation, MissingPermissions permissions, ResultHandlerChain<CommandSender> chain) {
        String permission = permissions.asJoinedText();
        CommandSender sender = invocation.sender();

        sender.sendMessage(CC.error("You don't have permission to execute this command! &7(" + permission + ")"));
    }
}
