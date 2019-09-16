package com.lgg.nticxs.web.config.security.cookie;

import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.codahale.metrics.Counter;
import com.lgg.nticxs.web.config.security.cookie.MetricRegistrySingleton;

public class SessionListenerWithMetrics implements HttpSessionListener {

    private final AtomicInteger activeSessions;

    private final Counter counterOfActiveSessions;

    public SessionListenerWithMetrics() {
        super();

        activeSessions = new AtomicInteger();
        counterOfActiveSessions = MetricRegistrySingleton.metrics.counter("web.sessions.active.count");
    }

    // API

    public final int getTotalActiveSession() {
        return activeSessions.get();
    }

    @Override
    public final void sessionCreated(final HttpSessionEvent event) {
        activeSessions.incrementAndGet();
        counterOfActiveSessions.inc();
    }

    @Override
    public final void sessionDestroyed(final HttpSessionEvent event) {
        activeSessions.decrementAndGet();
        counterOfActiveSessions.dec();
    }

}