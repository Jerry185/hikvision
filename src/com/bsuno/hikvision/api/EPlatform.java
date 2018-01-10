package com.bsuno.hikvision.api;

public enum EPlatform {  
    Linux("linux"),  
    Windows("windows"),
    Other("other"),
	Bit64("64"),
	Bit32("32");
	
    private EPlatform(String desc){  
        this.description = desc;  
    }  
      
    public String toString(){  
        return description;  
    }  
      
    private String description;  
} 