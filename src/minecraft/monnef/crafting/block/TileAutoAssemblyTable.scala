/*
 * Automatic Assembly Table
 * author: monnef
 */

package monnef.crafting.block

import monnef.crafting.common.ContainerAutoAssemblyTable

class TileAutoAssemblyTable extends TileCraftingWithInventory {
  def getInvName: String = "Automatic Assembly Table"

  def isInvNameLocalized: Boolean = false

  def getSizeInventory: Int = ContainerAutoAssemblyTable.slotsCount
}
