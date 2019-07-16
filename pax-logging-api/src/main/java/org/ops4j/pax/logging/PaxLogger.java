/*
 * Copyright 2005 Niclas Hedhman.
 *
 * Licensed  under the  Apache License,  Version 2.0  (the "License");
 * you may not use  this file  except in  compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed  under the  License is distributed on an "AS IS" BASIS,
 * WITHOUT  WARRANTIES OR CONDITIONS  OF ANY KIND, either  express  or
 * implied.
 *
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.ops4j.pax.logging;

/**
 * Main pax-logging interface for loggers to interact with any logging system.
 */
public interface PaxLogger {

    int LEVEL_TRACE = 0;
    int LEVEL_DEBUG = 1;
    int LEVEL_INFO = 2;
    int LEVEL_WARNING = 3;
    int LEVEL_ERROR = 4;

    boolean isTraceEnabled();
    boolean isDebugEnabled();
    boolean isInfoEnabled();
    boolean isWarnEnabled();
    boolean isErrorEnabled();
    boolean isFatalEnabled();

    // logging methods with marker support

    boolean isTraceEnabled(PaxMarker marker);
    boolean isDebugEnabled(PaxMarker marker);
    boolean isInfoEnabled(PaxMarker marker);
    boolean isWarnEnabled(PaxMarker marker);
    boolean isErrorEnabled(PaxMarker marker);
    boolean isFatalEnabled(PaxMarker marker);

    void trace(String message, Throwable t);
    void debug(String message, Throwable t);
    void inform(String message, Throwable t);
    void warn(String message, Throwable t);
    void error(String message, Throwable t);
    void fatal(String message, Throwable t);

    // logging methods accepting "fqcn" that allows backend framework to analyze stacktrace
    // when searching for actual Class/Method/File/LineNumber to log using certain
    // patterns (like %F, %L in Log4J).

    void trace(String message, Throwable t, String fqcn);
    void debug(String message, Throwable t, String fqcn);
    void inform(String message, Throwable t, String fqcn);
    void warn(String message, Throwable t, String fqcn);
    void error(String message, Throwable t, String fqcn);
    void fatal(String message, Throwable t, String fqcn);

    // logging methods with marker support

    void trace(PaxMarker marker, String message, Throwable t);
    void debug(PaxMarker marker, String message, Throwable t);
    void inform(PaxMarker marker, String message, Throwable t);
    void warn(PaxMarker marker, String message, Throwable t);
    void error(PaxMarker marker, String message, Throwable t);
    void fatal(PaxMarker marker, String message, Throwable t);

    void trace(PaxMarker marker, String message, Throwable t, String fqcn);
    void debug(PaxMarker marker, String message, Throwable t, String fqcn);
    void inform(PaxMarker marker, String message, Throwable t, String fqcn);
    void warn(PaxMarker marker, String message, Throwable t, String fqcn);
    void error(PaxMarker marker, String message, Throwable t, String fqcn);
    void fatal(PaxMarker marker, String message, Throwable t, String fqcn);

    /**
     * <p>Returns numerical log level associated with this logger. Higher values mean more <em>important</em>
     * levels (as in {@link org.ops4j.pax.logging.spi.PaxLevel}). Only these constants should be returned
     * (in increasing importance/severity):<ul>
     *     <li>{@link PaxLogger#LEVEL_TRACE}</li>
     *     <li>{@link PaxLogger#LEVEL_DEBUG}</li>
     *     <li>{@link PaxLogger#LEVEL_INFO}</li>
     *     <li>{@link PaxLogger#LEVEL_WARNING}</li>
     *     <li>{@link PaxLogger#LEVEL_ERROR}</li>
     * </ul></p>
     * @return
     */
    int getLogLevel();

    /**
     * Returns the name of the logger - usually in dot-separated format.
     * @return
     */
    String getName();

    /**
     * {@link PaxContext} of this logger that gives access to thread-bound MDC context.
     * @return
     */
    PaxContext getPaxContext();

}
