package com.sample.cloud.accounts.mapping;

import java.lang.reflect.Method;
import java.text.MessageFormat;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.servlet.mvc.condition.ProducesRequestCondition;
import org.springframework.web.servlet.mvc.condition.RequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * A subclass of RequestMappingHandlerMapping that checks for @Version
 * annotations and adds a Produces condition to the mapping.
 *
 * @author ednovoa
 *
 */
public class CustomRequestMappingHandler extends RequestMappingHandlerMapping {

	private static final String MEDIA_TYPE = "application/vnd.sample.account.v{0}+json";

	@Override
	protected RequestCondition<?> getCustomTypeCondition(Class<?> handlerType) {
		Version typeAnnotation = AnnotationUtils.findAnnotation(handlerType, Version.class);
		return this.createCondition(typeAnnotation);
	}

	@Override
	protected RequestCondition<?> getCustomMethodCondition(Method method) {
		Version methodAnnotation = AnnotationUtils.findAnnotation(method, Version.class);
		return this.createCondition(methodAnnotation);
	}

	private RequestCondition<?> createCondition(Version version) {
		return (version != null) ? new VersionRequestCondition(version.value()) : null;
	}

	/**
	 * Check for @Version on type and method levels.
	 */
	@Override
	protected RequestMappingInfo getMappingForMethod(Method method, Class<?> handlerType) {
		RequestMappingInfo info = super.getMappingForMethod(method, handlerType);
		Version methodAnnotation = AnnotationUtils.findAnnotation(method, Version.class);
		if (methodAnnotation != null) {
			info = this.acceptHeaderVersionInfo(info, methodAnnotation);
		} else {
			Version typeAnnotation = AnnotationUtils.findAnnotation(handlerType, Version.class);
			if (typeAnnotation != null) {
				info = this.acceptHeaderVersionInfo(info, typeAnnotation);
			}
		}
		return info;
	}

	/**
	 * Reads the value of @Version, converts it into a valid Media Type and adds
	 * a Produces condition to the method's RequestMapping.
	 *
	 * @param info
	 *            - the method's RequestMappingInfo
	 * @param version
	 *            - the supported API version
	 * @return a new RequestMappingInfo with an additional Produces condition
	 *         for the API version Media Type.
	 */
	private RequestMappingInfo acceptHeaderVersionInfo(RequestMappingInfo info, Version version) {
		String mediaTypes[] = new String[version.value().length];
		for (int i = 0; i < version.value().length; i++) {
			mediaTypes[i] = MessageFormat.format(MEDIA_TYPE, version.value()[i]);
		}
		RequestCondition<ProducesRequestCondition> customRequestCondition = new ProducesRequestCondition(mediaTypes);
		return new RequestMappingInfo(info, customRequestCondition);
	}
}
