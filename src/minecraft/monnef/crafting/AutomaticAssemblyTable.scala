/*
 * Automatic Assembly Table
 * author: monnef
 */

package monnef.crafting

import cpw.mods.fml.common.{SidedProxy, Mod}
import cpw.mods.fml.common.network.NetworkMod
import cpw.mods.fml.common.Mod.EventHandler
import cpw.mods.fml.common.event.{FMLPostInitializationEvent, FMLInitializationEvent, FMLPreInitializationEvent}
import monnef.crafting.common.{Reference, CommonProxy}

@Mod(modid = Reference.modId, name = Reference.modName, version = Reference.version, modLanguage = "scala", dependencies = "required-after:monnef-core")
@NetworkMod(clientSideRequired = true, serverSideRequired = true)
object AutomaticAssemblyTable {
  @SidedProxy(clientSide = "monnef.crafting.client.ClientProxy", serverSide = "monnef.crafting.common.CommonProxy")
  var proxy: CommonProxy = null

  @EventHandler
  def preInit(e: FMLPreInitializationEvent) {
  }

  @EventHandler
  def init(e: FMLInitializationEvent) {
    proxy.onLoad()
  }

  @EventHandler
  def postInit(e: FMLPostInitializationEvent) {
  }
}
