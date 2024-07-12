package io.github.bigcookie233.xsender;

import com.google.inject.Inject;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.proxy.ProxyServer;
import com.velocitypowered.api.proxy.messages.LegacyChannelIdentifier;
import com.velocitypowered.api.proxy.messages.MinecraftChannelIdentifier;
import org.slf4j.Logger;

@Plugin(
        id = "xsender",
        name = "XSender",
        version = "1.2",
        authors = {"Bigcookie233"}
)
public class XSender {
    private static XSender instance;
    private final ProxyServer server;
    public final Logger logger;

    @Inject
    public XSender(ProxyServer server, Logger logger) {
        instance = this;
        this.server = server;
        this.logger = logger;
    }

    @Subscribe
    public void onProxyInitialize(ProxyInitializeEvent event) {
        server.getChannelRegistrar().register(new LegacyChannelIdentifier("xsender:main"), MinecraftChannelIdentifier.create("velocityteleporter", "main"));
        server.getEventManager().register(this, new ProxyListener());
    }

    public ProxyServer getServer() {
        return server;
    }

    public static XSender getInstance() {
        return instance;
    }
}
