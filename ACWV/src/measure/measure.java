package measure;

import java.util.Map;

import mine.Tree;
import weka.core.Instances;

/**
 * 
 *  @fileName :   measure.measure.java
 *
 *	@version : 1.0
 *
 * 	@see { }
 *
 *	@author   :   fan
 *
 *	@since : JDK1.4
 *  
 *  Create date  : 2016年9月7日 上午9:49:28
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

public interface measure {
		public double calculate(Map rule,Instances ins);
		public double calculate(Map rule,Tree tree);
}
