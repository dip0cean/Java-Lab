package me.osean.effective_java.chapter_01.item_05.spring_ioc.component_scan;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = ComponentScanConfig.class)
public class ComponentScanConfig {
}
