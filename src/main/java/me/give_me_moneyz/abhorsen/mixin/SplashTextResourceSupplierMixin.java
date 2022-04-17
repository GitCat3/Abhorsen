package me.give_me_moneyz.abhorsen.mixin;

import net.minecraft.client.util.Splashes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Mixin(Splashes.class)
public class SplashTextResourceSupplierMixin {
    private final Random random = new Random();

    private final List<String> newSplashes = getNewSplashes();

    @Inject(method = "getSplashText", at = @At("HEAD"), cancellable = true)
    private void onApply(CallbackInfoReturnable<String> cir) {
        if(random.nextInt( 2) == 1){
            cir.setReturnValue(newSplashes.get(random.nextInt(newSplashes.size())));
        }
    }

    private static List<String> getNewSplashes() {
        return Arrays.asList(
                "§5§kYour Mother",
                "Amazing! New splash screen!",
                "§5LMAO§r imagine editing the splashscreen with a mod..."
        );
    }

}
