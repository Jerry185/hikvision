package com.bsuno.hikvision.api;
import com.sun.jna.NativeLong;

/**
 * 摄像头控制
 * 
 * @author bsuno
 *
 */
public class Control {
	
	private static HCNetSDK hCNetSDK = LoginHK.hCNetSDK;
	
	/**
	 * 获取摄像handle
	 * @param equipmentId
	 * @return
	 */
	public static NativeLong getHandle(String equipmentId){
		//获取ip对应摄像头的句柄
		MyNativeLong nativeLong = TempData.getTempData().getNativeLong(equipmentId);
		if (nativeLong == null) {
			return null;
		}
		
		return nativeLong.getlUserID();
	}
	/**
	 * 云台控制<br/>
	 * 云台控制的方式为调用该方法摄像头便会一直执行该操作,直到该操作接收到"停止"指令及(iStop)参数
	 * 
	 * @param ip 摄像头ip
	 * @param iCommand 控制指令
	 * @param iSpeed 云台运行速度 
	 * @param iStop 是否为停止操作
	 * @return
	 */
	public static boolean cloudControl(NativeLong userid, NativeLong lChannel,NativeLong lPreviewHandle, CloudCode iCommand, CloudCode iSpeed, CloudCode iStop) {
		//判断是否为停止操作
		if (iSpeed.getKey() == 1) {
			return hCNetSDK.NET_DVR_PTZControl_Other(userid, lChannel,iCommand.getKey(), iStop.getKey());
			//return hCNetSDK.NET_DVR_PTZControl(lPreviewHandle,iCommand.getKey(), iStop.getKey());
		}
		boolean success=hCNetSDK.NET_DVR_PTZControlWithSpeed_Other(userid, lChannel, iCommand.getKey(), iStop.getKey(),iSpeed.getKey());
		//boolean success=hCNetSDK.NET_DVR_PTZControlWithSpeed(lPreviewHandle, iCommand.getKey(), iStop.getKey(),iSpeed.getKey());
		return success;
	}
	
}