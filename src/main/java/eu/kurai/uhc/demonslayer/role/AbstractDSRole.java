package eu.kurai.uhc.demonslayer.role;

import eu.unchat.uhc.role.AbstractRole;

public abstract class AbstractDSRole extends AbstractRole {

    public abstract Gender getGender();

    public abstract Rank getRank();

    public enum Gender {
        MALE,
        FEMALE
    }

    public enum Rank {
        S,
        A,
        B,
        C
    }

}
