/*
 * Automatic Assembly Table
 * author: monnef
 */

package monnef.crafting.common

import monnef.core.block.CustomIconDescriptor

trait IconDescriptorCrafting extends CustomIconDescriptor {
  def getDescriptorModName: String = Reference.modName

  def getDescriptorSheetNumber: Int = Reference.defaultSheetNumber
}
