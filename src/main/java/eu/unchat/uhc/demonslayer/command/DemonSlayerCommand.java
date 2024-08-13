package eu.unchat.uhc.demonslayer.command;

import dev.rollczi.litecommands.annotations.argument.Arg;
import dev.rollczi.litecommands.annotations.command.Command;
import dev.rollczi.litecommands.annotations.context.Context;
import dev.rollczi.litecommands.annotations.execute.Execute;
import eu.unchat.uhc.API;
import eu.unchat.uhc.cycle.Cycle;
import eu.unchat.uhc.demonslayer.command.validator.role.HasRole;
import eu.unchat.uhc.demonslayer.command.validator.team.HasTeam;
import eu.unchat.uhc.demonslayer.power.slayer.tanjiro.SmellPower;
import eu.unchat.uhc.demonslayer.role.defaults.demon.MuzanRole;
import eu.unchat.uhc.demonslayer.role.defaults.slayer.TanjiroRole;
import eu.unchat.uhc.demonslayer.team.defaults.DemonTeam;
import eu.unchat.uhc.power.AbstractPower;
import eu.unchat.uhc.profile.IProfile;
import eu.unchat.uhc.demonslayer.role.AbstractDSRole;
import eu.unchat.uhc.role.RoleData;
import eu.unchat.uhc.util.CC;
import lombok.SneakyThrows;
import net.minecraft.server.v1_8_R3.ChatComponentUtils;
import net.minecraft.server.v1_8_R3.EntityPlayer;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

@Command(name = "demonslayer", aliases = {"ds"})
public final class DemonSlayerCommand {

    @Execute(name = "effect")
    void executeEffect(final @Context Player player) {
        player.performCommand("effect");
    }

    @SneakyThrows
    @Execute(name = "role", aliases = "me")
    void executeRole(final @Context Player player) {
        final IProfile profile = IProfile.of(player.getUniqueId());
        AbstractDSRole role = (AbstractDSRole) profile.getRole();
        if (role == null) {
            player.sendMessage(CC.error("Vous n'avez pas de rôle"));
            return;
        }

        player.sendMessage(CC.center("&3&lDEMON SLAYER UHC"));

        IChatBaseComponent component = IChatBaseComponent.ChatSerializer.a(role.getDescription(player).replace("<role_name>", role.getName()));
        EntityPlayer entityPlayer = ((CraftPlayer) player).getHandle();
        entityPlayer.sendMessage(ChatComponentUtils.filterForDisplay(entityPlayer, component, entityPlayer));

        for (RoleData data : role.getKnownRoles()) {
            AbstractDSRole abstractRole = (eu.unchat.uhc.demonslayer.role.AbstractDSRole) API.get().getRoleHandler().getRole(data.getIdentifier());
            IProfile knownProfile = API.get().getRoleHandler().getProfile(abstractRole.getClass());
            player.sendMessage(CC.translate(" &8- &7" + (knownProfile == null ? "&cIl n'y à pas de " + abstractRole.getName() + " dans la partie." : knownProfile.getName() + " est " + abstractRole.getName())));
        }
    }

    @Execute(name = "flair")
    void executeFlair(final @Context @HasRole(TanjiroRole.class) Player player) {
        IProfile profile = IProfile.of(player.getUniqueId());
        AbstractPower power = profile.getPower(SmellPower.class);

        if (API.get().getCycleHandler().getCurrentCycle() != Cycle.NIGHT) {
            player.sendMessage(CC.translate("&cCe pouvoir n'est utilisable que la nuit."));
            return;
        }

        player.sendMessage(CC.translate("&cTest !"));
    }

    @Execute(name = "blood")
    void executeBloodGift(
            final @Context @HasRole(MuzanRole.class) Player player,
            @Arg("target") @HasTeam(DemonTeam.class) IProfile target) {

        IProfile profile = IProfile.of(player.getUniqueId());
        AbstractPower power = profile.getPower(MuzanRole.BloodGiftPower.class);

    }

}
