package com.sample.cloud.accounts.mapping;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Convenience annotation for determining what API versions a Controller or
 * Controller Method supports. <br/>
 * For example, the following mappings are equivalent:
 *
 * <pre class="code">
 * &#064;Version("1")
 * &#064;RequestMapping("/items")
 * public List<Item> getItems()
 *
 * &#064;RequestMapping(value = "/items", produces = SampleMediaType.APPLICATION_API_V1_VALUE)
 * public List<Item> getItems()
 * </pre>
 *
 * @see org.springframework.web.bind.annotation.RequestMapping
 *      &#064;RequestMapping
 *
 * @author ednovoa
 *
 */
@Documented
@Target(value = { ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Version {

	String[] value();

}
