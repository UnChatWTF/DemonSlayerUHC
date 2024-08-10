package eu.unchat.uhc.demonslayer.command.validator.team;

import eu.unchat.uhc.team.AbstractTeam;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface HasTeam {
    Class<? extends AbstractTeam>[] value();
}
