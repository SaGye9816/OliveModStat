package kr.sagye.handler;

import kr.sagye.util.Reference;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
public class TickHandler {

    public static final Map<Runnable, Integer> taskTick = new HashMap<Runnable, Integer>();

    @SubscribeEvent
    public static void onServerTick(TickEvent.ServerTickEvent event) {
        if (event.phase != TickEvent.Phase.START) {
            return;
        }

        for (Runnable run : new HashSet<>(taskTick.keySet())) {
            if (taskTick.get(run) == 0) {
                run.run();
                taskTick.remove(run);
            } else {
                taskTick.put(run, taskTick.get(run) - 1);
            }
        }
    }

}
