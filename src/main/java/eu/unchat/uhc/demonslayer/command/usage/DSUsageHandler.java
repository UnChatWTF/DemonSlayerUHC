package eu.unchat.uhc.demonslayer.command.usage;

import dev.rollczi.litecommands.handler.result.ResultHandlerChain;
import dev.rollczi.litecommands.invalidusage.InvalidUsage;
import dev.rollczi.litecommands.invalidusage.InvalidUsageHandler;
import dev.rollczi.litecommands.invocation.Invocation;
import dev.rollczi.litecommands.schematic.Schematic;
import eu.unchat.uhc.util.CC;
import org.bukkit.command.CommandSender;

public final class DSUsageHandler implements InvalidUsageHandler<CommandSender> {
    @Override
    public void handle(Invocation<CommandSender> invocation, InvalidUsage<CommandSender> result, ResultHandlerChain<CommandSender> chain) {
        CommandSender sender = invocation.sender();
        Schematic schematic = result.getSchematic();

        if (schematic.isOnlyFirst()) {
            sender.sendMessage(CC.translate("&cUsage: &7" + schematic.first()));
            return;
        }

        sender.sendMessage(CC.translate("&cVoici les différentes commandes disponibles:"));
        for (String string : schematic.all()) {
            sender.sendMessage(CC.translate(" &8- &7" + string));
        }
    }
}
