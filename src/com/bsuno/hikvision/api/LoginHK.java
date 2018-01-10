package com.bsuno.hikvision.api;

import org.apache.log4j.Logger;

import com.sun.jna.Native;
import com.sun.jna.NativeLong;

/**
 * 登录到摄像头
 * 
 * @author bsuno
 *
 */
public class LoginHK {
	
	/**
	 * 全局HCNetSDK对象
	 */
	public static HCNetSDK hCNetSDK;
	
	private static Logger log = Logger.getLogger(LoginHK.class);
	
	/**
	 * 设备参数信息
	 */
	private HCNetSDK.NET_DVR_DEVICEINFO_V30 m_strDeviceInfo;
	
	
	/**
	 * 用户参数
	 */
	private HCNetSDK.NET_DVR_CLIENTINFO m_strClientInfo;
	
	/**
	 * 用户句柄
	 */
	private NativeLong lUserID;
	
	/**
	 * 预览句柄
	 */
	private NativeLong lPreviewHandle;
	
	private static LoginHK instance;
	
	
	public LoginHK(){
		try{
			hCNetSDK= (HCNetSDK) Native.loadLibrary("hcnetsdk",
		            HCNetSDK.class);
		}catch(UnsatisfiedLinkError e){
			log.error("海康云平加载动态库失败！"+e.getMessage());
		}
	}
	
	
	public synchronized static LoginHK getInstance() {
		if (instance == null) {
			instance = new LoginHK();
		}
		return instance;
	}

	
	/**
	 * 登录到设备
	 */
	public long doLogin(VidiconConfig config){
		boolean initSuc = hCNetSDK.NET_DVR_Init();
		if (initSuc != true){
			log.error("hCNetSDK初始化失败!");
		}
	      
		m_strDeviceInfo = new HCNetSDK.NET_DVR_DEVICEINFO_V30();
		//获取用户句柄
		lUserID = hCNetSDK.NET_DVR_Login_V30(config.getAddr(), (short)config.getPort(), config.getUsername(), config.getPassword(), m_strDeviceInfo);
		
		long userID = lUserID.longValue();
		return userID;
	}
	
	/**
	 * 获取用户句柄
	 * 
	 * @return
	 */
	public NativeLong user() {
		return lUserID;
	}
	
	/**
	 * 退出登录
	 */
	public boolean logout(String equipmentId){
        MyNativeLong nativelong = TempData.getTempData().getNativeLong(equipmentId);
        if(nativelong!=null){
        	  boolean success = hCNetSDK.NET_DVR_Logout_V30(nativelong.getlUserID());
              if(success){
              	TempData.getTempData().deleteNativeLong(equipmentId);
                  return true;
              }
              log.error("海康设备退出失败！equipmentid:"+equipmentId);
              return false;
        }else{
        	return false;
        }
	}
	
	/**
	 * 获取预览句柄
	 * 
	 * @return
	 */
	public NativeLong play(NativeLong userid,NativeLong channelId){
        
        m_strClientInfo = new HCNetSDK.NET_DVR_CLIENTINFO();
        m_strClientInfo.lChannel = channelId;
        
        lPreviewHandle = hCNetSDK.NET_DVR_RealPlay_V30(userid, m_strClientInfo, null, null, true);
        log.info("获取预览对象："+lPreviewHandle.intValue());
        
        log.info("获取预览对象状态："+hCNetSDK.NET_DVR_GetLastError());
      
        return lPreviewHandle;
	}
	
}
