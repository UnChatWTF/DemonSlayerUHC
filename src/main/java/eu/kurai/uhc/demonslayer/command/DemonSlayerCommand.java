package eu.kurai.uhc.demonslayer.command;

import dev.rollczi.litecommands.annotations.command.Command;
import dev.rollczi.litecommands.annotations.context.Context;
import dev.rollczi.litecommands.annotations.execute.Execute;
import eu.kurai.uhc.demonslayer.role.AbstractDSRole;
import eu.unchat.uhc.API;
import eu.unchat.uhc.profile.IProfile;
import eu.unchat.uhc.role.AbstractRole;
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

        for (Class<? extends AbstractRole> clazz : role.getKnownRoles()) {
            if (clazz == null) {
                continue;
            }

            AbstractDSRole abstractRole = (eu.kurai.uhc.demonslayer.role.AbstractDSRole) API.get().getRoleHandler().getRoles().get(clazz).getRole();
            IProfile knownProfile = API.get().getRoleHandler().getProfile(abstractRole.getClass());
            player.sendMessage(CC.translate(" &8- &7" + (knownProfile == null ? "&cIl n'y à pas de " + abstractRole.getName() + " dans la partie." : knownProfile.getName() + " est " + abstractRole.getName())));
        }
    }

}
