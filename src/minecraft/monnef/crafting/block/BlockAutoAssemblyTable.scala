/*
 * Automatic Assembly Table
 * author: monnef
 */

package monnef.crafting.block

import net.minecraft.block.material.Material
import net.minecraft.world.World
import net.minecraft.entity.player.EntityPlayer
import monnef.crafting.AutomaticAssemblyTable
import monnef.crafting.common.GuiEnum
import net.minecraft.tileentity.TileEntity

class BlockAutoAssemblyTable(_id: Int) extends BlockCrafting(_id, Material.iron) {

  setResistance(5)
  setHardness(5)

  override def onBlockActivated(world: World, x: Int, y: Int, z: Int, player: EntityPlayer, side: Int, par7: Float, par8: Float, par9: Float): Boolean =
    if (!player.isSneaking) {
      player.openGui(AutomaticAssemblyTable, GuiEnum.AutoAssemblyTable.id, world, x, y, z)
      true
    } else {
      super.onBlockActivated(world, x, y, z, player, side, par7, par8, par9)
    }

  override def hasTileEntity(metadata: Int): Boolean = true

  override def createTileEntity(world: World, metadata: Int): TileEntity = new TileAutoAssemblyTable
}
