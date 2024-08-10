package eu.unchat.uhc.demonslayer.role;

import eu.unchat.uhc.API;
import eu.unchat.uhc.demonslayer.role.defaults.demon.DakiRole;
import eu.unchat.uhc.demonslayer.role.defaults.demon.KumoRole;
import eu.unchat.uhc.demonslayer.role.defaults.demon.MuzanRole;
import eu.unchat.uhc.demonslayer.role.defaults.demon.NakimeRole;
import eu.unchat.uhc.demonslayer.role.defaults.slayer.*;
import eu.unchat.uhc.role.AbstractRole;

public final class DSRoleHandler {
    public DSRoleHandler() {
        init();
    }

    private void init() {
        registerRole(TanjiroRole.class);
        registerRole(ZenitsuRole.class);
        registerRole(InosukeRole.class);
        registerRole(NezukoRole.class);
        registerRole(GiyuRole.class);
        registerRole(ShinobuRole.class);
        registerRole(KyojuroRole.class);

        registerRole(MuzanRole.class);
        registerRole(NakimeRole.class);
        registerRole(KumoRole.class);
        registerRole(DakiRole.class);
    }

    private void registerRole(final Class<? extends AbstractRole> clazz) {
        API.get().getRoleHandler().registerRole(clazz);
    }
}
