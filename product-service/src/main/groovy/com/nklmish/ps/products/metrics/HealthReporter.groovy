package com.nklmish.ps.products.metrics

import com.codahale.metrics.JvmAttributeGaugeSet
import com.codahale.metrics.MetricFilter
import com.codahale.metrics.MetricRegistry
import com.codahale.metrics.graphite.Graphite
import com.codahale.metrics.graphite.GraphiteReporter
import com.codahale.metrics.jvm.GarbageCollectorMetricSet
import com.codahale.metrics.jvm.MemoryUsageGaugeSet

import static java.util.concurrent.TimeUnit.*

class HealthReporter {

    private MetricRegistry metricRegistry

    private String host

    private int port

    HealthReporter(MetricRegistry metricRegistry, String host, int port) {
        this.metricRegistry = metricRegistry
        this.host = host
        this.port = port
    }

    public void enableGraphiteReporter() {
        metricRegistry.register("garbage", new GarbageCollectorMetricSet())
        metricRegistry.register("jvm", new JvmAttributeGaugeSet())
        metricRegistry.register("memory", new MemoryUsageGaugeSet())
        final Graphite graphite = new Graphite(new InetSocketAddress(host, port))

        GraphiteReporter.forRegistry(metricRegistry)
                .prefixedWith("product-service")
                .convertRatesTo(SECONDS)
                .convertDurationsTo(MILLISECONDS)
                .filter(MetricFilter.ALL)
                .build(graphite)
                .start(1, MINUTES)
    }

}
