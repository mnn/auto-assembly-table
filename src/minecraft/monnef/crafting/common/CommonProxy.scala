/*
 * Automatic Assembly Table
 * author: monnef
 */

package monnef.crafting.common

import monnef.core.common.ContainerRegistry
import monnef.crafting.block.TileAutoAssemblyTable

class CommonProxy {
  def registerContainers() {
    ContainerRegistry.register(classOf[TileAutoAssemblyTable], classOf[ContainerAutoAssemblyTable])
  }

  def onLoad() = {}
}
