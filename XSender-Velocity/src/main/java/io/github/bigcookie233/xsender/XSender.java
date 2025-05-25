package io.github.bigcookie233.xsender;

import com.google.inject.Inject;
import com.google.inject.Provider;
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
        version = "1.3",
        authors = {"Bigcookie233"}
)
public class XSender {
    public final Logger logger;
    private final ProxyServer server;
    private final Provider<ProxyListener> provider;

    @Inject
    public XSender(ProxyServer server, Logger logger, Provider<ProxyListener> provider) {
        this.server = server;
        this.logger = logger;
        this.provider = provider;
    }

    @Subscribe
    public void onProxyInitialize(ProxyInitializeEvent event) {
        server.getChannelRegistrar().register(new LegacyChannelIdentifier("xsender:main"), MinecraftChannelIdentifier.create("velocityteleporter", "main"));
        server.getEventManager().register(this, this.provider.get());
    }
}
