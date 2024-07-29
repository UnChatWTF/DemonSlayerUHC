package eu.unchat.uhc.demonslayer.speciality.blade;

import com.google.common.collect.Lists;
import eu.unchat.uhc.demonslayer.speciality.blade.defaults.BlackBlade;
import eu.unchat.uhc.demonslayer.speciality.blade.defaults.YellowBlade;
import lombok.SneakyThrows;

import java.util.Collection;

public class DSBladeHandler {
    private final Collection<IBlade> blades;

    public DSBladeHandler() {
        this.blades = Lists.newArrayList();
        init();
    }

    private void init() {
        registerBlade(BlackBlade.class);
        registerBlade(YellowBlade.class);
    }

    @SneakyThrows
    public void registerBlade(final Class<? extends IBlade> blade) {
        this.blades.add(blade.getDeclaredConstructor().newInstance());
    }
}
