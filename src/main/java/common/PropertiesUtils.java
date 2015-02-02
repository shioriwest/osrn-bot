package common;

import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

public class PropertiesUtils {

	public static final Properties TOKEN = new Properties("token");

	private static final Logger logger = Logger.getLogger(PropertiesUtils.class);

	public static String getValue(String key) {
		return TOKEN.getValue(key);
	}

	public static class Properties {
		protected String baseName;
		protected Locale locale;

		public Properties(String baseName) {
			this(baseName, Locale.getDefault());
		}

		public Properties(String baseName, Locale locale) {
			this.baseName = baseName;
			this.locale = locale;
		}

		public String getValue(String key) {
			ResourceBundle bundle = ResourceBundle.getBundle(baseName, locale);
			try {
				return bundle.getString(key);
			} catch (Exception e) {
				if (logger.isDebugEnabled()) {
					logger.debug("", e);
				}
				return null;
			}
		}

		public Integer getIntegerValue(String key) {
			String str = getValue(key);
			return str == null ? null : Integer.valueOf(str);
		}

		public Locale getLocale() {
			return this.locale;
		}
	}
}
