package eu.unchat.uhc.demonslayer.team;

import eu.unchat.uhc.API;
import eu.unchat.uhc.demonslayer.team.defaults.DemonTeam;
import eu.unchat.uhc.demonslayer.team.defaults.SlayerTeam;
import eu.unchat.uhc.demonslayer.team.defaults.SolitaryTeam;
import eu.unchat.uhc.team.AbstractTeam;

public final class DSTeamHandler {
    public DSTeamHandler() {
        init();
    }

    private void init() {
        registerTeam(SlayerTeam.class);
        registerTeam(DemonTeam.class);
        registerTeam(SolitaryTeam.class);
    }

    private void registerTeam(final Class<? extends AbstractTeam> clazz) {
        API.get().getTeamHandler().registerTeam(clazz);
    }
}
