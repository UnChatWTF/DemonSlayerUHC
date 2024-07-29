package eu.unchat.uhc.demonslayer.command.validator.role;

import dev.rollczi.litecommands.annotations.validator.requirment.AnnotatedValidator;
import dev.rollczi.litecommands.command.executor.CommandExecutor;
import dev.rollczi.litecommands.invocation.Invocation;
import dev.rollczi.litecommands.requirement.Requirement;
import dev.rollczi.litecommands.validator.ValidatorResult;
import eu.unchat.uhc.API;
import eu.unchat.uhc.profile.IProfile;
import eu.unchat.uhc.role.AbstractRole;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

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

        if (profile.getRole() == null || !AbstractRole.isRole(profile, hasRole.value())) {
            AbstractRole role = API.get().getRoleHandler().getRole(hasRole.value());
            if (role == null) {
                return ValidatorResult.invalid("&cLe rôle n'existe pas ?!");
            }
            return ValidatorResult.invalid("&cVous n'avez pas le rôle " + role.getName() + ".");
        }

        return ValidatorResult.valid();
    }
}
