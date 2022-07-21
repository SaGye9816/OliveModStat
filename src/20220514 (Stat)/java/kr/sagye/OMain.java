package kr.sagye;

import kr.sagye.proxy.CommonProxy;
import kr.sagye.util.Reference;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.Display;



@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION)
public class OMain {

    public static Logger logger;

    @Mod.Instance()
    public static OMain INSTANCE;

    @SidedProxy(clientSide = Reference.Client_Side, serverSide = Reference.Server_Side)
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preinit(FMLPreInitializationEvent event) {
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        //두번째 핸들러 (월드젠/IMC메세지/이벤트핸들러/레시피)
        //보통 Mincraft Forge에서 이벤트 버스 레지스터를 활용해서 등록시킴
        proxy.init();
        proxy.registerKeybind();
    }

    @Mod.EventHandler
    @SideOnly(Side.CLIENT)
    public void postinit(FMLPostInitializationEvent event) {
        //세번째 핸들러 (모드 호환성개선/ 기타 타 모드 관련)
        Display.setTitle("YDTV X Team. Olive");
        //Display.setIcon();
    }

    @Mod.EventHandler
    public void Serverinit(FMLServerStartingEvent event) {
    }
}
