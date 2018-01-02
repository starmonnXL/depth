package com.depth.cms.commons.util;

import java.text.SimpleDateFormat;

public enum CommonConvertType {
	String() {
		@Override
		public Object getValue(java.lang.String value) throws Exception{
			return value;
		}

		@Override
		public CommonConvertType setType(Class<?> type) {
			return null;
		}
	},
	Integer() {
		@Override
		public Object getValue(java.lang.String value) throws Exception {
			if(value==null ||"".equals(value))return null;
			return java.lang.Integer.parseInt(value);
		}

		@Override
		public CommonConvertType setType(Class<?> type) {
			return null;
		}
	},
	Double() {
		@Override
		public Object getValue(java.lang.String value) throws Exception {
			if(value==null ||"".equals(value))return null;
			return java.lang.Double.parseDouble(value);
		}

		@Override
		public CommonConvertType setType(Class<?> type) {
			return null;
		}
	},
	Float() {
		@Override
		public Object getValue(java.lang.String value) throws Exception {
			if(value==null ||"".equals(value))return null;
			return java.lang.Float.parseFloat(value);
		}

		@Override
		public CommonConvertType setType(Class<?> type) {
			return null;
		}
	},
	Long() {
		@Override
		public Object getValue(java.lang.String value) throws Exception {
			if(value==null ||"".equals(value))return null;
			return java.lang.Long.parseLong(value);
		}

		@Override
		public CommonConvertType setType(Class<?> type) {
			return null;
		}
	},
	Date() {
		@Override
		public Object getValue(java.lang.String value) throws Exception {
			if(value==null ||"".equals(value))return null;
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd  hh:mm:ss");
			java.util.Date date = null;
			date = format.parse(value);
			return date;
		}

		@Override
		public CommonConvertType setType(Class<?> type) {
			return null;
		}
	},
	help() {
		@Override
		public Object getValue(java.lang.String value) {
			return null;
		}

		@Override
		public CommonConvertType setType(Class<?> type) {
			if (type == java.lang.String.class) {
				return String;
			} else if (type == java.lang.Integer.class) {
				return Integer;
			} else if (type == java.lang.Float.class) {
				return Float;
			} else if (type == java.lang.Long.class) {
				return Long;
			} else if (type == java.util.Date.class) {
				return Date;
			}else if (type == java.lang.Double.class) {
				return Double;
			} else {
				return null;
			}
		}
	};
	public abstract Object getValue(java.lang.String value) throws Exception;
	public abstract CommonConvertType setType(Class<?> type) throws Exception;
}