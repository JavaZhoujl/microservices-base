package com.msc.microservices.common.logging;


import com.msc.microservices.common.consts.StringConst;
import com.msc.microservices.common.util.LocalDateTimeUtil;
import com.msc.microservices.common.util.OSUtil;
import com.msc.microservices.common.util.ObjectMapperUtil;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.appender.RollingRandomAccessFileAppender;
import org.apache.logging.log4j.core.appender.rolling.CronTriggeringPolicy;
import org.apache.logging.log4j.core.appender.rolling.DirectWriteRolloverStrategy;
import org.apache.logging.log4j.core.appender.rolling.RolloverStrategy;
import org.apache.logging.log4j.core.appender.rolling.TriggeringPolicy;
import org.apache.logging.log4j.core.async.AsyncLoggerConfig;
import org.apache.logging.log4j.core.config.AppenderRef;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.LoggerConfig;
import org.apache.logging.log4j.core.layout.PatternLayout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;

/**
 * 通用业务日志
 *
 * @author zjl
 */
public final class BusinessLogger {
    /**
     * 通用业务日志名称
     */
    private static final String BUSINESS_LOGGER_NAME = "businessLogger";
    /**
     * 防止没有设置project.name或者根本不需要这个日志时也去生成日志文件目录,目前采用方案是输出到std日志中
     */
    private static final boolean SHOULD_INIT;
    private static final boolean LOG4J2_PRESENT;
    private static final String BUSINESS_LOG_ROOT;
    /**
     * slf4j Logger接口
     */
    private final Logger logger;

    static {
        // windows操作系统
        if (OSUtil.isWindows()) {
            BUSINESS_LOG_ROOT = "C:\\wwwlogs\\business\\";
        } else {
            BUSINESS_LOG_ROOT = "/wwwlogs/business/";
        }
        if (StringUtils.isNotBlank(StringConst.PROJECT_NAME)) {
            SHOULD_INIT = true;
        } else {
            SHOULD_INIT = false;
        }
        boolean tmp = false;
        try {
            Class.forName("org.apache.logging.log4j.core.LoggerContext");
            tmp = true;
        } catch (ClassNotFoundException e) {
            // 忽略
        }
        LOG4J2_PRESENT = tmp;
    }

    /**
     * 通用业务日志静态实例类,必须放在static代码块后面保证后执行
     */
    private static final BusinessLogger BUSINESS_LOGGER = new BusinessLogger();

    private BusinessLogger() {
        logger = createLogger();
    }

    public static BusinessLogger getInstance() {
        return BUSINESS_LOGGER;
    }

    /**
     * 记录业务日志
     *
     * @param context 业务内容
     */
    public void log(Object context) {
        log("business log", context);
    }

    /**
     * 记录业务日志
     *
     * @param message 自定义message
     * @param context 业务内容
     */
    public void log(String message, Object context) {
        BaseLog<Object> baseLog = new BaseLog<>();
        baseLog.setMessage(message);
        baseLog.setLevel(LevelEnum.INFO.getLevel());
        baseLog.setLevelName(LevelEnum.INFO.getLevelName());
        baseLog.setChannel(Channel.BUSINESS);
        baseLog.setContext(context);
        baseLog.setDatetime(LocalDateTimeUtil.getMicroSecondFormattedNow());
        try {
            logger.info(ObjectMapperUtil.getSnakeObjectMapper().writeValueAsString(baseLog));
        } catch (Throwable th) {
            // 忽略
        }
    }

    private Logger createLogger() {
        if (SHOULD_INIT && LOG4J2_PRESENT) {
            // 一定要用false,否则使用SLF4J获取的LoggerContext与全局的LoggerContext不一致
            LoggerContext context = (LoggerContext) LogManager.getContext(false);
            Configuration configuration = context.getConfiguration();
            final Layout layout = PatternLayout.newBuilder()
                    .withPattern("%m%n")
                    .withConfiguration(configuration)
                    .withCharset(StandardCharsets.UTF_8)
                    .withAlwaysWriteExceptions(false)
                    .withNoConsoleNoAnsi(true)
                    .build();
            final TriggeringPolicy policy = CronTriggeringPolicy.createPolicy(configuration, null, "0 0 0 * * ?");
            // directWrite策略
            RolloverStrategy directWriteRolloverStrategy = DirectWriteRolloverStrategy.newBuilder()
                    .withMaxFiles("10")
                    .withConfig(configuration)
                    .build();
            String name = "businessFile";
            final Appender appender = RollingRandomAccessFileAppender.newBuilder()
                    .withFilePattern(BUSINESS_LOG_ROOT + StringConst.PROJECT_NAME + "_%d{yyyy-MM-dd}.log")
                    .withAppend(true)
                    .withName(name)
                    .setConfiguration(configuration)
                    .withImmediateFlush(true)
                    .withPolicy(policy)
                    .withStrategy(directWriteRolloverStrategy)
                    .withLayout(layout)
                    .build();
            appender.start();
            // 新增json日志appender
            configuration.addAppender(appender);
            AppenderRef appenderRef = AppenderRef.createAppenderRef(name, Level.INFO, null);
            LoggerConfig loggerConfig = AsyncLoggerConfig.createLogger("false", "info", BUSINESS_LOGGER_NAME, "false", new AppenderRef[]{appenderRef}, null, configuration, null);
            // appenderRef添加还不够，必须手动添加appender
            loggerConfig.addAppender(appender, Level.INFO, null);
            loggerConfig.start();
            configuration.addLogger(BUSINESS_LOGGER_NAME, loggerConfig);
            context.updateLoggers();
        }
        // TODO 也许以后会兼容logback等其他日志方案
        return LoggerFactory.getLogger(BUSINESS_LOGGER_NAME);
    }
}
