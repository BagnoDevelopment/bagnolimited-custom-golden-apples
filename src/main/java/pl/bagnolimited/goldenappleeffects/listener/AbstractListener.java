package pl.bagnolimited.goldenappleeffects.listener;

import lombok.RequiredArgsConstructor;
import org.bukkit.event.Listener;
import pl.bagnolimited.goldenappleeffects.config.PluginConfiguration;
import pl.bagnolimited.goldenappleeffects.service.PlayerEffectService;

@RequiredArgsConstructor
public abstract class AbstractListener implements Listener {
    protected final PluginConfiguration pluginConfiguration;
    protected final PlayerEffectService playerEffectService;
}