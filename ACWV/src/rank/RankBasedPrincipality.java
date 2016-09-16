package rank;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 *  @fileName :   rank.RankBasedPrincipality.java
 *
 *	@version : 1.0
 *
 * 	@see { }
 *
 *	@author   :   fan
 *
 *	@since : JDK1.4
 *  
 *  Create date  : 2016年9月14日 下午10:37:56
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

public class RankBasedPrincipality implements RuleRank{
	private List source;
	private List measure;
	@Override
	public List rank(List ruleList,List measureList) {
		// TODO method stub
		this.source = ruleList;
		this.measure = measureList;
		if(source.size()==0){
			return null;
		}
		quickSort(0, source.size()-1);
		return source;
	}
	
	public void quickSort(int start,int end){
		int i = start+1;
		int j = end;
		double key = (double) measure.get(start);
		if(start >= end){
			return;
		}
        while(true){
        	while((double)measure.get(j)>key){
        		j--;
        	}
        	while((double)measure.get(i)<key&&i<j){
        		i++;
        	}
        	if(i>=j){
        		break;
        	}
        	swap(measure,i,j);
        	swap(source,i,j);
        	if((double)measure.get(i)==key){
        		j--;
        	}else{
        		i++;
        	}
        }
        swap(measure,start,j);
        swap(source,start,j);
        if(start<i-1){
        	this.quickSort(start, i-1);
        }
        if(j+1<end){
        	this.quickSort(j+1, end);
        }
	}
	
	public void swap(List l , int i , int j){
		Object obj1 = l.get(i);
		Object obj2 = l.get(j);
		l.set(i, obj2);
		l.set(j, obj1);
	}
	
	public List getSource() {
		return source;
	}

	public void setSource(List source) {
		this.source = source;
	}

	public List getMeasure() {
		return measure;
	}

	public void setMeasure(List measure) {
		this.measure = measure;
	}

	public static void main(String []args){
		List<Double> l1 = new ArrayList();
		List<Double> l2 = new ArrayList();
		l1.add(2.0);
		l1.add(1.0);
		l1.add(7.0);
		l1.add(5.0);
		l1.add(6.0);
		
		l2.add(2.0);
		l2.add(1.0);
		l2.add(7.0);
		l2.add(5.0);
		l2.add(6.0);
		
		RankBasedPrincipality rp = new RankBasedPrincipality();
		rp.rank(l1, l2);
		List l = rp.getSource();
		for(Object i:l ){
			System.out.println(i);
		}
	}
}
