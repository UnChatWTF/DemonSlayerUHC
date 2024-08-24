package eu.kurai.uhc.demonslayer.role;

import eu.kurai.uhc.demonslayer.role.defaults.demon.*;
import eu.kurai.uhc.demonslayer.role.defaults.slayer.*;
import eu.kurai.uhc.demonslayer.role.defaults.solitary.JigoroRole;
import eu.kurai.uhc.demonslayer.role.defaults.solitary.KyogaiRole;
import eu.kurai.uhc.demonslayer.role.defaults.solitary.ShinjuroRole;
import eu.kurai.uhc.demonslayer.role.defaults.solitary.YoriichiRole;
import eu.unchat.uhc.API;
import eu.unchat.uhc.profile.IProfile;
import eu.unchat.uhc.team.AbstractTeam;

import java.util.Arrays;
import java.util.List;

public final class DSRoleHandler {
    public DSRoleHandler() {
        init();
    }

    @SafeVarargs
    public final List<IProfile> getTeamList(final Class<? extends AbstractTeam>... teams) {
        return API.get()
                .getProfileHandler()
                .getAllProfiles()
                .stream()
                .filter(profile -> profile.getRole() != null)
                .filter(profile -> profile.getRole().getTeam() != null)
                .filter(profile -> Arrays.stream(teams).anyMatch(team -> team.isAssignableFrom(profile.getRole().getTeam())))
                .toList();
    }

    private void init() {
        registerRole(GiyuRole.class);
        registerRole(InosukeRole.class);
        registerRole(KagayaRole.class);
        registerRole(KiriyaRole.class);
        registerRole(KyojuroRole.class);
        registerRole(MitsuriRole.class);
        registerRole(NezukoRole.class);
        registerRole(ObanaiRole.class);
        registerRole(ShinobuRole.class);
        registerRole(TamayoRole.class);
        registerRole(TanjiroRole.class);
        registerRole(TengenRole.class);
        registerRole(YushiroRole.class);
        registerRole(ZenitsuRole.class);

        registerRole(MuzanRole.class);
        registerRole(KokushiboRole.class);
        registerRole(NakimeRole.class);
        registerRole(KumoRole.class);
        registerRole(DakiRole.class);
        registerRole(GyutaroRole.class);
        registerRole(DomaRole.class);
        registerRole(KaigakuRole.class);
        registerRole(HantenguRole.class);

        registerRole(YoriichiRole.class);
        registerRole(KyogaiRole.class);
        registerRole(ShinjuroRole.class);
        registerRole(JigoroRole.class);
    }

    private void registerRole(final Class<? extends AbstractDSRole> clazz) {
        API.get().getRoleHandler().registerRole(clazz);
    }
}
