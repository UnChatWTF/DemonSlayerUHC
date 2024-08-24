package eu.kurai.uhc.demonslayer.power.demon.doma;

import eu.unchat.uhc.power.defaults.AbstractItemPower;
import eu.unchat.uhc.profile.IProfile;
import eu.unchat.uhc.util.CC;
import eu.unchat.uhc.util.ItemBuilder;
import lombok.Getter;
import net.minecraft.server.v1_8_R3.EntityZombie;
import net.minecraft.server.v1_8_R3.GenericAttributes;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.inventory.ItemStack;

@Getter
public final class DivineCrystalChildPower extends AbstractItemPower implements Listener {

    private final String name, identifier;


    private final InteractionType interactionType = InteractionType.RIGHT_CLICK;

    private Zombie zombie;

    public DivineCrystalChildPower() {
        this.name = "&c&lEnfant divin de crystal";
        this.identifier = "divine_crystal_child";

        this.setInitialCooldown(15 * 60);
        this.setInitialUses(-1);
    }

    @Override
    public Result onInteract(Player player, InteractionType type) {
        if (!type.equals(InteractionType.RIGHT_CLICK)) {
            return Result.FAILED;
        }

        Location location = player.getLocation();

        EntityZombie entityZombie = new EntityZombie(((CraftPlayer) player).getHandle().getWorld());
        entityZombie.getAttributeInstance(GenericAttributes.maxHealth).setValue(20);
        entityZombie.setHealth(20);
        entityZombie.setCustomNameVisible(true);
        entityZombie.setCustomName(CC.translate("&3&lENFANT DIVIN DE CRISTAL"));
        entityZombie.setBaby(true);
        entityZombie.setLocation(location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
        entityZombie.setGoalTarget(((CraftPlayer) player).getHandle(), EntityTargetEvent.TargetReason.CUSTOM, true);

        ((CraftWorld) location.getWorld()).addEntity(entityZombie, CreatureSpawnEvent.SpawnReason.CUSTOM);
        this.zombie = (Zombie) entityZombie.getBukkitEntity();
        return Result.SUCCESSFUL;
    }

    @Override
    public ItemStack getIcon() {
        return new ItemBuilder(Material.PACKED_ICE)
                .name(formatName())
                .glowing(true)
                .asItemStack();
    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Zombie zombieDamager) || !(event.getEntity() instanceof Player player)) {
            System.out.println("Not a zombie or not a player");
            return;
        }

        if (!zombieDamager.getUniqueId().equals(this.zombie.getUniqueId())) {
            System.out.println("Zombie not equals");
            return;
        }

        System.out.println("Zombie damage player");
        IProfile.of(player.getUniqueId()).stun(4, true);
    }


}
