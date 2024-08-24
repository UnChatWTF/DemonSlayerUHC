package eu.kurai.uhc.demonslayer.command.validator.role;

import dev.rollczi.litecommands.annotations.validator.requirment.AnnotatedValidator;
import dev.rollczi.litecommands.command.executor.CommandExecutor;
import dev.rollczi.litecommands.invocation.Invocation;
import dev.rollczi.litecommands.requirement.Requirement;
import dev.rollczi.litecommands.validator.ValidatorResult;
import eu.kurai.uhc.demonslayer.role.AbstractDSRole;
import eu.unchat.uhc.profile.IProfile;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

public final class HasRoleValidator implements AnnotatedValidator<CommandSender, Player, HasRole> {
    @Override
    public ValidatorResult validate(
            Invocation<CommandSender> invocation,
            CommandExecutor<CommandSender> executor,
            Requirement<Player> requirement,
            Player player,
            HasRole hasRole
    ) {
        IProfile profile = IProfile.of(player.getUniqueId());

        if (profile.getRole() == null) {
            return ValidatorResult.invalid("Vous n'avez pas de rôle.");
        }

        if (Arrays.stream(hasRole.value()).noneMatch(clazz -> AbstractDSRole.isRole(profile, clazz))) {
            return ValidatorResult.invalid("Vous ou le joueur ciblé n'avez pas le rôle requis.");
        }

        return ValidatorResult.valid();
    }
}
