package kr.sagye.proxy;

import kr.sagye.ui.gui.*;
import kr.sagye.ui.gui.Character;
import net.minecraft.client.Minecraft;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.FMLCommonHandler;
import org.lwjgl.input.Keyboard;

public class ClientProxy extends CommonProxy {

    public static KeyBinding openStateKey;

    public ClientProxy() {
        FMLCommonHandler.instance().bus().register(this);
    }

    @Override
    public void registerKeybind() {
        this.openStateKey = new KeyBinding("Open Player Status",  KeyConflictContext.UNIVERSAL, Keyboard.KEY_P, "This War of MINE");
        ClientRegistry.registerKeyBinding(this.openStateKey);

    }

    @Override
    public void init() {
    }


}