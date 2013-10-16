/*
 * Automatic Assembly Table
 * author: monnef
 */

package monnef.crafting.block

import net.minecraft.world.{IBlockAccess, World}
import net.minecraft.entity.player.EntityPlayer
import monnef.crafting.AutomaticAssemblyTable
import monnef.crafting.common.GuiEnum
import net.minecraft.tileentity.TileEntity
import monnef.core.utils.BreakableIronMaterial
import BreakableIronMaterial.breakableIronMaterial
import net.minecraft.util.Icon
import monnef.crafting.common.AutoAssemblyTableHelper._

class BlockAutoAssemblyTable(_id: Int) extends BlockCrafting(_id, breakableIronMaterial, 1) {

  setResistance(5)
  setHardness(5)
  setIconsCount(16)

  override def onBlockActivated(world: World, x: Int, y: Int, z: Int, player: EntityPlayer, side: Int, par7: Float, par8: Float, par9: Float): Boolean =
    if (!player.isSneaking) {
      player.openGui(AutomaticAssemblyTable, GuiEnum.AutoAssemblyTable.id, world, x, y, z)
      true
    } else {
      super.onBlockActivated(world, x, y, z, player, side, par7, par8, par9)
    }

  override def hasTileEntity(metadata: Int): Boolean = true

  override def createTileEntity(world: World, metadata: Int): TileEntity = new TileAutoAssemblyTable

  override def getBlockTexture(world: IBlockAccess, x: Int, y: Int, z: Int, side: Int): Icon = icons(tableGroupToDyeNumber(side))
}
