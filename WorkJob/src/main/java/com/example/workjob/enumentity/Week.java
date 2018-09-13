package com.example.workjob.enumentity;

public enum Week {
	Monday("星期一",1),
	Tuesday("星期二",1),
	Wednesday("星期三",1),
	Thursday("星期四",1),
	Friday("星期五",1),
	Saturday("星期六",1),
	Sunday("星期日",1);
	
	 // 成员变量  
    private String name;  
    private int index;
	
	private Week(String name, int index){
		 this.name = name;  
	     this.index = index;
	}
	
	// 普通方法  
    public static String getName(int index) {  
        for (Week w: Week.values()) {  
            if (w.getIndex() == index) {  
                return w.name;  
            }  
        }  
        return null;  
    }  
    

    public String getName() {  
        return name;  
    }  
    public void setName(String name) {  
        this.name = name;  
    }  
    public int getIndex() {  
        return index;  
    }  
    public void setIndex(int index) {  
        this.index = index;  
    }  

}
