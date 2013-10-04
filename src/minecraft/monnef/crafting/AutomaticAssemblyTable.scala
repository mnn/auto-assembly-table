/*
 * Automatic Assembly Table
 * author: monnef
 */

package monnef.crafting

import cpw.mods.fml.common.{SidedProxy, Mod}
import cpw.mods.fml.common.network.NetworkMod
import cpw.mods.fml.common.Mod.EventHandler
import cpw.mods.fml.common.event.{FMLPostInitializationEvent, FMLInitializationEvent, FMLPreInitializationEvent}
import monnef.crafting.common.{ConfigurationManager, Reference, CommonProxy}
import monnef.core.utils.{RegistryUtils, CustomLogger, IDProvider}
import net.minecraftforge.common.Configuration
import monnef.crafting.block.BlockAutoAssemblyTable
import monnef.crafting.client.CraftingCreativeTab
import java.lang.Exception
import net.minecraft.item.ItemStack

@Mod(modid = Reference.modId, name = Reference.modName, version = Reference.version, modLanguage = "scala", dependencies = "required-after:monnef-core")
@NetworkMod(clientSideRequired = true, serverSideRequired = true)
object AutomaticAssemblyTable {
  @SidedProxy(clientSide = "monnef.crafting.client.ClientProxy", serverSide = "monnef.crafting.common.CommonProxy")
  var proxy: CommonProxy = null
  var idProvider: IDProvider = new IDProvider(2200, 22000, Reference.modName)
  var config: Configuration = _
  var creativeTab: CraftingCreativeTab = _
  var log: CustomLogger = new CustomLogger(Reference.modId)

  var aat: BlockAutoAssemblyTable = _

  @EventHandler
  def preInit(e: FMLPreInitializationEvent) {
    config = new Configuration(e.getSuggestedConfigurationFile)
    try {
      config.load()
      idProvider.linkWithConfig(config)
      ConfigurationManager.loadSettings(config)
    } catch {
      case ex: Exception => log.printSevere("Cannot load config, got: " + ex.getMessage)
    } finally {
      config.save()
    }

    creativeTab = new CraftingCreativeTab("monnefCrafting", Reference.modName)

    aat = new BlockAutoAssemblyTable(idProvider.getBlockIDFromConfig("autoAssemblyTable"))
    RegistryUtils.registerBlock(aat, "autoAssemblyTable", "Automatic Assembly Table")

    creativeTab setup new ItemStack(aat).getItem
  }

  @EventHandler
  def init(e: FMLInitializationEvent) {
    proxy.onLoad()
    // TODO: register recipes
  }

  @EventHandler
  def postInit(e: FMLPostInitializationEvent) {
    log.printInfo(Reference.modName + " by monnef is successfully initialized.")
  }
}
