
package mine;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import prun.DBCover;
import prun.RulePrun;
import prun.X2Prun;
import rank.RankBasedPrincipality;
import weka.associations.LabeledItemSet;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ArffLoader;

public class ACWV extends Classifier{
	public double minsup = 0.01;			//支持度阈值
	public double minconv = 1.1;			//置信度阈值
	public int ruleNumLimit = 100000;       //规则条数限制
	double[] classValue;                    //每个类的数值属性
	int[] classCount;						//每个类对应的实例数目
	double [] voted;					    //投票结果
	int numClass;							//数据集所具有的类的数目
	static int buildCount = 0;
	Instances m_instances;				
	Instances m_onlyclass;					//只有类标签的数据实例
	FastVector m_hashtables = new FastVector();  
	public Instances m_onlyClass;
	int clIndex=0;
	int numAttr=0;
	CCFP fp;
	Tree[] t;                       //多棵树，每个类型会有一棵树
	FastVector []headertable;
	private int necSupport, necMaxSupport;		// minimum support
	int attrvalue[];//store number of values each attribute can be
	
	List ruleList;
	List pincipalityList;

	public void buildClassifier (Instances instances)throws Exception
	{ 
		ruleList = new ArrayList();
		pincipalityList = new ArrayList();
		double upperBoundMinSupport=1;
		buildCount++;
		// m_instances does not contain the class attribute
		m_instances = LabeledItemSet.divide(instances, false);  //只保留数据

		// m_onlyClass contains only the class attribute
		m_onlyClass = LabeledItemSet.divide(instances, true);   //只保留类属性
		Calculation cal = new Calculation();
		cal.calSupport(minsup, upperBoundMinSupport, instances.numInstances());    //计算出所有的支持度等
		necSupport = cal.getNecSupport();
		attrvalue = cal.calAttrValue(m_instances);
		numClass=m_onlyClass.numDistinctValues(0);//number of classValue
		numAttr = m_instances.numAttributes();
		t = new Tree[numClass];   //每个类构建一颗树
		if(buildCount>1){		
			fp = new CCFP(m_instances, m_onlyClass,minsup, minconv, necSupport, ruleNumLimit, attrvalue);
			headertable = fp.buildHeaderTable(numClass, necSupport);   //FP树的Headtable
			
			t = fp.buildTree(headertable);
			
//			RulePrun rulePrun = new X2Prun(0.5,instances);
//			RulePrun rulePrun = new DBCover(instances);
			for(int i = 0 ; i < numClass ; i++){
//				t[i].countnode();
				List tem  = new ArrayList();
				t[i].genAllRules(t[i].root, tem,i);
				List allRuleList = t[i].getAllRuleList();
				ruleList.addAll(allRuleList);
				List pincipality = t[i].getPrincipalityList();
				pincipalityList.addAll(pincipality);
//				t[i].countnode();
			}
//			RankBasedPrincipality rp = new RankBasedPrincipality();
//			ruleList = rp.rank(ruleList, pincipalityList);
//			ruleList = rulePrun.prun(ruleList);
			
		}
	}

	public double classifyInstance(Instance instance)
	{	
		int max = 0;
		if(buildCount>1){
			double[] vote = new double[numClass];
//			vote = fp.vote(instance, headertable);
			vote = vote(instance);
			voted = vote;
//			System.out.println(vote[0]+" "+vote[1]);
			max = findMax(vote);
		}
		return max;
	}

	public double[]getVoted(){
		return voted;
	}
	
	public double[] vote(Instance instance){
		double []result = new double[numClass];
		int []len = new int[numClass];
		for(int i = 0 ; i < ruleList.size() ; i++){
		    Map rule = (Map) ruleList.get(i);
		    Iterator it = rule.keySet().iterator();
		    
		    while(it.hasNext()){
		    	List curr = (List) it.next();
		    	boolean isAllMatch = true;
		    	for(int j = 0 ; j < curr.size() ; j++){
		    		if(!((TreeNode)curr.get(j)).containedBy(instance)){
		    			isAllMatch = false;
		    		}
		    	}
		    	if(isAllMatch){
		    		result[((int)rule.get(curr))]+=calWeight((double)pincipalityList.get(i), curr.size(), numAttr);
		    		len[((int)rule.get(curr))]+=curr.size();
		    	}
		    }
		}
		
		for(int i = 0 ; i < numClass ; i++){
			for(int j = i ; j < numClass ; j++){
				if(result[i] == result[j]){
					if(len[i]>len[j]){
						result[j]++;
					}else if(len[i]<len[j]){
						result[i]++;
					}
				}
			}
		}
		return result;
	}
	
	private double calWeight(double conv, int rulelen, int size) {
		double weight = 0;
		double d = size - rulelen;
		if (d == 0)
			d = 0.01;
		// weight = conv /d;
		weight = conv * rulelen;
		return weight;
	}
	
	private int findMax(double[] d)
	{
		int l=d.length;
		int iMax=0;
		double temp=d[0];
		for(int i=1;i<l;i++)
		{
			if(d[i]>temp)
			{
				iMax=i;
				temp=d[i];
			}
		}
		return iMax;
	}

	public static void main(String[] argv){
		ACWV.runClassifier();
	}
	
	public static void runClassifier(){
//		File folder = new File("data");
		File folder = new File("unbalance");
		File[]files = folder.listFiles();
		for(File f:files){
			Calendar c1 = Calendar.getInstance();
			String[] arg ={"-t",f.getAbsolutePath(),"-i","-x","10"};
			System.out.println(f.getName());
			runClassifier(new ACWV(), arg);
			Calendar c2 = Calendar.getInstance();
			System.out.println(c2.getTimeInMillis()-c1.getTimeInMillis());
		}
	}

	public FastVector[] getCCFPhead() {
		return headertable;
	}

	public Tree[] getCCFPTree(){
		return t;
	}
}
