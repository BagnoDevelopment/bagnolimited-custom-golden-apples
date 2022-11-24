package pl.bagnolimited.goldenappleeffects.wrapper;

import lombok.Data;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public final @Data(staticConstructor = "wrap") class PlayerEffectWrapper {

    private final PotionEffectType potionEffectType;
    private final int duration;
    private final int amplifier;

    public PotionEffect unwrap() {
        return new PotionEffect(this.potionEffectType, (this.duration * 20), this.amplifier);
    }

}