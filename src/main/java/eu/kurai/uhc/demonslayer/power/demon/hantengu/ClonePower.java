package eu.kurai.uhc.demonslayer.power.demon.hantengu;

import com.google.common.collect.Maps;
import eu.kurai.uhc.demonslayer.role.AbstractDSRole;
import eu.kurai.uhc.demonslayer.role.defaults.demon.HantenguRole;
import eu.unchat.uhc.menu.Button;
import eu.unchat.uhc.menu.Menu;
import eu.unchat.uhc.menu.button.CloseButton;
import eu.unchat.uhc.power.defaults.AbstractItemPower;
import eu.unchat.uhc.profile.IProfile;
import eu.unchat.uhc.util.CC;
import eu.unchat.uhc.util.ItemBuilder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

@Getter
public final class ClonePower extends AbstractItemPower {

    private final String name, identifier;

    private final InteractionType interactionType = InteractionType.RIGHT_CLICK;

    public ClonePower() {
        this.name = "&c&lCLONE";
        this.identifier = "clone";

        setInitialCooldown(0);
        setInitialUses(-1);
    }

    @Override
    public Result onInteract(Player player, InteractionType interactionType) {
        new CloneMenu().openMenu(player);
        return Result.SUCCESSFUL;
    }

    @Override
    public ItemStack getIcon() {
        return new ItemBuilder(Material.INK_SACK, 1, 12)
                .name(formatName())
                .glowing(true)
                .asItemStack();
    }

    private static final class CloneMenu implements Menu {
        @Override
        public String getTitle(Player player) {
            return "Clone - Hantengu";
        }

        @Override
        public Map<Integer, Button> getButtons(Player player) {
            Map<Integer, Button> buttons = Maps.newHashMap();

            buttons.put(11, new CloneButton(HantenguRole.Clone.SEKIDO));
            buttons.put(15, new CloneButton(HantenguRole.Clone.KARAKU));
            buttons.put(22, new CloneButton(HantenguRole.Clone.ZOHAKUTEN));
            buttons.put(29, new CloneButton(HantenguRole.Clone.AIZETSU));
            buttons.put(33, new CloneButton(HantenguRole.Clone.UROGI));

            buttons.put(40, new CloseButton());

            generateBorder(buttons);
            return buttons;
        }

        @RequiredArgsConstructor
        private static final class CloneButton implements Button {
            private final HantenguRole.Clone clone;

            @Override
            public void onClick(Player player, Menu menu, ClickType clickType) {
                IProfile profile = IProfile.of(player.getUniqueId());
                if (!AbstractDSRole.isRole(profile, HantenguRole.class)) {
                    return;
                }

                HantenguRole role = (HantenguRole) profile.getRole();
                profile.getActionBar().removeActionBar("clone_" + role.getClone().name().toLowerCase());
                role.setClone(clone);
                player.sendMessage(CC.info("Vous avez choisi le clone " + prettyName(clone) + "."));
            }

            @Override
            public ItemStack getIcon(Player player) {
                return new ItemBuilder(Material.INK_SACK, 1, 1)
                        .name("&c&l" + prettyName(clone))
                        .asItemStack();
            }

            private String prettyName(HantenguRole.Clone clone) {
                return clone.name().charAt(0) + clone.name().substring(1).toLowerCase();
            }
        }
    }
}
