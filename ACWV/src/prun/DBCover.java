package prun;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import mine.TreeNode;
import weka.core.Instance;
import weka.core.Instances;

/**
 * 
 * @fileName : prun.DBCover.java
 *
 * @version : 1.0
 *
 * @see { }
 *
 * @author : fan
 *
 * @since : JDK1.4
 * 
 *        Create date : 2016年9月5日 下午2:26:36 Last modified time :
 * 
 *        Test or not : No Check or not: No
 *
 *        The modifier : The checker :
 * 
 * @describe : ALL RIGHTS RESERVED,COPYRIGHT(C) FCH LIMITED 2016
 */

public class DBCover implements RulePrun {
	private Instances instance;
	/*
	 * (non-Javadoc)
	 * 
	 * @see prun.RulePrun#prun(java.util.List) 对规则进行裁剪，保留有用的规则
	 */

	@Override
	public List prun(List ruleList) {
		// TODO method stub
		List resultList = new ArrayList();
		for (int i = 0; i < instance.numInstances(); i++) {
			Instance ins = instance.instance(i);
//			boolean contained = false;
//			for (Object obj : resultList) {
//				Map map = (Map) obj;
//				Set key = map.keySet();
//				Iterator it = key.iterator();
//				if (it.hasNext()) {
//					List rule = (List) it.next();
//					if (isContains(ins, rule)) {
//						contained = true;
//						break;
//					}
//				}
//			}
//
//			if (contained) {
//				continue;
//			}
			
			for (Object obj : ruleList) {
				Map map = (Map) obj;
				Set key = map.keySet();
				Iterator it = key.iterator();
				if (it.hasNext()) {
					List rule = (List) it.next();
					if (isContains(ins, rule)) {
						if(!resultList.contains(map)){
						  resultList.add(map);
						}
						break;
					}
				}
			}
		}
		System.out.println(resultList.size());
		return resultList;
	}

	public DBCover(Instances instance) {
		super();
		this.instance = instance;
	}

	public boolean isContains(Instance instance, List rule) {

		boolean contains = true;
		for (Object obj : rule) {
			if (!((TreeNode) obj).containedBy(instance)) {
				contains = false;
			}
		}
		return contains;
	}
}
