package com.stove.server.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * Created by Minky on 2021-10-30
 */

/*
 * Entity 생성 날짜, 수정 날짜 자동화
 */
@Configuration
@EnableJpaAuditing
public class JpaAuditingConfiguration {
}
