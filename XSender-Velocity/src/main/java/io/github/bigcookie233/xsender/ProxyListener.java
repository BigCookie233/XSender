package io.github.bigcookie233.xsender;

import com.google.common.io.ByteArrayDataInput;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.connection.PluginMessageEvent;
import com.velocitypowered.api.proxy.ConnectionRequestBuilder;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.ProxyServer;
import com.velocitypowered.api.proxy.ServerConnection;
import com.velocitypowered.api.proxy.server.RegisteredServer;

import java.util.Optional;
import java.util.UUID;

public class ProxyListener {
    private final ProxyServer server;

    public ProxyListener() {
        this.server = XSender.getInstance().getServer();
    }

    @Subscribe
    public void onPluginMessage(PluginMessageEvent event) {
        if (!(event.getSource() instanceof ServerConnection))
            return;

        if (!event.getIdentifier().getId().equals("xsender:main"))
            return;

        ByteArrayDataInput input = event.dataAsDataStream();
        String sChannel = input.readUTF();
        if (!sChannel.equals("XSEND"))
            return;

        String s1 = input.readUTF();
        Optional<Player> player = this.server.getPlayer(UUID.fromString(s1));
        if (!player.isPresent())
            return;

        String s2 = input.readUTF();
        Optional<RegisteredServer> registeredServer = this.server.getServer(s2);
        if (!registeredServer.isPresent())
            return;

        ConnectionRequestBuilder builder = player.get().createConnectionRequest(registeredServer.get());
        builder.fireAndForget();
    }
}
