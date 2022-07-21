package kr.sagye.sound;

import kr.sagye.util.Reference;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;

public class GuiSound {
    public static SoundEvent PAGE = new SoundEvent(new ResourceLocation(Reference.MOD_ID, "guisound.page")).setRegistryName("page");
    public static SoundEvent SELECT = new SoundEvent(new ResourceLocation(Reference.MOD_ID, "guisound.select")).setRegistryName("select");
}
