package pl.bagnolimited.goldenappleeffects;

import eu.okaeri.injector.Injector;
import eu.okaeri.injector.OkaeriInjector;
import org.bukkit.plugin.java.JavaPlugin;
import pl.bagnolimited.goldenappleeffects.config.ConfigurationFactory;
import pl.bagnolimited.goldenappleeffects.config.PluginConfiguration;
import pl.bagnolimited.goldenappleeffects.listener.PlayerItemConsumeListener;
import pl.bagnolimited.goldenappleeffects.service.PlayerEffectService;

import java.io.File;

public final class GoldenAppleEffectsPlugin extends JavaPlugin {

    private final File pluginConfigurationFile = new File(this.getDataFolder(), "config.yml");

    @Override
    public void onEnable() {
        final Injector injector = OkaeriInjector.create();
        final ConfigurationFactory configurationFactory = new ConfigurationFactory();

        final PluginConfiguration pluginConfiguration = configurationFactory
                .create(PluginConfiguration.class, this.pluginConfigurationFile);
        injector.registerInjectable(pluginConfiguration);

        injector.registerInjectable(new PlayerEffectService(pluginConfiguration));

        this.getServer().getPluginManager().registerEvents(injector.createInstance(PlayerItemConsumeListener.class), this);
    }

}