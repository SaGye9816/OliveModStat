package kr.sagye.handler;

import kr.sagye.util.Reference;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
public class Etc {

    @SubscribeEvent
    public static void onPlayerjoin(PlayerEvent.PlayerLoggedInEvent event) {

        TickHandler.taskTick.put(() -> {
            TextComponentString Text = new TextComponentString("");
            Text.appendSibling(new TextComponentString("[Olive] ").setStyle(new Style().setColor(TextFormatting.BOLD.DARK_GREEN)));
            Text.appendSibling(new TextComponentString("모드 제작자 ").setStyle(new Style().setColor(TextFormatting.WHITE)));
            Text.appendSibling(new TextComponentString(": ").setStyle(new Style().setColor(TextFormatting.GRAY)));
            Text.appendSibling(new TextComponentString("사계").setStyle(new Style().setColor(TextFormatting.YELLOW)));

            event.player.sendMessage(Text);
        }, 20);

    }
}
