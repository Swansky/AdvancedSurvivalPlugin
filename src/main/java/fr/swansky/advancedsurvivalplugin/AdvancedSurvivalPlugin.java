package fr.swansky.advancedsurvivalplugin;

import fr.swansky.advancedsurvivalplugin.customItem.commands.GiveCustomItemCommand;
import fr.swansky.advancedsurvivalplugin.data.YmlManager;
import fr.swansky.advancedsurvivalplugin.economy.commands.GiveMoneyCommand;
import fr.swansky.advancedsurvivalplugin.economy.commands.ResetWalletCommand;
import fr.swansky.advancedsurvivalplugin.economy.commands.TransferMoneyCommand;
import fr.swansky.advancedsurvivalplugin.home.commands.DeleteHomeCommand;
import fr.swansky.advancedsurvivalplugin.home.commands.HomeCommand;
import fr.swansky.advancedsurvivalplugin.home.commands.SetHomeCommand;
import fr.swansky.advancedsurvivalplugin.market.commands.AddMarketCommand;
import fr.swansky.advancedsurvivalplugin.market.commands.AddVillagerMarketCommand;
import fr.swansky.advancedsurvivalplugin.market.commands.DeleteMarketCommand;
import fr.swansky.advancedsurvivalplugin.utilsCommands.CraftCommand;
import fr.swansky.advancedsurvivalplugin.utilsCommands.EnderChestCommand;
import fr.swansky.advancedsurvivalplugin.utilsCommands.SlimeCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class AdvancedSurvivalPlugin extends JavaPlugin {
    public static AdvancedSurvivalPlugin INSTANCE;
    private YmlManager ymlManager;

    @Override
    public void onLoad() {
        INSTANCE = this;
    }

    @Override
    public void onEnable() {
        this.ymlManager = new YmlManager();
        registerCommands();

    }

    @Override
    public void onDisable() {

    }



    private void registerCommands() {
        // Custom items
        getCommand("customItem").setExecutor(new GiveCustomItemCommand());

        // Economy system
        getCommand("giveMoney").setExecutor(new GiveMoneyCommand());
        getCommand("resetMoney").setExecutor(new ResetWalletCommand());
        getCommand("transferMoney").setExecutor(new TransferMoneyCommand());

        // Homes
        getCommand("delHome").setExecutor(new DeleteHomeCommand());
        getCommand("home").setExecutor(new HomeCommand());
        getCommand("setHome").setExecutor(new SetHomeCommand());

        // Markets
        getCommand("addMarket").setExecutor(new AddMarketCommand());
        getCommand("addVillagerMarket").setExecutor(new AddVillagerMarketCommand());
        getCommand("deleteMarket").setExecutor(new DeleteMarketCommand());

        // Utils Commands
        getCommand("craft").setExecutor(new CraftCommand());
        getCommand("ec").setExecutor(new EnderChestCommand());
        getCommand("slime").setExecutor(new SlimeCommand());
    }

    public YmlManager getYmlManager() {
        return ymlManager;
    }
}
