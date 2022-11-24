package pl.bagnolimited.goldenappleeffects.config;

import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.CustomKey;
import eu.okaeri.configs.annotation.Exclude;
import lombok.Getter;
import org.bukkit.potion.PotionEffectType;
import pl.bagnolimited.goldenappleeffects.wrapper.PlayerEffectWrapper;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

@Getter
public final class PluginConfiguration extends OkaeriConfig {

    @CustomKey("golden-apple-effects")
    private List<PlayerEffectWrapper> goldenAppleEffectList = Arrays
            .asList(PlayerEffectWrapper.wrap(PotionEffectType.ABSORPTION, 120, 0),
                    PlayerEffectWrapper.wrap(PotionEffectType.REGENERATION, 5, 1));

    @CustomKey("enchanted-golden-apple-effects")
    private List<PlayerEffectWrapper> enchantedGoldenAppleEffectList = Arrays
            .asList(PlayerEffectWrapper.wrap(PotionEffectType.ABSORPTION, 120, 3),
                    PlayerEffectWrapper.wrap(PotionEffectType.REGENERATION, 30, 4),
                    PlayerEffectWrapper.wrap(PotionEffectType.DAMAGE_RESISTANCE, 300, 0),
                    PlayerEffectWrapper.wrap(PotionEffectType.FIRE_RESISTANCE, 300, 0));

    @Exclude
    private final Supplier<Stream<PlayerEffectWrapper>> combinedEffectList = () ->
            Stream.of(this.goldenAppleEffectList, this.enchantedGoldenAppleEffectList)
                    .flatMap(Collection::stream);

}