/*
 * Automatic Assembly Table
 * author: monnef
 */

package monnef.crafting.block

import monnef.core.common.ContainerRegistry.ContainerTag
import monnef.crafting.common.AutoAssemblyTableHelper._

@ContainerTag(slotsCount = totalSlots, outputSlotsCount = outputSlotsCount, containerClassName = "monnef.crafting.common.ContainerAutoAssemblyTable", guiClassName = "monnef.crafting.client.GuiAutoAssemblyTable")
class TileAutoAssemblyTable extends TileCraftingWithInventory {
  def getInvName: String = "Automatic Assembly Table"

  def isInvNameLocalized: Boolean = false

  // cost=8; slotsUsed=5;
  // slotsUsed*cost+20*cost = 200
}
