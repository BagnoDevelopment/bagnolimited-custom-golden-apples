package pl.bagnolimited.goldenappleeffects.config.serdes;

import eu.okaeri.configs.schema.GenericsDeclaration;
import eu.okaeri.configs.serdes.DeserializationData;
import eu.okaeri.configs.serdes.ObjectSerializer;
import eu.okaeri.configs.serdes.SerializationData;
import lombok.NonNull;
import org.bukkit.potion.PotionEffectType;
import pl.bagnolimited.goldenappleeffects.wrapper.PlayerEffectWrapper;

public final class PlayerEffectWrapperSerializer implements ObjectSerializer<PlayerEffectWrapper> {

    @Override
    public boolean supports(@NonNull Class<? super PlayerEffectWrapper> type) {
        return PlayerEffectWrapper.class.isAssignableFrom(type);
    }

    @Override
    public void serialize(@NonNull PlayerEffectWrapper object, @NonNull SerializationData data, @NonNull GenericsDeclaration generics) {
        data.add("effect-type", object.getPotionEffectType().getName());
        data.add("effect-duration", object.getDuration());
        data.add("effect-amplifier", object.getAmplifier());
    }

    @Override
    public PlayerEffectWrapper deserialize(@NonNull DeserializationData data, @NonNull GenericsDeclaration generics) {
        final PotionEffectType potionEffectType = PotionEffectType.getByName(data.get("effect-type", String.class));
        return PlayerEffectWrapper.wrap(
                potionEffectType,
                data.get("effect-duration", Integer.class),
                data.get("effect-amplifier", Integer.class)
        );
    }
}