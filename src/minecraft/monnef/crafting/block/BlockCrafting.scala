/*
 * Automatic Assembly Table
 * author: monnef
 */

package monnef.crafting.block

import net.minecraft.block.material.Material
import monnef.crafting.common.Reference
import monnef.crafting.AutomaticAssemblyTable
import monnef.core.block.BlockMonnefCore

class BlockCrafting(_id: Int, _mat: Material, _idx: Int) extends BlockMonnefCore(_id, _idx, _mat) {

  def this(_id: Int, _mat: Material) = this(_id, _mat, 0)

  def getModName: String = Reference.modName

  def getDefaultSheetNumber: Int = Reference.defaultSheetNumber

  setCreativeTab(AutomaticAssemblyTable.creativeTab)
}
