package com.bsuno.hikvision.api;

import java.util.HashMap;
import java.util.Map;

import com.sun.jna.NativeLong;

/**
 * 全局缓存变量
 * 
 * @author bsuno
 *
 */
public class TempData {
	
	private TempData(){}
	
	private static TempData tempData = null;
	
	private Map<String, MyNativeLong> nativeLongMap;
	
	public static TempData getTempData(){
		if(tempData == null){
			tempData = new TempData();
			tempData.nativeLongMap = new HashMap<String, MyNativeLong>();
			return tempData;
		}
		return TempData.tempData;
	}
	
	/**
	 * 获取指定IP摄像头的预览句柄
	 * @param equipmentId
	 * @return
	 */
	public MyNativeLong getNativeLong(String equipmentId){
		return nativeLongMap.get(equipmentId);
	}
	
	/**
	 * 设置指定IP摄像头的句柄
	 * @param equipmentId
	 * @param lRealHandle
	 */
	public void setNativeLong(String equipmentId,NativeLong userId){		
		MyNativeLong nativelong = new MyNativeLong();
		nativelong.setlUserID(userId);
		nativeLongMap.put(equipmentId, nativelong);
	}
	
	/**
	 * 删除指定句柄
	 * @param equipmentId
	 */
	public void deleteNativeLong(String equipmentId){
		if(nativeLongMap.containsKey(equipmentId)){
			nativeLongMap.remove(equipmentId);
		}
	}

}
