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
import fr.swansky.advancedsurvivalplugin.economy.listeners.PlayerJoinListener;
import fr.swansky.advancedsurvivalplugin.home.HomeManager;
import fr.swansky.advancedsurvivalplugin.home.commands.DeleteHomeCommand;
import fr.swansky.advancedsurvivalplugin.home.commands.HomeCommand;
import fr.swansky.advancedsurvivalplugin.home.commands.SetHomeCommand;
import fr.swansky.advancedsurvivalplugin.market.CustomMarketVillagerManager;
import fr.swansky.advancedsurvivalplugin.market.MarketManager;
import fr.swansky.advancedsurvivalplugin.market.commands.*;
import fr.swansky.advancedsurvivalplugin.market.listeners.CustomMarketVillagerClickEvent;
import fr.swansky.advancedsurvivalplugin.market.listeners.InventoryClickListener;
import fr.swansky.advancedsurvivalplugin.utilsGameplay.BackManager;
import fr.swansky.advancedsurvivalplugin.utilsGameplay.commands.BackCommand;
import fr.swansky.advancedsurvivalplugin.utilsGameplay.commands.CraftCommand;
import fr.swansky.advancedsurvivalplugin.utilsGameplay.commands.EnderChestCommand;
import fr.swansky.advancedsurvivalplugin.utilsGameplay.commands.SlimeCommand;
import fr.swansky.advancedsurvivalplugin.utilsGameplay.listeners.CoordinateOnDeathListener;
import fr.swansky.advancedsurvivalplugin.utilsGameplay.listeners.TeleportListener;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class AdvancedSurvivalPlugin extends JavaPlugin {
    public static AdvancedSurvivalPlugin INSTANCE;
    public static NamespacedKey NAMESPACE_KEY;
    private YmlManager ymlManager;
    private CustomItemManager customItemManager;
    private MarketManager marketManager;
    private WalletManager walletManager;
    private HomeManager homeManager;
    private CustomMarketVillagerManager customMarketVillagerManager;
    private BackManager backManager;

    @Override
    public void onLoad() {
        INSTANCE = this;
        NAMESPACE_KEY = new NamespacedKey(INSTANCE, "advancedsurvival");
    }

    @Override
    public void onEnable() {
        this.ymlManager = new YmlManager();
        this.walletManager = new WalletManager();
        this.customItemManager = new CustomItemManager();
        this.marketManager = new MarketManager();
        this.customMarketVillagerManager = new CustomMarketVillagerManager(marketManager);
        this.homeManager = new HomeManager();
        this.backManager = new BackManager();

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
        getCommand("addVillagerMarket").setExecutor(new AddVillagerMarketCommand(marketManager, customMarketVillagerManager));
        getCommand("deleteVillagerMarket").setExecutor(new DeleteVillagerMarketCommand(customMarketVillagerManager));
        getCommand("deleteMarket").setExecutor(new DeleteMarketCommand(marketManager));
        getCommand("marketList").setExecutor(new MarketListCommand(marketManager));
        getCommand("addMarketItem").setExecutor(new AddMarketItemCommand(marketManager));
        getCommand("openMarket").setExecutor(new OpenMarketCommand(marketManager));

        // Economy system
        getCommand("giveMoney").setExecutor(new GiveMoneyCommand(walletManager));
        getCommand("resetMoney").setExecutor(new ResetWalletCommand(walletManager));
        getCommand("transferMoney").setExecutor(new TransferMoneyCommand(walletManager));
        getCommand("money").setExecutor(new MoneyCommand(walletManager));

        // Homes
        getCommand("delHome").setExecutor(new DeleteHomeCommand(homeManager));
        getCommand("home").setExecutor(new HomeCommand(homeManager));
        getCommand("setHome").setExecutor(new SetHomeCommand(homeManager));


        // Utils Commands
        getCommand("craft").setExecutor(new CraftCommand());
        getCommand("ec").setExecutor(new EnderChestCommand());
        getCommand("slime").setExecutor(new SlimeCommand());
        getCommand("back").setExecutor(new BackCommand(backManager));

    }

    private void registerEvents() {
        final PluginManager pm = this.getServer().getPluginManager();

        pm.registerEvents(new ClickCustomItemListener(customItemManager), this);
        pm.registerEvents(new InventoryClickListener(), this);
        pm.registerEvents(new PlayerJoinListener(walletManager), this);
        pm.registerEvents(new CustomMarketVillagerClickEvent(customMarketVillagerManager), this);

        //utils gameplay
        pm.registerEvents(new CoordinateOnDeathListener(), this);
        pm.registerEvents(new TeleportListener(backManager), this);
    }


    public YmlManager getYmlManager() {
        return ymlManager;
    }

    public MarketManager getMarketManager() {
        return marketManager;
    }

    public WalletManager getWalletManager() {
        return walletManager;
    }
}
