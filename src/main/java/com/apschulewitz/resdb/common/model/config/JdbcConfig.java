package com.apschulewitz.resdb.common.model.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component
@ComponentScan(basePackages = { "com.apschulewitz.resdb.common.model" })
public class JdbcConfig {
}
