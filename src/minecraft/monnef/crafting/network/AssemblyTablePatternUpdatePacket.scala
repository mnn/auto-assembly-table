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
import cpw.mods.fml.client.FMLClientHandler
import net.minecraft.client.Minecraft

/**
 * Update packet of crafting pattern (from color buttons) of assembly table
 * @param idxToState Data
 * @param side Target side
 */
class AssemblyTablePatternUpdatePacket(var idxToState: List[(Int, Int)] = List.empty, var side: Side = null) extends PacketMonnefCoreBase {
  /*
   byte : side; 0 - client, 1 - server
   byte : number of items
   item :
     byte: index of button
     byte: state of button
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
      out.writeByte(v._2.toByte)
    })
  }

  def read(in: MessageIn[_]) {
    side = if (in.readByte() == 0) Side.CLIENT else Side.SERVER
    val size = in.readByte()
    idxToState = List.empty
    for (i <- 1 to size) idxToState ::=(in.readByte(), in.readByte())
    idxToState = idxToState.reverse
  }

  override def executeServer(player: EntityPlayerMP) {
    if (side != Side.SERVER) WRONG_SIDE
    // TODO
  }

  override def executeClient(player: EntityPlayerSP) {
    if (side != Side.CLIENT) WRONG_SIDE
    Minecraft.getMinecraft.currentScreen match {
      case gui: GuiAutoAssemblyTable =>
        gui.tableTile.
      case _ =>
    }
  }
}

object AssemblyTablePatternUpdatePacket {
  def create(button: CraftingColorButton, buttonIndex: Int, side: Side): AssemblyTablePatternUpdatePacket = new AssemblyTablePatternUpdatePacket(
    List(buttonIndex -> button.getCurrentStateNumber), side
  )

  def create(buttons: Map[Int, CraftingColorButton], side: Side): AssemblyTablePatternUpdatePacket = new AssemblyTablePatternUpdatePacket(
    buttons.toList.map {a => a._1 -> a._2.getCurrentStateNumber}, side
  )
}