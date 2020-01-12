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
package org.ops4j.pax.logging;

/**
 * Interfaces for loggers that delegate to {@link PaxLogger}, so they can switch to non fallback {@link PaxLogger}
 * when {@link PaxLoggingManager} becomes available.
 */
public interface PaxLoggingManagerAwareLogger {

    /**
     * Configures a {@link PaxLoggingManager} that from now on can be used to
     * {@link PaxLoggingManager#getLogger(String, String) obtain a non-fallback logger} that given logger
     * is delegating to.
     *
     * This method is called in activator of pax-logging-api to ensure that loggers that may already been created
     * from pax-logging adjusted facades/factories actually delegate to real {@link PaxLoggingService}. This is
     * especially visible in pax-exam tests, where pax-logging-api's SLF4J classes are already used by pax-exam
     * itself before even starting OSGi framework.
     */
    void setPaxLoggingManager(PaxLoggingManager manager);

}
