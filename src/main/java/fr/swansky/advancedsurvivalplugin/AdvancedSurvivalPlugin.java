package fr.swansky.advancedsurvivalplugin;

import fr.swansky.advancedsurvivalplugin.customItem.CustomItemManager;
import fr.swansky.advancedsurvivalplugin.customItem.commands.GiveCustomItemCommand;
import fr.swansky.advancedsurvivalplugin.customItem.listeners.ClickCustomItemListener;
import fr.swansky.advancedsurvivalplugin.data.YmlManager;
import fr.swansky.advancedsurvivalplugin.market.MarketManager;
import fr.swansky.advancedsurvivalplugin.market.commands.AddMarketCommand;
import fr.swansky.advancedsurvivalplugin.market.commands.AddVillagerMarketCommand;
import fr.swansky.advancedsurvivalplugin.market.commands.DeleteMarketCommand;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class AdvancedSurvivalPlugin extends JavaPlugin {
    public static AdvancedSurvivalPlugin INSTANCE;
    public static NamespacedKey NAMESPACE_KEY;
    private YmlManager ymlManager;
    private CustomItemManager customItemManager;
    private MarketManager marketManager;

    @Override
    public void onLoad() {
        INSTANCE = this;
        NAMESPACE_KEY = new NamespacedKey(INSTANCE, "advancedsurvival");
    }

    @Override
    public void onEnable() {
        this.ymlManager = new YmlManager();
        this.customItemManager = new CustomItemManager();
        this.marketManager = new MarketManager();
        registerEvents();
        registerCommands();

    }

    @Override
    public void onDisable() {

    }


    private void registerCommands() {
        // Custom items
        getCommand("customItem").setExecutor(new GiveCustomItemCommand());

        // Markets
        getCommand("addMarket").setExecutor(new AddMarketCommand(marketManager));
        getCommand("addVillagerMarket").setExecutor(new AddVillagerMarketCommand());
        getCommand("deleteMarket").setExecutor(new DeleteMarketCommand());

      /*  // Economy system
        getCommand("giveMoney").setExecutor(new GiveMoneyCommand());
        getCommand("resetMoney").setExecutor(new ResetWalletCommand());
        getCommand("transferMoney").setExecutor(new TransferMoneyCommand());

        // Homes
        getCommand("delHome").setExecutor(new DeleteHomeCommand());
        getCommand("home").setExecutor(new HomeCommand());
        getCommand("setHome").setExecutor(new SetHomeCommand());



        // Utils Commands
        getCommand("craft").setExecutor(new CraftCommand());
        getCommand("ec").setExecutor(new EnderChestCommand());
        getCommand("slime").setExecutor(new SlimeCommand());*/
    }

    private void registerEvents() {
        final PluginManager pm = this.getServer().getPluginManager();

        pm.registerEvents(new ClickCustomItemListener(customItemManager), this);

    }


    public YmlManager getYmlManager() {
        return ymlManager;
    }
}
