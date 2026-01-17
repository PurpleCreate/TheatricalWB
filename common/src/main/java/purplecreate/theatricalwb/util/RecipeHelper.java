package purplecreate.theatricalwb.util;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.Level;

import java.util.function.Supplier;

public class RecipeHelper {
  public static ItemStack getResult(Recipe<?> recipe) {
    Level level = getLevel();
    if (level == null)
      return ItemStack.EMPTY;
    return recipe.getResultItem(level.registryAccess());
  }

  public static ItemStack unsafeGetResult(Recipe<?> recipe) {
    return recipe.getResultItem(null);
  }

  private static Level getLevel() {
    Supplier<Level> supplier = switch (Platform.getSide()) {
      case CLIENT -> RecipeHelper::getClientLevel;
      case SERVER -> RecipeHelper::getServerLevel;
    };

    return supplier.get();
  }

  @Environment(EnvType.CLIENT)
  private static Level getClientLevel() {
    return Minecraft.getInstance().level;
  }

  @Environment(EnvType.SERVER)
  private static Level getServerLevel() {
    return Platform.getServer().overworld();
  }
}
