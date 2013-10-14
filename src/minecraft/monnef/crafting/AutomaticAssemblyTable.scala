/*
 * Automatic Assembly Table
 * author: monnef
 */

package monnef.crafting

import cpw.mods.fml.common.{ModMetadata, SidedProxy, Mod}
import cpw.mods.fml.common.network.{NetworkRegistry, NetworkMod}
import cpw.mods.fml.common.Mod.EventHandler
import cpw.mods.fml.common.event.{FMLPostInitializationEvent, FMLInitializationEvent, FMLPreInitializationEvent}
import monnef.crafting.common.{GuiHandler, ConfigurationManager, CommonProxy}
import monnef.core.utils.{RegistryUtils, CustomLogger, IDProvider}
import net.minecraftforge.common.Configuration
import monnef.crafting.block.BlockAutoAssemblyTable
import monnef.crafting.block.TileAutoAssemblyTable
import monnef.crafting.client.CraftingCreativeTab
import java.lang.Exception
import net.minecraft.item.ItemStack
import scala.collection.JavaConverters._
import cpw.mods.fml.common.registry.GameRegistry
import monnef.crafting.common.Reference._
import net.minecraft.tileentity.TileEntity
import monnef.core.client.PackageToModIdRegistry
import monnef.core.common.ContainerRegistry

@Mod(modid = modId, name = modName, version = version, modLanguage = "scala", dependencies = "required-after:monnef-core")
@NetworkMod(clientSideRequired = true, serverSideRequired = true)
object AutomaticAssemblyTable {
  @SidedProxy(clientSide = "monnef.crafting.client.ClientProxy", serverSide = "monnef.crafting.common.CommonProxy")
  var proxy: CommonProxy = null
  var idProvider: IDProvider = new IDProvider(2200, 22000, modName)
  var config: Configuration = _
  var creativeTab: CraftingCreativeTab = _
  var log: CustomLogger = new CustomLogger(modId)

  var aat: BlockAutoAssemblyTable = _

  def setupMetadata(metadata: ModMetadata) {
    metadata.authorList = List(author).asJava
  }

  def registerTile(c: Class[_ <: TileEntity], n: String) { GameRegistry.registerTileEntity(c, modId + n) }

  @EventHandler
  def preInit(e: FMLPreInitializationEvent) {
    setupMetadata(e.getModMetadata)
    PackageToModIdRegistry.registerClassToModId()
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

    //proxy.registerContainers()

    creativeTab = new CraftingCreativeTab("monnefCrafting", modName)

    aat = new BlockAutoAssemblyTable(idProvider.getBlockIDFromConfig("autoAssemblyTable"))
    RegistryUtils.registerBlock(aat, "autoAssemblyTable", "Automatic Assembly Table")
    registerTile(classOf[TileAutoAssemblyTable], "autoAssemblyTable")

    creativeTab setup new ItemStack(aat).getItem
    NetworkRegistry.instance().registerGuiHandler(this, new GuiHandler())
  }

  @EventHandler
  def init(e: FMLInitializationEvent) {
    proxy.onLoad()
    // TODO: register recipes
  }

  @EventHandler
  def postInit(e: FMLPostInitializationEvent) {
    log.printInfo(modName + " by monnef is successfully initialized.")
  }
}
