package eu.unchat.uhc.demonslayer.role;

import eu.unchat.uhc.API;
import eu.unchat.uhc.demonslayer.role.defaults.demon.*;
import eu.unchat.uhc.demonslayer.role.defaults.slayer.*;
import eu.unchat.uhc.profile.IProfile;
import eu.unchat.uhc.demonslayer.role.AbstractDSRole;
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
        registerRole(TanjiroRole.class);
        registerRole(ZenitsuRole.class);
        registerRole(InosukeRole.class);
        registerRole(NezukoRole.class);
        registerRole(GiyuRole.class);
        registerRole(ShinobuRole.class);
        registerRole(KyojuroRole.class);
        registerRole(ObanaiRole.class);
        registerRole(TamayoRole.class);
        registerRole(YushiroRole.class);
        registerRole(TengenRole.class);

        registerRole(MuzanRole.class);
        registerRole(NakimeRole.class);
        registerRole(KumoRole.class);
        registerRole(DakiRole.class);
        registerRole(GyutaroRole.class);
    }

    private void registerRole(final Class<? extends AbstractDSRole> clazz) {
        API.get().getRoleHandler().registerRole(clazz);
    }
}
