package eu.kurai.uhc.demonslayer.power.slayer.zenitsu;

import eu.kurai.uhc.demonslayer.DSPlugin;
import eu.kurai.uhc.demonslayer.role.AbstractDSRole;
import eu.kurai.uhc.demonslayer.role.defaults.slayer.ZenitsuRole;
import eu.unchat.uhc.power.defaults.AbstractCommandPower;
import eu.unchat.uhc.profile.IProfile;
import eu.unchat.uhc.util.CC;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;

import java.util.UUID;

@Getter
public final class HearPower extends AbstractCommandPower implements Listener {

    private final String name, commandName, identifier;

    private UUID target;

    public HearPower() {
        this.name = "&a&lHEAR";
        this.commandName = "hear";
        this.identifier = "hear";
    }

    @Override
    public Result onCommand(Player player, String[] args) {
        if (args.length < 1) {
            player.sendMessage(CC.error("/ds hear <pseudo>"));
            return Result.FAILED;
        }

        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            player.sendMessage(CC.error("Ce joueur n'existe pas."));
            return Result.FAILED;
        }

        IProfile profile = IProfile.of(target.getUniqueId());
        if (!profile.getState().equals(IProfile.State.ALIVE)) {
            player.sendMessage(CC.error("Ce joueur n'est pas en vie."));
            return Result.FAILED;
        }

        player.sendMessage(CC.info("Vous écoutez " + profile.getName() + "."));
        this.target = target.getUniqueId();
        Bukkit.getScheduler().runTaskLaterAsynchronously(DSPlugin.get(), () -> {
            this.target = null;
        }, (5 * 60) * 20L);
        return Result.SUCCESSFUL;
    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player damager) || this.target == null || !damager.getUniqueId().equals(this.target)) {
            return;
        }

        IProfile profile = AbstractDSRole.findPlayer(ZenitsuRole.class);
        if (profile == null) {
            this.target = null;
            return;
        }

        profile.sendMessage(CC.info("Le joueur " + damager.getName() + " a attaqué un joueur."));
    }

    @EventHandler
    public void onConsume(PlayerItemConsumeEvent event) {
        Player player = event.getPlayer();
        if (this.target == null || !player.getUniqueId().equals(this.target)) {
            return;
        }

        IProfile profile = AbstractDSRole.findPlayer(ZenitsuRole.class);
        if (profile == null) {
            this.target = null;
            return;
        }

        profile.sendMessage(CC.info("Le joueur " + player.getName() + " a consommé un objet."));
    }

}
