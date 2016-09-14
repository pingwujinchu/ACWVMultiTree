package rank;

import java.util.List;

/**
 * 
 *  @fileName :   rank.RuleRank.java
 *
 *	@version : 1.0
 *
 * 	@see { }
 *
 *	@author   :   fan
 *
 *	@since : JDK1.4
 *  
 *  Create date  : 2016年9月14日 下午3:25:55
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

public interface RuleRank {
     /**
      * 該接口可以對規則進行排序
     * @param ruleList
     * @return
     */
    public List rank(List ruleList);
}
