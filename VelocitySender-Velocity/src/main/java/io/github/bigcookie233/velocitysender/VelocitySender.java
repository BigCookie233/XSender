package io.github.bigcookie233.velocitysender;

import com.google.inject.Inject;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.proxy.ProxyServer;
import com.velocitypowered.api.proxy.messages.LegacyChannelIdentifier;
import com.velocitypowered.api.proxy.messages.MinecraftChannelIdentifier;
import org.slf4j.Logger;

@Plugin(id = "velocitysender", name = "VelocitySender", version = "1.0", authors = {"Bigcookie233"})
public class VelocitySender {
    private static VelocitySender instance;
    private final ProxyServer server;
    public final Logger logger;

    @Inject
    public VelocitySender(ProxyServer server, Logger logger) {
        instance = this;
        this.server = server;
        this.logger = logger;
    }

    @Subscribe
    public void onProxyInitialize(ProxyInitializeEvent event) {
        server.getChannelRegistrar().register(new LegacyChannelIdentifier("velocitysender:main"), MinecraftChannelIdentifier.create("velocitysender", "main"));
        server.getEventManager().register(this, new ProxyListener());
        System.out.println("register");
    }

    public ProxyServer getServer() {
        return server;
    }

    public static VelocitySender getInstance() {
        return instance;
    }
}
