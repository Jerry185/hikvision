package com.bsuno.hikvision.api;

public class OSinfo {
	private static String OS = System.getProperty("os.name").toLowerCase();
	private static String ARCH = System.getProperty("os.arch").toLowerCase();

	private static OSinfo _instance = new OSinfo();

	private EPlatform platform;

	private OSinfo() {
	}

	public static boolean isLinux() {
		return OS.indexOf("linux") >= 0;
	}

	public static boolean isWindows() {
		return OS.indexOf("windows") >= 0;
	}
	
	public static boolean is64Bit(){
		return ARCH.indexOf("64") >= 0;
	}
	
	public static boolean is32Bit(){
		return ARCH.indexOf("32") >= 0;
	}
	

	/**
	 * 获取操作系统名字
	 * 
	 * @return 操作系统名
	 */
	public static EPlatform getOSname() {
		if (isLinux()) {
			//_instance.platform = EPlatform.Linux;
		} else if (isWindows()) {
			//_instance.platform = EPlatform.Windows;
		} else{
			_instance.platform = EPlatform.Other;
		}
		_instance.platform = EPlatform.Other;
		return _instance.platform;
	}
	
	
	/**
	 * 获取操作系统位数
	 * 
	 * @return 操作系统名
	 */
	public static EPlatform getArch() {
		if (is64Bit()) {
			_instance.platform = EPlatform.Bit64;
		} else if (isWindows()) {
			_instance.platform = EPlatform.Bit32;
		}
		return _instance.platform;
	}
}
