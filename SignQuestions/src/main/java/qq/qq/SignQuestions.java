package qq.qq;

import org.bukkit.plugin.java.JavaPlugin;
import qq.qq.manager.SignManager;
import qq.qq.manager.players.StudentPlayer;

import java.util.ArrayList;
import java.util.List;

public final class SignQuestions extends JavaPlugin {

    public static List<StudentPlayer> studentPlayers = new ArrayList<>();

    @Override
    public void onEnable() {
        // Plugin startup logic

        getServer().getPluginManager().registerEvents(new SignManager(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
