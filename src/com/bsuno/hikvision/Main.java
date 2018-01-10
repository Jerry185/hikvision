package com.bsuno.hikvision;

import com.bsuno.hikvision.api.CloudCode;
import com.bsuno.hikvision.api.Control;
import com.bsuno.hikvision.api.LoginHK;
import com.bsuno.hikvision.api.VidiconConfig;
import com.sun.jna.NativeLong;

public class Main {
	/**
	 * 调用云台摄像头
	 * @return
	*/
	public static boolean cloudControl(NativeLong nativelong,String option){
		boolean success = false;
	    NativeLong channel=new NativeLong(33);
	    
	    CloudCode iCommand=null;
		//向海康云平发送指令
		switch(option){
		    case "left":
		    	iCommand=CloudCode.PAN_LEFT;
		        break;
			case "right":
				iCommand=CloudCode.PAN_RIGHT;
				break;
			case "up":
				iCommand=CloudCode.TILT_UP;
				break;
			case "down":
				iCommand=CloudCode.TILT_DOWN;
				break;
			case "near":
				iCommand=CloudCode.FOCUS_NEAR;
				break;
			case "far":
				iCommand=CloudCode.FOCUS_FAR;
				break;
		}
		
		if(iCommand==null){
			return false;
		}
		
		//开始调用
		success = Control.cloudControl(nativelong,channel,null,iCommand, CloudCode.SPEED_LV6, CloudCode.START);
		
		if(!success){
			return false;
		}
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//调用结束
		Control.cloudControl(nativelong,channel, null,iCommand, CloudCode.SPEED_LV6, CloudCode.END);
		return true;
	}
	
	
	public static void main(String[] args) {
		VidiconConfig config = new VidiconConfig("", "", "10.1.250.8", 8000, "admin","password","",33);
		//海康云台登录
		long userID = LoginHK.getInstance().doLogin(config);
		
		if(userID == -1){
			System.out.println("login fail...");
		} else {
			NativeLong nativelong = Control.getHandle(config.getId());
			if(nativelong==null){
				return;
			}
			//向海康云平发送指令
			cloudControl(nativelong,"left");
		}
	}	
}
