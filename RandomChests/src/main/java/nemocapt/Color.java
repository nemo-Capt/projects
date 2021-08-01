package nemocapt;

import org.bukkit.ChatColor;

public class Color {

    public static String colorizeMyMessage(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }

}
