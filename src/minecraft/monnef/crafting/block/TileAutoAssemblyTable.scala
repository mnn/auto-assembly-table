/*
 * Automatic Assembly Table
 * author: monnef
 */

package monnef.crafting.block

import monnef.crafting.common.ContainerAutoAssemblyTable
import monnef.core.common.ContainerRegistry.ContainerTag

@ContainerTag(slotsCount = 10 + 9 * 6, outputSlotsCount = 10)
class TileAutoAssemblyTable extends TileCraftingWithInventory {
  def getInvName: String = "Automatic Assembly Table"

  def isInvNameLocalized: Boolean = false
}
