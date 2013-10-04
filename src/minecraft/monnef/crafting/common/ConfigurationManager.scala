/*
 * Automatic Assembly Table
 * author: monnef
 */

package monnef.crafting.common

import net.minecraftforge.common.Configuration.CATEGORY_GENERAL
import net.minecraftforge.common.Configuration

object ConfigurationManager {
  var vanillaRecipes: Boolean = _
  var autoTableRequiresPower: Boolean = _

  def loadSettings(config: Configuration) {
    vanillaRecipes = config.get(CATEGORY_GENERAL, "useFallbackVanillaRecipes", false).getBoolean(false)
    autoTableRequiresPower = config.get(CATEGORY_GENERAL, "aatRequiresPower", true).getBoolean(true)
  }
}
