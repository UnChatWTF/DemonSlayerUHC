package eu.unchat.uhc.demonslayer.role;

import eu.unchat.uhc.API;
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
    }

    private void registerRole(final Class<? extends AbstractRole> clazz) {
        API.get().getRoleHandler().registerRole(clazz);
    }
}
