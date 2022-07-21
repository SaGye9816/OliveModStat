package kr.sagye.handler;

import kr.sagye.proxy.ClientProxy;
import kr.sagye.sound.GuiSound;
import kr.sagye.ui.gui.Status;
import kr.sagye.util.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import org.lwjgl.input.Keyboard;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID, value = Side.CLIENT)
public class ClientHandler {

    static boolean guiPreviousPress = false;

    @SubscribeEvent
    public static void clientTickEvent(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.START) {
            if (Keyboard.isKeyDown(ClientProxy.openStateKey.getKeyCode())) {
                if (!guiPreviousPress) {
                    if (Minecraft.getMinecraft().currentScreen instanceof Status) {
                        Minecraft.getMinecraft().player.closeScreen();
                    } else {

                        Status gui = new Status();
                        Minecraft.getMinecraft().displayGuiScreen(gui);
                        Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.getMasterRecord(GuiSound.PAGE, 1.0F));
                    }
                    guiPreviousPress = true;
                }
            } else {
                guiPreviousPress = false;
            }
        }
        return;
    }
}

