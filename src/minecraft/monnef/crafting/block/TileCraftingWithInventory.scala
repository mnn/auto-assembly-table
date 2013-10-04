/*
 * Automatic Assembly Table
 * author: monnef
 */

package monnef.crafting.block

abstract class TileCraftingWithInventory extends TileCrafting with Inventory {
  override def onInventoryChanged() { super.onInventoryChanged() }
}
