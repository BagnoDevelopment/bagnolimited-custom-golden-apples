package pl.bagnolimited.goldenappleeffects.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import pl.bagnolimited.goldenappleeffects.config.PluginConfiguration;
import pl.bagnolimited.goldenappleeffects.wrapper.PlayerEffectWrapper;

import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

@RequiredArgsConstructor
public final class PlayerEffectService {

    private final PluginConfiguration pluginConfiguration;

    public void applyEffectsFor(@NonNull Player player, @NonNull List<PlayerEffectWrapper> effects) {
        if (this.isApplicableFor(player)) effects.forEach(playerEffectWrapper -> player.addPotionEffect(playerEffectWrapper.unwrap(), true));
    }

    private boolean isApplicableFor(@NonNull Player player) {
        final Supplier<Stream<PotionEffect>> playerPotionEffectsStreamSupplier = () -> Stream.of(player.getActivePotionEffects())
                .flatMap(Collection::stream);
        return playerPotionEffectsStreamSupplier.get().anyMatch(potionEffect -> this.pluginConfiguration.getCombinedEffectList().get()
                .filter(playerEffectWrapper -> playerEffectWrapper.getPotionEffectType() == potionEffect.getType())
                .filter(playerEffectWrapper -> playerEffectWrapper.getDuration() == potionEffect.getDuration())
                .anyMatch(playerEffectWrapper -> playerEffectWrapper.getAmplifier() == potionEffect.getAmplifier()));
    }

}