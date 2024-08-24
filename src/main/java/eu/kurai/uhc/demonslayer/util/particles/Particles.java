package eu.kurai.uhc.demonslayer.util.particles;

import com.google.common.base.Preconditions;
import lombok.Data;
import net.minecraft.server.v1_8_R3.EnumParticle;
import net.minecraft.server.v1_8_R3.PacketPlayOutWorldParticles;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;
import java.util.concurrent.CompletableFuture;

@Data
public class Particles {
    /**
     * Represents a particle with various properties.
     */
    private EnumParticle particle;
    /**
     * Represents a boolean value indicating whether the particles should be visible from a long distance. (from 256 to 65536 blocks away)
     */
    private boolean far;

    /**
     * The x-coordinate of the particles in a given point in 3D space.
     */
    private double x;
    /**
     * The y-coordinate of the particles in a given point in 3D space.
     */
    private double y;
    /**
     * The z-coordinate of the particles in a given point in 3D space.
     */
    private double z;
    /**
     * Represents the offset on the X-axis for positioning particles in the Particles class.
     * <p>
     * This variable is used to determine the amount of offset to apply to the X position of particles when rendering. In the context of the Particles class, particles are rendered
     * in the game world and this offset is used to position them relative to the specified coordinates.
     * <p>
     * The initial value of this variable is undefined. It is recommended to set it to a suitable value before using it to ensure proper placement of particles.
     *
     * @see Particles#Particles(EnumParticle, boolean, double, double, double, float, float, float, float, int, int[])
     * @see Particles#send(Player)
     */
    private float offsetX;
    /**
     * Represents the offset on the Y-axis for positioning particles in the Particles class.
     * <p>
     * This variable is used to determine the amount of offset to apply to the Y position of particles when rendering. In the context of the Particles class, particles are rendered
     * in the game world and this offset is used to position them relative to the specified coordinates.
     * <p>
     * The initial value of this variable is undefined. It is recommended to set it to a suitable value before using it to ensure proper placement of particles.
     *
     * @see Particles#Particles(EnumParticle, boolean, double, double, double, float, float, float, float, int, int[])
     * @see Particles#send(Player)
     */
    private float offsetY;
    /**
     * Represents the offset on the Z-axis for positioning particles in the Particles class.
     * <p>
     * This variable is used to determine the amount of offset to apply to the Z position of particles when rendering. In the context of the Particles class, particles are rendered
     * in the game world and this offset is used to position them relative to the specified coordinates.
     * <p>
     * The initial value of this variable is undefined. It is recommended to set it to a suitable value before using it to ensure proper placement of particles.
     *
     * @see Particles#Particles(EnumParticle, boolean, double, double, double, float, float, float, float, int, int[])
     * @see Particles#send(Player)
     */
    private float offsetZ;
    /**
     * The speed of the particle effect.
     * <p>
     * This variable is used to determine the speed at which the particle effect will be displayed. It is measured in units per tick.
     * The value must be a floating-point number.
     *
     * @see Particles
     */
    private float speed;

    /**
     * Represents the count of particles to display.
     * <p>
     * This variable represents the number of particles to display in a particle effect.
     * It determines the quantity of particles that will be emitted at a specific location.
     * The count must be a positive integer.
     */
    private int count;
    /**
     * Represents an array of integers used for storing additional data for a particle effect.
     */
    private int[] data;

    /**
     * Represents a particle effect in Minecraft.
     *
     * @param particle the type of particle to display
     * @param x        the x coordinate of the particle
     * @param y        the y coordinate of the particle
     * @param z        the z coordinate of the particle
     */
    public Particles(final @Nonnull EnumParticle particle, final double x, final double y, final double z) {
        this(particle, false, x, y, z, 0, 0, 0, 0, 0, new int[0]);
    }

    /**
     * Represents a particle effect in Minecraft.
     *
     * @param particle the type of particle to display
     * @param location the location of the particle
     */
    public Particles(final @Nonnull EnumParticle particle, final @Nonnull Location location) {
        this(particle, false, location, 0, 0, 0, 0, 0, new int[0]);
    }

    /**
     * Represents a particle effect in Minecraft.
     *
     * @param particle the type of particle to display
     * @param far      whether the particles are displayed at a long distance
     * @param location the location of the particle
     */
    public Particles(final @Nonnull EnumParticle particle, final boolean far, final @Nonnull Location location) {
        this(particle, far, location, 0, 0, 0, 0, 0, new int[0]);
    }

    /**
     * Represents a particle effect in Minecraft.
     *
     * @param particle the type of particle to display
     * @param far      whether the particles are displayed at a long distance
     * @param x        the x coordinate of the particle
     * @param y        the y coordinate of the particle
     * @param z        the z coordinate of the particle
     */
    public Particles(final @Nonnull EnumParticle particle, final boolean far, final double x, final double y, final double z) {
        this(particle, far, x, y, z, 0, 0, 0, 0, 0, new int[0]);
    }

    /**
     * Represents a particle effect in Minecraft.
     *
     * @param particle the type of particle to display
     * @param far      whether the particles are displayed at a long distance
     * @param x        the x coordinate of the particle
     * @param y        the y coordinate of the particle
     * @param z        the z coordinate of the particle
     * @param offsetX  the offset on the x-axis
     * @param offsetY  the offset on the y-axis
     * @param offsetZ  the offset on the z-axis
     * @param speed    the speed of the particle
     * @param count    the number of particles to display
     * @param data     additional data for the particle
     */
    public Particles(final @Nonnull EnumParticle particle, final boolean far, final double x, final double y, final double z, final float offsetX, final float offsetY, final float offsetZ, final float speed, final int count, final int[] data) {
        Preconditions.checkArgument(count >= 0, "The count must be a positive integer.");
        this.particle = particle;
        this.far = far;
        this.x = x;
        this.y = y;
        this.z = z;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.offsetZ = offsetZ;
        this.speed = speed;
        this.count = count;
        this.data = data;
    }

    /**
     * Represents a particle effect in Minecraft.
     *
     * @param particle the type of particle to display
     * @param far      whether the particles are displayed at a long distance
     * @param location the location of the particle
     * @param offsetX  the offset on the x-axis
     * @param offsetY  the offset on the y-axis
     * @param offsetZ  the offset on the z-axis
     * @param speed    the speed of the particle
     * @param count    the number of particles to display
     * @param data     additional data for the particle
     */
    public Particles(final @Nonnull EnumParticle particle, final boolean far, final @Nonnull Location location, final float offsetX, final float offsetY, final float offsetZ, final float speed, final int count, final int[] data) {
        Preconditions.checkArgument(count >= 0, "The count must be a positive integer.");
        this.particle = particle;
        this.far = far;
        this.x = location.getX();
        this.y = location.getY();
        this.z = location.getZ();
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.offsetZ = offsetZ;
        this.speed = speed;
        this.count = count;
        this.data = data;
    }

    /**
     * Sends a packet to a player to display particles at a specific location.
     *
     * @param player The player to send the packet to. Must not be null.
     */
    public void send(final @Nonnull Player player) {
        CompletableFuture.runAsync(() -> {
            PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles(particle, far, (float) x, (float) y, (float) z, offsetX, offsetY, offsetZ, speed, count, data);
            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
        });
    }
}
