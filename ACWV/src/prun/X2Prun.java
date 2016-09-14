package prun;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import mine.Tree;
import mine.TreeNode;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;

/**
 * 
 *  @fileName :   prun.X2Prun.java
 *
 *	@version : 1.0
 *
 * 	@see { }
 *
 *	@author   :   fan
 *
 *	@since : JDK1.4
 *  
 *  Create date  : 2016��9��5�� ����10:07:24
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

public class X2Prun implements RulePrun{
	double minbound ;
	Instances ins ;
	
	public X2Prun(double minbound, Instances ins) {
		super();
		this.minbound = minbound;
		this.ins = ins;
	}

	public List prun(List ruleList){
		List resultList = new ArrayList();
		for(Object obj : ruleList){
			Map map = (Map)obj;
			double x2 = calculateX2(map);
			if(x2>=minbound){
				resultList.add(map);
			}
		}
		
		return resultList;
	}
	
	/**
	 * @param map
	 * @return
	 * ʹ��x2���й���ü�
	 */
	
	public double calculateX2(Map map){
		double x2 =0;
		int expect = 0;
		int observe = 0;
		Set key = map.keySet();
		Iterator it = key.iterator();
		while(it.hasNext()){
			List rule = (List)it.next();
	        for(int i = 0 ; i < ins.numInstances() ; i++){
	        	Instance instance = ins.instance(i);
	        	if(isContains(instance, rule)){
	        		expect++;
	        		if((int)(map.get(rule))==instance.classValue()){
	        			observe++;
	        		}
	        	}
	        }
		}
		return (double)(observe-expect)*(observe-expect)/expect;
	}
	
	/**
	 * @param instance
	 * @param rule
	 * @return
	 * �жϹ���ǰ���Ƿ���ʵ����
	 */
	
	public boolean isContains(Instance instance,List rule){
		
		boolean contains = true;
		for(Object obj : rule){
			if(!((TreeNode)obj).containedBy(instance)){
				contains = false;
			}
		}
		return contains;
	}

}
