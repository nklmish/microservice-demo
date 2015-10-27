package com.nklmish.cs.comments.metrics;

import com.codahale.metrics.JvmAttributeGaugeSet;
import com.codahale.metrics.MetricFilter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.graphite.Graphite;
import com.codahale.metrics.graphite.GraphiteReporter;
import com.codahale.metrics.jvm.GarbageCollectorMetricSet;
import com.codahale.metrics.jvm.MemoryUsageGaugeSet;

import java.net.InetSocketAddress;

import static java.util.concurrent.TimeUnit.*;

public class HealthReporter {

    private MetricRegistry metricRegistry;

    private String host;

    private int port;

    public HealthReporter(MetricRegistry metricRegistry, String graphiteHost, int graphitePort) {
        this.metricRegistry = metricRegistry;
        this.host = graphiteHost;
        this.port = graphitePort;
    }

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
