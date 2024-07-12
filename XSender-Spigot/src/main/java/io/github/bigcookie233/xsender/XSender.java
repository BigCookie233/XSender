package io.github.bigcookie233.xsender;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class XSender extends JavaPlugin {
    private static XSender instance;

    @Override
    public void onEnable() {
        instance = this;
        this.getCommand("xsender").setExecutor(new onCommand());
        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "xsender:main");
    }

    public void sendMsg(Player player, String target) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("XSEND");
        out.writeUTF(player.getUniqueId().toString());
        out.writeUTF(target);
        player.sendPluginMessage(this, "xsender:main", out.toByteArray());
    }

    public static XSender getInstance() {
        return instance;
    }
}
