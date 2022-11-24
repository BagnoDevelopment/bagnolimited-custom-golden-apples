package pl.bagnolimited.goldenappleeffects.config;

import eu.okaeri.configs.ConfigManager;
import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.yaml.bukkit.YamlBukkitConfigurer;
import eu.okaeri.configs.yaml.bukkit.serdes.SerdesBukkit;
import lombok.NonNull;
import pl.bagnolimited.goldenappleeffects.config.serdes.PlayerEffectWrapperSerdes;

import java.io.File;

public final class ConfigurationFactory {

    public <T extends OkaeriConfig> T create(@NonNull Class<T> representative, @NonNull File file) {
        return ConfigManager.create(representative, (config) -> {
            config.withConfigurer(new YamlBukkitConfigurer(), new SerdesBukkit(), new PlayerEffectWrapperSerdes());
            config.withBindFile(file);
            config.saveDefaults();
            config.load();
        });
    }

}