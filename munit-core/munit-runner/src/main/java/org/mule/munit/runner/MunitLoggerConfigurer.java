/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.munit.runner;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.appender.RollingFileAppender;
import org.apache.logging.log4j.core.appender.rolling.DefaultRolloverStrategy;
import org.apache.logging.log4j.core.appender.rolling.RolloverStrategy;
import org.apache.logging.log4j.core.appender.rolling.TimeBasedTriggeringPolicy;
import org.apache.logging.log4j.core.appender.rolling.TriggeringPolicy;
import org.apache.logging.log4j.core.config.AbstractConfiguration;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.DefaultConfiguration;
import org.apache.logging.log4j.core.config.LoggerConfig;
import org.apache.logging.log4j.core.layout.PatternLayout;
import org.apache.logging.log4j.core.LoggerContext;

import java.io.Serializable;
import java.util.zip.Deflater;

/**
 * Created by damiansima on 12/2/14.
 */
public class MunitLoggerConfigurer {
    private static final String PATTERN_LAYOUT = "%-5p %d [%t] %c: %m%n";

    public static void configureFileLogger(String filePath, String fileName) {
        LoggerContext ctx = (LoggerContext) LogManager.getContext(false);
        Configuration logConfiguration = ctx.getConfiguration();

        RollingFileAppender appender = createRollingFileAppender(String.format(filePath, fileName), "'.'%d{yyyy-MM-dd}", "defaultFileAppender", new DefaultConfiguration());
        doAddAppender(logConfiguration, appender);

        ctx.updateLoggers();
    }

    private static RollingFileAppender createRollingFileAppender(String logFilePath, String filePattern, String appenderName, Configuration configuration) {
        TriggeringPolicy triggeringPolicy = TimeBasedTriggeringPolicy.createPolicy("1", "true");
        RolloverStrategy rolloverStrategy = DefaultRolloverStrategy.createStrategy("30", "1", null, String.valueOf(Deflater.NO_COMPRESSION), configuration);

        return RollingFileAppender.createAppender(logFilePath,
                logFilePath + filePattern,
                "true",
                appenderName,
                "true",
                null, null,
                triggeringPolicy,
                rolloverStrategy,
                createLayout(configuration),
                null, null, null, null,
                configuration);
    }

    private static Layout<? extends Serializable> createLayout(Configuration configuration) {
        return PatternLayout.createLayout(PATTERN_LAYOUT, configuration, null, null, true, false, null, null);
    }


    private static void doAddAppender(Configuration logConfiguration, Appender appender) {
        appender.start();
        logConfiguration.addAppender(appender);
        LoggerConfig lc = ((AbstractConfiguration) logConfiguration).getRootLogger();
        lc.addAppender(appender, Level.INFO, null);

    }
}
