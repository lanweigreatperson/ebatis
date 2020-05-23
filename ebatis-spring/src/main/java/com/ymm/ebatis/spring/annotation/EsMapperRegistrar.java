package com.ymm.ebatis.spring.annotation;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
class EsMapperRegistrar implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata metadata, BeanDefinitionRegistry registry) {
        AnnotationAttributes mapperAttributes =
                AnnotationAttributes.fromMap(metadata.getAnnotationAttributes(EnableEsMapper.class.getName(), false));

        new EsMapperBeanDefinitionScanner(registry, getClusterName(mapperAttributes)).scan(getPackagesToScan(mapperAttributes, metadata));
    }

    private String getClusterName(AnnotationAttributes mapperAttributes) {
        return mapperAttributes.getString("clusterRouter");
    }

    private String[] getPackagesToScan(AnnotationAttributes attributes, AnnotationMetadata metadata) {
        Set<String> packages = new HashSet<>();
        Stream.of(attributes.getStringArray("value")).filter(StringUtils::isNotBlank)
                .collect(Collectors.toCollection(() -> packages));

        Stream.of(attributes.getStringArray("basePackages")).filter(StringUtils::isNotBlank)
                .collect(Collectors.toCollection(() -> packages));

        if (packages.isEmpty()) {
            return new String[]{metadata.getClassName().substring(0, metadata.getClassName().lastIndexOf('.'))};
        } else {
            return packages.toArray(new String[0]);
        }
    }
}
