/*
 * Automatic Assembly Table
 * author: monnef
 */

package monnef.crafting.block

import net.minecraft.block.material.Material
import monnef.crafting.common.{IconDescriptorCrafting, Reference}
import monnef.crafting.AutomaticAssemblyTable
import monnef.core.block.BlockMonnefCore

class BlockCrafting(_id: Int, _mat: Material, _idx: Int) extends BlockMonnefCore(_id, _idx, _mat) with IconDescriptorCrafting {
  def this(_id: Int, _mat: Material) = this(_id, _mat, 0)

  setCreativeTab(AutomaticAssemblyTable.creativeTab)
}
