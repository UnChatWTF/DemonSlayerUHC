package eu.kurai.uhc.demonslayer.command.resolver;

import dev.rollczi.litecommands.argument.Argument;
import dev.rollczi.litecommands.argument.parser.ParseResult;
import dev.rollczi.litecommands.argument.resolver.ArgumentResolver;
import dev.rollczi.litecommands.invocation.Invocation;
import dev.rollczi.litecommands.suggestion.SuggestionContext;
import dev.rollczi.litecommands.suggestion.SuggestionResult;
import eu.unchat.uhc.API;
import eu.unchat.uhc.profile.IProfile;
import eu.unchat.uhc.util.CC;
import org.bukkit.command.CommandSender;

public final class ProfileResolver extends ArgumentResolver<CommandSender, IProfile> {
    @Override
    protected ParseResult<IProfile> parse(Invocation<CommandSender> invocation, Argument<IProfile> argument, String s) {
        IProfile found = API.get()
                .getProfileHandler()
                .getAllProfiles().stream()
                .filter(profile -> profile.getName().equalsIgnoreCase(s))
                .findFirst()
                .orElse(null);

        if (found == null) {
            return ParseResult.failure(CC.error("Profile with name \"" + s + "\" does not exist"));
        }

        return ParseResult.success(found);
    }

    @Override
    public SuggestionResult suggest(Invocation<CommandSender> invocation, Argument<IProfile> argument, SuggestionContext context) {
        return API.get()
                .getProfileHandler()
                .getAllProfiles()
                .stream()
                .map(IProfile::getName)
                .collect(SuggestionResult.collector());
    }
}
