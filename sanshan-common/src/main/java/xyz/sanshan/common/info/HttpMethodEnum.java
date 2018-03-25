

package xyz.sanshan.common.info;

import java.util.HashMap;
import java.util.Map;

public enum HttpMethodEnum {

	GET, HEAD, POST, PUT, PATCH, DELETE, OPTIONS, TRACE,ALL;


	private static final Map<String, HttpMethodEnum> mappings = new HashMap<String, HttpMethodEnum>(9);

	static {
		for (HttpMethodEnum httpMethod : values()) {
			mappings.put(httpMethod.name(), httpMethod);
		}
	}

	/**
	 *
	 * @param method
	 * @return
	 */
	public static HttpMethodEnum resolve(String method) {
		return (method != null ? mappings.get(method) : null);
	}


	public boolean matches(String method) {
		return (this == resolve(method));
	}

}