package eu.unchat.uhc.demonslayer.role;

import eu.unchat.uhc.role.AbstractRole;

public abstract class AbstractDSRole extends AbstractRole {

    public abstract Gender getGender();

    public enum Gender {
        MALE,
        FEMALE
    }

}
