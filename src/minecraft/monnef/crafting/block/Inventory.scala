/*
 * Automatic Assembly Table
 * author: monnef
 */

package monnef.crafting.block

import net.minecraft.inventory.{InventoryBasic, IInventory}
import net.minecraft.item.ItemStack
import net.minecraft.entity.player.EntityPlayer

trait Inventory extends IInventory {
  private val invObj: IInventory = new InventoryBasic(getInvName, isInvNameLocalized, getSizeInventory)

  def getSizeInventory: Int

  def getStackInSlot(slot: Int): ItemStack = invObj.getStackInSlot(slot)

  def decrStackSize(slot: Int, amount: Int): ItemStack = invObj.decrStackSize(slot, amount)

  def getStackInSlotOnClosing(slot: Int): ItemStack = invObj.getStackInSlotOnClosing(slot)

  def setInventorySlotContents(slot: Int, stack: ItemStack) { invObj.setInventorySlotContents(slot, stack) }

  def getInvName: String

  def isInvNameLocalized: Boolean

  def getInventoryStackLimit: Int = invObj.getInventoryStackLimit

  def onInventoryChanged() { invObj.onInventoryChanged() }

  def isUseableByPlayer(player: EntityPlayer): Boolean = invObj.isUseableByPlayer(player)

  def openChest() { invObj.openChest() }

  def closeChest() { invObj.closeChest() }

  def isItemValidForSlot(slot: Int, stack: ItemStack): Boolean = invObj.isItemValidForSlot(slot, stack)
}
