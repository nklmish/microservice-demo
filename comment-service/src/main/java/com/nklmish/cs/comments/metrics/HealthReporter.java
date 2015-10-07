package com.nklmish.cs.comments.metrics;

import com.codahale.metrics.JvmAttributeGaugeSet;
import com.codahale.metrics.MetricFilter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.graphite.Graphite;
import com.codahale.metrics.graphite.GraphiteReporter;
import com.codahale.metrics.jvm.GarbageCollectorMetricSet;
import com.codahale.metrics.jvm.MemoryUsageGaugeSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;

import static java.util.concurrent.TimeUnit.*;
import static java.util.concurrent.TimeUnit.SECONDS;

@Component
public class HealthReporter {

    @Autowired
    private MetricRegistry metricRegistry;

    @Value("${app.graphite.host}")
    private String host;

    @Value("${app.graphite.port}")
    private int port;

    public void enableGraphiteReporter() {
        metricRegistry.register("garbage", new GarbageCollectorMetricSet());
        metricRegistry.register("jvm", new JvmAttributeGaugeSet());
        metricRegistry.register("memory", new MemoryUsageGaugeSet());
        final Graphite graphite = new Graphite(new InetSocketAddress(host, port));

        GraphiteReporter.forRegistry(metricRegistry)
                .prefixedWith("comment-service")
                .convertRatesTo(SECONDS)
                .convertDurationsTo(MILLISECONDS)
                .filter(MetricFilter.ALL)
                .build(graphite)
                .start(1, MINUTES);
    }
}
