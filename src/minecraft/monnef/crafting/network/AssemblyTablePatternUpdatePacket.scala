/*
 * Automatic Assembly Table
 * author: monnef
 */

package monnef.crafting.network

import monnef.core.network.common.PacketMonnefCoreBase
import monnef.core.network.message.{MessageOut, MessageIn}
import monnef.crafting.client.{GuiAutoAssemblyTable, CraftingColorButton}
import cpw.mods.fml.relauncher.Side
import net.minecraft.client.entity.EntityPlayerSP
import net.minecraft.entity.player.EntityPlayerMP
import net.minecraft.client.Minecraft
import monnef.core.MonnefCorePlugin
import monnef.crafting.common.ContainerAutoAssemblyTable
import java.net.ProtocolException
import monnef.crafting.block.TileAutoAssemblyTable
import net.minecraft.inventory.ICrafting
import scala.collection.JavaConverters._
import monnef.core.utils.PlayerHelper

/**
 * Update packet of crafting pattern (from color buttons) of assembly table
 * @param idxToState Data
 * @param side Target side
 */
class AssemblyTablePatternUpdatePacket(var idxToState: List[(Int, Int)] = List.empty, var side: Side = null) extends PacketMonnefCoreBase {
  final val DEBUG = true
  /*
   byte : side; 0 - client, 1 - server
   byte : number of items
   item :
     byte: index of button
     byte: state of button (zero based, not minus one based!)
   */

  // to force Scala compiler to actually create a parameter-less constructor
  def this() = this(List.empty, null)

  def checkNullSide() { if (side == null) throw new RuntimeException("Got null side!") }

  def write(out: MessageOut[_]) {
    checkNullSide()
    out.writeByte(if (side == Side.CLIENT) 0 else 1)
    out.writeByte(idxToState.length.toByte)
    idxToState.foreach(v => {
      out.writeByte(v._1.toByte)
      out.writeByte((v._2 + 1).toByte)
    })
  }

  def read(in: MessageIn[_]) {
    side = if (in.readByte() == 0) Side.CLIENT else Side.SERVER
    val size = in.readByte()
    idxToState = List.empty
    for (i <- 1 to size) idxToState ::=(in.readByte(), in.readByte() - 1)
    idxToState = idxToState.reverse
  }

  override def executeServer(player: EntityPlayerMP) {
    if (side != Side.SERVER) WRONG_SIDE
    player.openContainer match {
      case c: ContainerAutoAssemblyTable =>
        val tile = c.tableTile
        tile.getErrorOfIndexStateList(idxToState) match {
          case Some(error) => throw new ProtocolException(error)
          case None =>
        }
        // update server pattern
        if (DEBUG) MonnefCorePlugin.Log.printDebug(s"S: updating pattern - $idxToState")
        tile.updatePattern(idxToState)
        // send to all crafters
        val updatePacket = new AssemblyTablePatternUpdatePacket(idxToState, Side.CLIENT)
        for {
          a <- PlayerHelper.getPlayersWithOpenedContainerAround(player, 5, classOf[ContainerAutoAssemblyTable]).asScala // don't use c.getCrafters.asScala, it returns only one!
          if a.isInstanceOf[EntityPlayerMP]
          player = a.asInstanceOf[EntityPlayerMP]
        } updatePacket.sendToClient(player)

      case _ => throw new ProtocolException(s"Got ${this.getClass.getSimpleName} on a server, but no container is assigned to this player.")
    }
  }

  override def executeClient(player: EntityPlayerSP) {
    if (side != Side.CLIENT) WRONG_SIDE
    Minecraft.getMinecraft.currentScreen match {
      case gui: GuiAutoAssemblyTable =>
        if (DEBUG) MonnefCorePlugin.Log.printDebug(s"C: updating pattern - $idxToState")
        gui.tableTile.updatePattern(idxToState)

      case _ => MonnefCorePlugin.Log.printWarning(s"Got ${this.getClass.getSimpleName}, but no GUI is opened. Ignoring.")
    }
  }
}

object AssemblyTablePatternUpdatePacket {
  def create(tableTile: TileAutoAssemblyTable, side: Side): AssemblyTablePatternUpdatePacket = new AssemblyTablePatternUpdatePacket(
    tableTile.generateCompleteIndexStateList(), side
  )

  def create(button: CraftingColorButton, buttonIndex: Int, side: Side): AssemblyTablePatternUpdatePacket = new AssemblyTablePatternUpdatePacket(
    List(buttonIndex -> button.numberOfSelectedState), side
  )

  def create(buttons: Map[Int, CraftingColorButton], side: Side): AssemblyTablePatternUpdatePacket = new AssemblyTablePatternUpdatePacket(
    buttons.toList.map {a => a._1 -> a._2.getCurrentStateNumber}, side
  )
}