package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 *  @fileName :   test.Test.java
 *
 *	@version : 1.0
 *
 * 	@see { }
 *
 *	@author   :   fan
 *
 *	@since : JDK1.4
 *  
 *  Create date  : 2016年8月10日 下午6:26:49
 *  Last modified time :
 *	
 * 	Test or not : No
 *	Check or not: No
 *
 * 	The modifier :
 *	The checker	: 
 *	 
 *  @describe :
 *  ALL RIGHTS RESERVED,COPYRIGHT(C) FCH LIMITED 2016
*/

public class Test {
	private List allList;
	
	
    public Test() {
		super();
		this.allList = new ArrayList();
	}

	public static void main(String []args){
    	List list = new ArrayList();
    	for(int i = 0 ; i < 10 ; i++){
    		list.add(i);
    	}
    	Test t = new Test();
    	t.genList(list);
    }
    
    public void genList(List rule){
    	if(rule.size() == 0){
    		Map map = new HashMap();
    		map.put(rule, 1);
    		allList.add(map);
    	}else{
    		Map map = new HashMap();
    		rule.remove(0);
    		map.put(rule, 1);
    		allList.add(map);
    	}
    }
}
