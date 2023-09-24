package io.github.bigcookie233.velocityteleporter;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class VelocityTeleporter extends JavaPlugin {
    private static VelocityTeleporter instance;

    @Override
    public void onEnable() {
        instance = this;
        this.getCommand("velocityteleporter").setExecutor(new onCommand());
        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "velocityteleporter:main");
    }

    public void sendMsg(Player player, String target) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("VTELEPORT");
        out.writeUTF(player.getUniqueId().toString());
        out.writeUTF(target);
        player.sendPluginMessage(this, "velocityteleporter:main", out.toByteArray());
    }

    public static VelocityTeleporter getInstance() {
        return instance;
    }
}
