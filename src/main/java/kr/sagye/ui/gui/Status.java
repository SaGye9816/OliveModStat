package kr.sagye.ui.gui;

import kr.sagye.sound.GuiSound;
import kr.sagye.ui.RenderingHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Mouse;
import scala.Array;
import scala.Char;

import java.util.ArrayList;


public class Status extends GuiScreen {

    private boolean leftpreDown = false;
    private boolean rightpreDown = false;
    private Character renderStory = null;

    private ResourceLocation bg = new ResourceLocation("omodstat", "textures/gui/background.png");
    private static final ResourceLocation black = new ResourceLocation("omodstat", "textures/gui/black.png");
    private final Character[] characters = new Character[]{
            new Character1(),
            new Character2(),
            new Character3(),
            new Character4(),
            new Character5(),
            new Character6(),
            new Character7()
    };
    private final ArrayList<Character> renderChoices = new ArrayList<>(3);

    @Override
    public void initGui() {
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        ScaledResolution scaledResolution = new ScaledResolution(mc);

        Character renderHover = null;

        boolean leftdown = Mouse.isButtonDown(0);
        boolean rightdown = Mouse.isButtonDown(1);
        boolean leftClicked = false;
        boolean rightClicked = false;

        if (leftdown != leftpreDown) {
            if (!leftpreDown) {
                leftClicked = true;
            }
        }

        leftpreDown = leftdown;

        if (rightdown != rightpreDown) {
            if (!rightpreDown) {
                rightClicked = true;
            }
        }

        rightpreDown = rightdown;

        int factor = scaledResolution.getScaleFactor();

        float bgx = 0;
        float bgy = 0;

        int mx = mouseX / 2;
        int my = mouseY / 2;

        this.bg = new ResourceLocation("omodstat", "textures/gui/background.png");
        RenderingHelper.drawTexture(bg, bgx, bgy, 1280 / factor, 720 / factor, 1.0F);
        for (Character character : characters) {
            RenderingHelper.drawTexture(character.photoLocation, bgx, bgy, 1280 / factor, 720 / factor, 1.0F);
        }

        boolean showStory = false;

        for (Character character : characters) {
            if (character.minX <= mx && mx <= character.maxX) {
                if (character.minY <= my && my <= character.maxY) {

                    renderHover = character;

                    if (leftClicked) {
                        if (!renderChoices.contains(character)) {
                            if (renderChoices.size() < 3) {
                                renderChoices.add(character);
                                Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.getMasterRecord(GuiSound.SELECT, 1.0F));
                            }
                        } else {
                            renderChoices.remove(character);
                        }
                    }

                    if (rightClicked) {
                        if (renderStory == null) {
                            renderStory = character;
                            showStory = true;
                            if (showStory) {
                                Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.getMasterRecord(GuiSound.PAGE, 1.0F));
                            }
                        }
                    }
                }
            }
        }

        if (showStory == false && rightClicked) {
            renderStory = null;
        }

        for (Character character : renderChoices) {
            RenderingHelper.drawTexture(character.choiceLocation, bgx, bgy, 1280/factor, 720/factor, 1.0F);
        }

        if (renderHover != null) {
            RenderingHelper.drawTexture(renderHover.statLocation, bgx, bgy, 1280/factor, 720/factor, 1.0F);
        }

        if (renderStory != null) {
            RenderingHelper.drawTexture(black, bgx, bgy, 1280/factor, 720/factor, 0.5F);
            RenderingHelper.drawTexture(renderStory.storyLocation, bgx, bgy, 1280/factor, 720/factor, 1.0F);
        }
    }
}





