package purplecreate.theatricalwb.util.forge;

import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.inventory.MenuConstructor;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.server.ServerLifecycleHooks;
import purplecreate.theatricalwb.util.Platform;

public class PlatformImpl {
  public static Platform get() {
    return Platform.FORGE;
  }

  public static MenuProvider menu(MenuConstructor constructor, Component title) {
    return new SimpleMenuProvider(constructor, title);
  }

  public static Platform.Side getSide() {
    return switch (FMLEnvironment.dist) {
      case CLIENT -> Platform.Side.CLIENT;
      case DEDICATED_SERVER -> Platform.Side.SERVER;
    };
  }

  public static MinecraftServer getServer() {
    return ServerLifecycleHooks.getCurrentServer();
  }
}
