package pl.bagnolimited.goldenappleeffects.listener;

import eu.okaeri.injector.annotation.Inject;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import pl.bagnolimited.goldenappleeffects.config.PluginConfiguration;
import pl.bagnolimited.goldenappleeffects.service.PlayerEffectService;

public final class PlayerItemConsumeListener extends AbstractListener {

    @Inject
    public PlayerItemConsumeListener(PluginConfiguration pluginConfiguration, PlayerEffectService playerEffectService) {
        super(pluginConfiguration, playerEffectService);
    }

    @EventHandler
    private void handle(final PlayerItemConsumeEvent event) {
        final Player player = event.getPlayer();
        final ItemStack itemStack = event.getItem();

        if (itemStack.getType() != Material.GOLDEN_APPLE) return;

        if (itemStack.getDurability() == 0) {
            this.playerEffectService.applyEffectsFor(player, this.pluginConfiguration.getGoldenAppleEffectList());
            return;
        }
        if (itemStack.getDurability() == 1) {
            this.playerEffectService.applyEffectsFor(player, this.pluginConfiguration.getEnchantedGoldenAppleEffectList());
        }
    }

}