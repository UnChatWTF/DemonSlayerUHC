package eu.kurai.uhc.demonslayer.team;

import eu.kurai.uhc.demonslayer.team.defaults.DemonTeam;
import eu.kurai.uhc.demonslayer.team.defaults.SlayerTeam;
import eu.kurai.uhc.demonslayer.team.defaults.SolitaryTeam;
import eu.kurai.uhc.demonslayer.team.defaults.jigoro.JigoroDuoTeam;
import eu.kurai.uhc.demonslayer.team.defaults.jigoro.JigoroSolitaryTeam;
import eu.kurai.uhc.demonslayer.team.defaults.jigoro.JigoroTrioTeam;
import eu.unchat.uhc.API;
import eu.unchat.uhc.team.AbstractTeam;

public final class DSTeamHandler {
    public DSTeamHandler() {
        init();
    }

    private void init() {
        registerTeam(SlayerTeam.class);
        registerTeam(DemonTeam.class);
        registerTeam(SolitaryTeam.class);
        registerTeam(JigoroSolitaryTeam.class);
        registerTeam(JigoroDuoTeam.class);
        registerTeam(JigoroTrioTeam.class);
    }

    private void registerTeam(final Class<? extends AbstractTeam> clazz) {
        API.get().getTeamHandler().registerTeam(clazz);
    }
}
