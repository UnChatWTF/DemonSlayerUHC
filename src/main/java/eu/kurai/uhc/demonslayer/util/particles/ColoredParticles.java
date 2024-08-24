package eu.kurai.uhc.demonslayer.util.particles;

import net.minecraft.server.v1_8_R3.EnumParticle;
import org.bukkit.Location;

import javax.annotation.Nonnull;
import java.awt.*;

public class ColoredParticles extends Particles {
    /**
     * Creates a colored particle effect.
     *
     * @param particle the type of particle to display
     * @param location the location of the particle
     * @param color    the color of the particle (using {@link Color})
     * @param count    the number of particles to display
     * @param data     additional data for the particle
     */
    public ColoredParticles(@Nonnull EnumParticle particle, Location location, @Nonnull Color color, int count, int[] data) {
        this(particle, false, location, color, count, data);
    }

    /**
     * Creates a colored particle effect.
     *
     * @param particle the type of particle to display
     * @param far      whether the particles are displayed at a long distance
     * @param location the location of the particle
     * @param color    the color of the particle (using {@link Color})
     */
    public ColoredParticles(@Nonnull EnumParticle particle, boolean far, Location location, @Nonnull Color color) {
        this(particle, far, location.getX(), location.getY(), location.getZ(), color, 0, new int[0]);
    }


    /**
     * Creates a colored particle effect.
     *
     * @param particle the type of particle to display
     * @param location the location of the particle
     * @param color    the color of the particle (using {@link Color})
     */
    public ColoredParticles(@Nonnull EnumParticle particle, Location location, @Nonnull Color color) {
        this(particle, false, location.getX(), location.getY(), location.getZ(), color, 0, new int[0]);
    }

    /**
     * Creates a colored particle effect.
     *
     * @param particle the type of particle to display
     * @param far      whether the particles are displayed at a long distance
     * @param x        the x coordinate of the particle
     * @param y        the y coordinate of the particle
     * @param z        the z coordinate of the particle
     * @param color    the color of the particle (using {@link Color})
     * @param count    the number of particles to display
     * @param data     additional data for the particle
     */
    public ColoredParticles(final @Nonnull EnumParticle particle, final boolean far, final double x, final double y, final double z, final @Nonnull Color color, final int count, final int[] data) {
        super(particle, far, x, y, z, 0, 0, 0, 1, count, data);
        float r = color.getRed() / 255f;
        float g = color.getGreen() / 255f;
        float b = color.getBlue() / 255f;

        if (r == 0 && g == 0 && b == 0) {
            r = 0.001f;
            g = 0.001f;
            b = 0.001f;
        }

        this.setOffsetX(r);
        this.setOffsetY(g);
        this.setOffsetZ(b);
    }

    /**
     * Creates a colored particle effect.
     *
     * @param particle the type of particle to display
     * @param far      whether the particles are displayed at a long distance
     * @param location the location of the particle
     * @param color    the color of the particle (using {@link Color})
     * @param count    the number of particles to display
     * @param data     additional data for the particle
     */
    public ColoredParticles(final @Nonnull EnumParticle particle, final boolean far, final @Nonnull Location location, final @Nonnull Color color, final int count, final int[] data) {
        this(particle, far, location.getX(), location.getY(), location.getZ(), color, count, data);
    }
}
