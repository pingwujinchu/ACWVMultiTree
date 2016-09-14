package measure;

import java.util.Map;

import mine.Tree;
import weka.core.Instances;

/**
 * 
 *  @fileName :   measure.Completeness.java
 *
 *	@version : 1.0
 *
 * 	@see { }
 *
 *	@author   :   fan
 *
 *	@since : JDK1.4
 *  
 *  Create date  : 2016年9月7日 上午9:55:52
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

public class Completeness implements measure{

	@Override
	public double calculate(Map rule, Instances ins) {
		// TODO method stub
		return 0;
	}

	@Override
	public double calculate(Map rule, Tree tree) {
		double completeness = 0;
		return 0;
	}

}
