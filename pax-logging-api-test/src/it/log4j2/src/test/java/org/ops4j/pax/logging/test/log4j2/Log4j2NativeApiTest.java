/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.ops4j.pax.logging.test.log4j2;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.apache.logging.log4j.ThreadContext;
import org.apache.logging.log4j.core.appender.ConsoleAppender;
import org.apache.logging.log4j.core.config.Configurator;
import org.apache.logging.log4j.core.config.builder.api.AppenderComponentBuilder;
import org.apache.logging.log4j.core.config.builder.api.ConfigurationBuilder;
import org.apache.logging.log4j.core.config.builder.api.ConfigurationBuilderFactory;
import org.apache.logging.log4j.core.config.builder.impl.BuiltConfiguration;
import org.apache.logging.log4j.message.MessageFormatMessageFactory;
import org.apache.logging.log4j.message.ReusableMessageFactory;
import org.apache.logging.log4j.spi.ExtendedLogger;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * <p>This unit test shows different log4j2 API usages. It's much easier than with Log4J1 because API separation
 * in Log4J2 is better.</p>
 */
public class Log4j2NativeApiTest {

    @BeforeClass
    public static void config() {
        ConfigurationBuilder<BuiltConfiguration> builder = ConfigurationBuilderFactory.newConfigurationBuilder();
        builder.setConfigurationName("programmatic");
        builder.setStatusLevel(Level.ERROR);

        AppenderComponentBuilder appenderBuilder = builder.newAppender("Stdout", "CONSOLE")
                .addAttribute("target", ConsoleAppender.Target.SYSTEM_OUT);
        appenderBuilder.add(builder.newLayout("PatternLayout")
                .addAttribute("pattern", "%d {%t} %c (%X) %level (%marker | %markerSimpleName): %msg%n%throwable"));
        builder.add(appenderBuilder);

        builder.add(builder.newRootLogger(Level.DEBUG)
                .add(builder.newAppenderRef("Stdout")));

        Configurator.initialize(builder.build(true));
    }

    @Test
    public void simplestUsage() {
        // Loggers are created by calling LogManager.getLogger. The Logger itself performs no direct actions.
        // It simply has a name and is associated with a LoggerConfig

        Logger log = LogManager.getLogger(Log4j2NativeApiTest.class);

        log.info("simplestUsage - INFO1");
        log.trace("simplestUsage - TRACE");

        Logger root = LogManager.getRootLogger();
        root.info("simplestUsage - INFO2");

        // Since naming Loggers after their owning class is such a common idiom, the convenience method
        // LogManager.getLogger() is provided to automatically use the calling class's fully qualified class name
        // as the Logger name.
        Logger log2 = LogManager.getLogger();
        log2.info("simplestUsage - INFO3");
    }

    @Test
    public void fqcn() {
        // Loggers are created by calling LogManager.getLogger. The Logger itself performs no direct actions.
        // It simply has a name and is associated with a LoggerConfig

        ExtendedLogger log = (ExtendedLogger) LogManager.getLogger(Log4j2NativeApiTest.class);

        log.log(Level.ERROR, "Hello 1!");
        log.log(Level.INFO, "Hello {}!!", "world");
        log.logMessage("a.b.c.d", Level.ERROR, null, ReusableMessageFactory.INSTANCE.newMessage("Hello 2!"), null);
    }

    @Test
    public void mdc() {
        Logger log = LogManager.getLogger(Log4j2NativeApiTest.class);

        ThreadContext.put("country", "Equestria");
        log.info("mdc - INFO");
        ThreadContext.clearAll();
    }

    @Test
    public void markers() {
        // Loggers are created by calling LogManager.getLogger. The Logger itself performs no direct actions.
        // It simply has a name and is associated with a LoggerConfig

        Logger log = LogManager.getLogger(Log4j2NativeApiTest.class);

        Marker m1 = MarkerManager.getMarker("m1");
        m1.addParents(MarkerManager.getMarker("p1"), MarkerManager.getMarker("p2"));
        log.info(m1, "markers - INFO");
    }

    @Test
    public void levels() {
        // LoggerConfigs will be assigned a Log Level. The set of built-in levels includes TRACE, DEBUG, INFO, WARN,
        // ERROR, and FATAL. Log4j 2 also supports custom log levels. Another mechanism for getting more granularity
        // is to use Markers instead.
    }

    @Test
    public void filters() {
        // In addition to the automatic log Level filtering that takes place as described in the previous section,
        // Log4j provides Filters that can be applied before control is passed to any LoggerConfig, after control
        // is passed to a LoggerConfig but before calling any Appenders, after control is passed to a LoggerConfig
        // but before calling a specific Appender, and on each Appender.
    }

    @Test
    public void appenders() {
        // The ability to selectively enable or disable logging requests based on their logger is only part of the
        // picture. Log4j allows logging requests to print to multiple destinations. In log4j speak, an output
        // destination is called an Appender. Currently, appenders exist for the console, files, remote socket servers,
        // Apache Flume, JMS, remote UNIX Syslog daemons, and various database APIs.
    }

}
