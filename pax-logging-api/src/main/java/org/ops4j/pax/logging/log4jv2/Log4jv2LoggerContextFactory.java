/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache license, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the license for the specific language governing permissions and
 * limitations under the license.
 */
package org.ops4j.pax.logging.log4jv2;

import java.net.URI;

import org.apache.logging.log4j.spi.LoggerContext;
import org.apache.logging.log4j.spi.LoggerContextFactory;

/**
 * This is the main class that's briding Log4J2 API into Pax Logging. It's responsibility is to
 * produce {@link LoggerContext} objects.
 *
 * See http://logging.apache.org/log4j/2.x/manual/architecture.html
 *
 * Even if there are scenarios where multiple {@link LoggerContext contexts} may be used, this factory
 * holds single, static Pax Logging-specific {@link LoggerContext}
 */
public class Log4jv2LoggerContextFactory implements LoggerContextFactory {

    private static LoggerContext context = new Log4jv2LoggerContext();

    @Override
    public LoggerContext getContext(final String fqcn, final ClassLoader loader, final Object externalContext,
                                    final boolean currentContext) {
        return context;
    }

    @Override
    public LoggerContext getContext(final String fqcn, final ClassLoader loader, final Object externalContext,
                                    final boolean currentContext, final URI configLocation, final String name) {
        return context;
    }

    @Override
    public void removeContext(final LoggerContext removeContext) {
    }
}
