/*
 * Automatic Assembly Table
 * author: monnef
 */

package monnef.crafting.client

import monnef.crafting.common.CommonProxy
import monnef.core.common.ContainerRegistry
import monnef.crafting.block.TileAutoAssemblyTable

class ClientProxy extends CommonProxy {


  override def registerContainers() {
    super.registerContainers()
    ContainerRegistry.registerOnClient(classOf[TileAutoAssemblyTable], classOf[GuiAutoAssemblyTable])
  }

  override def onLoad() {
  }
}
