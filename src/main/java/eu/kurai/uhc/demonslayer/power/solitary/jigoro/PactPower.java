package eu.kurai.uhc.demonslayer.power.solitary.jigoro;

import com.google.common.collect.Maps;
import eu.kurai.uhc.demonslayer.role.AbstractDSRole;
import eu.kurai.uhc.demonslayer.role.defaults.demon.KaigakuRole;
import eu.kurai.uhc.demonslayer.role.defaults.slayer.ZenitsuRole;
import eu.kurai.uhc.demonslayer.role.defaults.solitary.JigoroRole;
import eu.kurai.uhc.demonslayer.team.defaults.jigoro.JigoroDuoTeam;
import eu.kurai.uhc.demonslayer.team.defaults.jigoro.JigoroSolitaryTeam;
import eu.kurai.uhc.demonslayer.team.defaults.jigoro.JigoroTrioTeam;
import eu.unchat.uhc.API;
import eu.unchat.uhc.menu.Button;
import eu.unchat.uhc.menu.Menu;
import eu.unchat.uhc.menu.button.CloseButton;
import eu.unchat.uhc.power.defaults.AbstractItemPower;
import eu.unchat.uhc.profile.IProfile;
import eu.unchat.uhc.team.AbstractTeam;
import eu.unchat.uhc.util.CC;
import eu.unchat.uhc.util.ItemBuilder;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.Map;
import java.util.function.Predicate;

@Getter
public final class PactPower extends AbstractItemPower {

    private final String name, identifier;

    private final InteractionType interactionType = InteractionType.RIGHT_CLICK;

    public PactPower() {
        this.name = "&6&lPacte";
        this.identifier = "pact";

        this.setInitialUses(1);
    }

    @Override
    public Result onInteract(Player player, InteractionType interactionType) {
        new PacteMenu().openMenu(player);
        return Result.IGNORED;
    }

    @Override
    public ItemStack getIcon() {
        return new ItemBuilder(Material.CHEST)
                .name(formatName())
                .asItemStack();
    }

    private static final class PacteMenu implements Menu {
        @Override
        public String getTitle(Player player) {
            return "Pacte - Jigoro";
        }

        @Override
        public Map<Integer, Button> getButtons(Player player) {
            final Map<Integer, Button> buttons = Maps.newHashMap();
            buttons.put(10, new PacteButton(new ItemBuilder(Material.CHEST)
                    .name("&6&lPacte I &8- &6&lSolitaire")
                    .lore(
                            "&7Vous devez gagner &6&lseul&7.",
                            "&7Vous obtenez l'effet &c&lForce I &7de manière permanente.",
                            "",
                            "&7Si vous parvenez à éliminer &a&lZen'Itsu &7ou &c&lKaigaku&7,",
                            "&7vous obtiendrez &c1❤ &7supplémentaire.",
                            "",
                            "&7Chaque élimination réduit le délai de votre capacité",
                            "&8\"&6&lDIVINE SPEED&8\" &7de &b30 secondes&7."
                    )
                    .asItemStack(),
                    () -> {
                        IProfile profile = AbstractDSRole.findPlayer(JigoroRole.class);
                        profile.setStrengthBuffer(profile.getStrengthBuffer() + 15);
                    },
                    (predicate) -> true,
                    JigoroSolitaryTeam.class,
                    JigoroRole.class));

            buttons.put(12, new PacteButton(new ItemBuilder(Material.CHEST)
                    .name("&6&lPacte II &8- &a&lDuo &8(&a&lZen'Itsu&8)")
                    .lore(
                            "&7Vous devez gagner avec &a&lZen'Itsu&7.",
                            "&7Vous et votre duo obtenez l'effet &c&lForce I &7lorsque",
                            "&7vous êtes à moins de &b20 blocs &7l'un de l'autre.",
                            "",
                            "&7Si vous ou &a&lZen'Itsu &7parvenez à éliminer &c&lKaigaku&7,",
                            "&7vous obtiendrez tous les deux l'effet &c&lForce I &7de manière permanente.",
                            "",
                            "&7Le pouvoir &8\"&a&lDIVINE SPEED&8\" &7de &a&lZen'Itsu &7ne",
                            "&7possèdera plus aucune restriction d'utilisations."
                    )
                    .asItemStack(),
                    () -> {
                    },
                    (predicate) -> AbstractDSRole.findPlayer(ZenitsuRole.class) != null,
                    JigoroDuoTeam.class,
                    JigoroRole.class, ZenitsuRole.class));

            buttons.put(14, new PacteButton(new ItemBuilder(Material.CHEST)
                    .name("&6&lPacte II &8- &c&lDuo &8(&c&lKaigaku&8)")
                    .lore(
                            "&7Vous devez gagner avec &c&lKaigaku&7.",
                            "&7Vous et votre duo obtenez l'effet &c&lForce I &7lorsque",
                            "&7vous êtes à moins de &b20 blocs &7l'un de l'autre.",
                            "",
                            "&7Si vous ou &c&lKaigaku &7parvenez à éliminer un joueur,",
                            "&7vous obtiendrez tous les deux &c0.5❤ &7supplémentaire."
                    )
                    .asItemStack(),
                    () -> {
                    },
                    (predicate) -> AbstractDSRole.findPlayer(KaigakuRole.class) != null,
                    JigoroDuoTeam.class,
                    JigoroRole.class, KaigakuRole.class));

            buttons.put(16, new PacteButton(new ItemBuilder(Material.CHEST)
                    .name("&6&lPacte III &8- &6&lTrio &8(&a&lZen'Itsu &7et &c&lKaigaku&8)")
                    .lore(
                            "&7Vous devez gagner avec &a&lZen'Itsu &7ainsi que &c&lKaigaku&7.",
                            "&7Lorsque l'ensemble du trio se trouve à moins de &b20 blocs &7l'un de l'autre,",
                            "&7ils obtiennent l'effet &c&lForce I &7ainsi que &810% &7de &8Résistance&7.",
                            "&7Sauf vous, qui n'obtenez pas les pourcentages de &8Résistance&7.",
                            "",
                            "&7Lorsqu'un membre du trio élimine un joueur, l'ensemble du trio",
                            "&7obtient un bonus parmis les suivants:",
                            " &8- &b+5% &7de &bVitesse",
                            " &8- &c+5% &7de &cForce",
                            " &8- &8+5% &7de &8Résistance",
                            " &8- &d+0.5❤"
                    )
                    .asItemStack(),
                    () -> {
                    },
                    (predicate -> AbstractDSRole.findPlayer(KaigakuRole.class) != null
                            && AbstractDSRole.findPlayer(ZenitsuRole.class) != null),
                    JigoroTrioTeam.class,
                    JigoroRole.class, ZenitsuRole.class, KaigakuRole.class));

            buttons.put(22, new CloseButton());
            generateBorder(buttons);
            return buttons;
        }

        private static final class PacteButton implements Button {

            private final Class<? extends AbstractDSRole>[] roles;
            private final Runnable runnable;
            private final Predicate<Player> available;
            private final Class<? extends AbstractTeam> team;
            private final ItemStack itemStack;

            public PacteButton(final ItemStack itemStack, final Runnable runnable, final Predicate<Player> available, final Class<? extends AbstractTeam> team, final Class<? extends AbstractDSRole>... roles) {
                this.roles = roles;
                this.runnable = runnable;
                this.available = available;
                this.team = team;
                this.itemStack = itemStack;
            }

            @Override
            public void onClick(Player player, Menu menu, ClickType clickType) {
                if (!available.test(player)) {
                    player.sendMessage(CC.error("Vous ne pouvez pas choisir ce pacte."));
                    return;
                }

                for (Class<? extends AbstractDSRole> role : roles) {
                    IProfile profile = AbstractDSRole.findPlayer(role);
                    if (profile == null) {
                        player.sendMessage(CC.error("Un des membres du pacte n'est pas présent dans la composition de la partie."));
                        return;
                    }

                    profile.setTeam(API.get().getTeamHandler().getTeam(team).getTeam());
                }

                runnable.run();

                player.sendMessage(CC.info("Vous avez choisi le " + itemStack.getItemMeta().getDisplayName() + "."));
                ((AbstractItemPower) IProfile.of(player.getUniqueId()).getPower(PactPower.class)).setUses(1);
                player.closeInventory();
            }

            @Override
            public ItemStack getIcon(Player player) {
                return available.test(player) ? itemStack : new ItemBuilder(Material.IRON_FENCE)
                        .name("&c&lIndisponible")
                        .lore(
                                "&7Vous ne pouvez pas choisir ce pacte."
                        )
                        .asItemStack();
            }
        }
    }
}
