package io.github.bigcookie233.velocitysender;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class VelocitySender extends JavaPlugin {
    private static VelocitySender instance;

    @Override
    public void onEnable() {
        instance = this;
        this.getCommand("velocitysender").setExecutor(new onCommand());
        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "velocitysender:main");
    }

    public void sendMsg(Player player, String target) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("SELECT");
        out.writeUTF(player.getUniqueId().toString());
        out.writeUTF(target);
        player.sendPluginMessage(this, "velocitysender:main", out.toByteArray());
    }

    public static VelocitySender getInstance() {
        return instance;
    }
}
