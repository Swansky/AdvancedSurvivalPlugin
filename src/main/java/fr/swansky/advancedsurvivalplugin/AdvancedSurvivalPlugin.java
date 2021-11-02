package fr.swansky.advancedsurvivalplugin;

import fr.swansky.advancedsurvivalplugin.customItem.CustomItemManager;
import fr.swansky.advancedsurvivalplugin.customItem.commands.GiveCustomItemCommand;
import fr.swansky.advancedsurvivalplugin.customItem.listeners.ClickCustomItemListener;
import fr.swansky.advancedsurvivalplugin.data.YmlManager;
import fr.swansky.advancedsurvivalplugin.economy.WalletManager;
import fr.swansky.advancedsurvivalplugin.economy.commands.GiveMoneyCommand;
import fr.swansky.advancedsurvivalplugin.economy.commands.MoneyCommand;
import fr.swansky.advancedsurvivalplugin.economy.commands.ResetWalletCommand;
import fr.swansky.advancedsurvivalplugin.economy.commands.TransferMoneyCommand;
import fr.swansky.advancedsurvivalplugin.market.MarketManager;
import fr.swansky.advancedsurvivalplugin.market.commands.*;
import fr.swansky.advancedsurvivalplugin.market.listeners.InventoryClickListener;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class AdvancedSurvivalPlugin extends JavaPlugin {
    public static AdvancedSurvivalPlugin INSTANCE;
    public static NamespacedKey NAMESPACE_KEY;
    private YmlManager ymlManager;
    private CustomItemManager customItemManager;
    private MarketManager marketController;
    private WalletManager walletManager;
    @Override
    public void onLoad() {
        INSTANCE = this;
        NAMESPACE_KEY = new NamespacedKey(INSTANCE, "advancedsurvival");
    }

    @Override
    public void onEnable() {
        this.ymlManager = new YmlManager();
        this.customItemManager = new CustomItemManager();
        this.marketController = new MarketManager();
        this.walletManager = new WalletManager();
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
        getCommand("addMarket").setExecutor(new AddMarketCommand(marketController));
        getCommand("addVillagerMarket").setExecutor(new AddVillagerMarketCommand());
        getCommand("deleteMarket").setExecutor(new DeleteMarketCommand());
        getCommand("marketList").setExecutor(new MarketListCommand(marketController));
        getCommand("addMarketItem").setExecutor(new AddMarketItemCommand(marketController));
        getCommand("openMarket").setExecutor(new OpenMarketCommand(marketController));

        // Economy system
        getCommand("giveMoney").setExecutor(new GiveMoneyCommand(walletManager));
        getCommand("resetMoney").setExecutor(new ResetWalletCommand(walletManager));
        getCommand("transferMoney").setExecutor(new TransferMoneyCommand(walletManager));
        getCommand("money").setExecutor(new MoneyCommand(walletManager));
      /*

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
        pm.registerEvents(new InventoryClickListener(), this);

    }


    public YmlManager getYmlManager() {
        return ymlManager;
    }
}
