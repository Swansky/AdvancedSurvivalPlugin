package fr.swansky.advancedsurvivalplugin;

import org.bukkit.ChatColor;

public enum Rank {
    ADMIN("advancedsurvival.admin");

    private final String permission;
    public static final String NO_PERMISSION_MESSAGE = ChatColor.RED+"Tu n'as pas le droit d'executer cette commande.";
    Rank(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
