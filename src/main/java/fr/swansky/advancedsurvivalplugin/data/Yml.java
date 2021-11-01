package fr.swansky.advancedsurvivalplugin.data;

import fr.swansky.advancedsurvivalplugin.AdvancedSurvivalPlugin;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.List;

public abstract class Yml<T> {
    protected String filename;
    protected File file;
    protected YamlConfiguration config;

    public Yml(String filename) {
        this.filename = filename;
        this.file = new File(AdvancedSurvivalPlugin.INSTANCE.getDataFolder(), this.getSavePath() + filename + ".yml");
        this.config = YamlConfiguration.loadConfiguration(file);
    }

    public abstract List<T> read();

    public abstract void write(T t);


    public void clean() {
        for (String key : this.config.getKeys(false)) {
            this.config.set(key, null);
        }
    }

    public void save() {
        try {
            this.config.save(this.file);
        } catch (IOException e) {
            AdvancedSurvivalPlugin.INSTANCE.getLogger().warning("Unable to save yaml file : " + this.file.getAbsolutePath());
            e.printStackTrace();
        }
    }


    protected abstract String getSavePath();

}
