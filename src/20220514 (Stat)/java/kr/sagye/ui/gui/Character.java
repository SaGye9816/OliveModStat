package kr.sagye.ui.gui;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;

public abstract class Character extends GuiScreen {

    float maxX, maxY, minX, minY;

    final ResourceLocation photoLocation;
    final ResourceLocation statLocation;
    final ResourceLocation choiceLocation;
    final ResourceLocation storyLocation;

    public Character(float maxX, float maxY, float minX, float minY, ResourceLocation photoLocation, ResourceLocation statLocation, ResourceLocation choiceLocation, ResourceLocation storyLocation) {
        this.maxX = maxX;
        this.maxY = maxY;
        this.minX = minX;
        this.minY = minY;
        this.photoLocation = photoLocation;
        this.statLocation = statLocation;
        this.choiceLocation = choiceLocation;
        this.storyLocation = storyLocation;
    }


}
