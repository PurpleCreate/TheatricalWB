package purplecreate.theatricalwb.fabric;

import com.tterrag.registrate.fabric.EnvExecutor;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import purplecreate.theatricalwb.Workbench;
import purplecreate.theatricalwb.util.fabric.PlatformImpl;

public class WorkbenchImpl implements ModInitializer {
  @Override
  public void onInitialize() {
    ServerLifecycleEvents.SERVER_STARTING.register(server -> {
      PlatformImpl.currentServer = server;
    });

    Workbench.init();
    Workbench.REGISTRATE.register();
    Workbench.commonSetup();

    EnvExecutor.runWhenOn(EnvType.CLIENT, () -> Workbench::clientSetup);
  }
}
