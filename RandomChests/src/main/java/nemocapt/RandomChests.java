package nemocapt;

import nemocapt.chestmanager.ChestManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

/**
 * Отсюда три поля, все методы кроме onEnable, а также все содержимое onEnable
 * нужно перенести в твой главный класс
 */
public final class RandomChests extends JavaPlugin implements Listener {

    private ChestManager chestManager;
    private File customConfigFile;
    private FileConfiguration customConfig;

    @Override
    public void onEnable() {

        createCustomConfig();

        this.chestManager = new ChestManager(getCustomConfig());

        getServer().getPluginManager().registerEvents(this.chestManager, this);
    }

    private FileConfiguration getCustomConfig() {
        return this.customConfig;
    }

    private void createCustomConfig() {
        customConfigFile = new File(getDataFolder(), "randomchestsconfig.yml");
        if (!customConfigFile.exists()) {
            customConfigFile.getParentFile().mkdirs();
            saveResource("randomchestsconfig.yml", false);
        }

        customConfig= new YamlConfiguration();
        try {
            customConfig.load(customConfigFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        if(label.equalsIgnoreCase("resetchests")) {
            this.chestManager.resetChests();
            commandSender.sendMessage("Chests are reset.");
        }
        return true;
    }

}
