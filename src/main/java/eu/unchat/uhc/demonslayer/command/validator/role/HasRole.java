package eu.unchat.uhc.demonslayer.command.validator.role;

import eu.unchat.uhc.role.AbstractRole;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface HasRole {
    Class<? extends AbstractRole> value();
}
