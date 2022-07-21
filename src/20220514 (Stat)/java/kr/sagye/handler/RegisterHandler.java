package kr.sagye.handler;


import kr.sagye.sound.GuiSound;
import kr.sagye.util.Reference;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;


@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
public class RegisterHandler {

    @SubscribeEvent
    public static void registerSoundEvents(RegistryEvent.Register<SoundEvent> event) {
        final SoundEvent[] soundEvents = {
                GuiSound.PAGE,
                GuiSound.SELECT
        };
        event.getRegistry().registerAll(soundEvents);
    }

}
