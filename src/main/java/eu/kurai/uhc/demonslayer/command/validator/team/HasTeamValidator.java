package eu.kurai.uhc.demonslayer.command.validator.team;

import dev.rollczi.litecommands.annotations.validator.requirment.AnnotatedValidator;
import dev.rollczi.litecommands.command.executor.CommandExecutor;
import dev.rollczi.litecommands.invocation.Invocation;
import dev.rollczi.litecommands.requirement.Requirement;
import dev.rollczi.litecommands.validator.ValidatorResult;
import eu.unchat.uhc.profile.IProfile;
import eu.unchat.uhc.team.AbstractTeam;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

public final class HasTeamValidator implements AnnotatedValidator<CommandSender, Player, HasTeam> {
    @Override
    public ValidatorResult validate(
            Invocation<CommandSender> invocation,
            CommandExecutor<CommandSender> executor,
            Requirement<Player> requirement,
            Player player,
            HasTeam hasTeam
    ) {
        IProfile profile = IProfile.of(player.getUniqueId());

        if (profile.getTeam() == null) {
            return ValidatorResult.invalid("&cVous n'avez pas de camp.");
        }

        if (Arrays.stream(hasTeam.value()).noneMatch(clazz -> AbstractTeam.isTeam(profile, clazz))) {
            return ValidatorResult.invalid("&cVous ou le joueur ciblé n'avez pas le camp requis.");
        }

        return ValidatorResult.valid();
    }
}
