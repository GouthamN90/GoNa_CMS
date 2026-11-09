package com.gona.app.populator;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;


@Component
public class DelegateComponentFormPopulator {
	
	@Autowired
    private ApplicationContext context;
	
	private  Map<String, String> COMPONENT_TEMPLATE_POPULATOR_MAP = Map.of(
	        "BannerComponent", "bannerComponentFormPopulator"
	);
	
	public ComponentFormPopulator<?, ?> getPopulator(String componentType) {
		String populatorBeanName = COMPONENT_TEMPLATE_POPULATOR_MAP.get(componentType);
		if (populatorBeanName == null) {
			throw new IllegalArgumentException("No populator found for component type: " + componentType);
		}
		return context.getBean(populatorBeanName, ComponentFormPopulator.class);
	}

}
