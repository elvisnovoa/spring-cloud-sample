package com.sample.cloud.accounts.mapping;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.web.servlet.mvc.condition.RequestCondition;

/**
 * Matches requests based on our custom MediaType in the Accept header.
 *
 * @author ednovoa
 *
 */
public class VersionRequestCondition implements RequestCondition<VersionRequestCondition> {

	private final Set<String> versions;
	private final Pattern pattern = Pattern.compile("^application\\/vnd.sample.account.v+([0-9\\.]+)\\+json");

	public VersionRequestCondition(String... versions) {
		this(Arrays.asList(versions));
	}

	public VersionRequestCondition(Collection<String> versions) {
		this.versions = Collections.unmodifiableSet(new HashSet<String>(versions));
	}

	@Override
	public VersionRequestCondition combine(VersionRequestCondition other) {
		Set<String> allVersions = new LinkedHashSet<String>(this.versions);
		allVersions.addAll(other.versions);
		return new VersionRequestCondition(allVersions);
	}

	@Override
	public VersionRequestCondition getMatchingCondition(HttpServletRequest request) {
		String accept = request.getHeader("Accept");

		Matcher matcher = this.pattern.matcher(accept);

		if (matcher.find()) {
			for (String version : this.versions) {
				if (matcher.group(1).equals(version))
					return this;
			}
		}

		return null;
	}

	@Override
	public int compareTo(VersionRequestCondition other, HttpServletRequest request) {
		return CollectionUtils.removeAll(other.versions, this.versions).size();
	}
}
