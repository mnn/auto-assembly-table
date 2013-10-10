/*
 * Automatic Assembly Table
 * author: monnef
 */

package monnef.crafting.block

import monnef.core.common.ContainerRegistry

abstract class TileCraftingWithInventory extends TileCrafting with Inventory {
  val containerDescriptor = ContainerRegistry.getContainerPrototype(this.getClass)

  override def onInventoryChanged() { super.onInventoryChanged() }

  def getSizeInventory: Int = containerDescriptor.getSlotsCount
}
