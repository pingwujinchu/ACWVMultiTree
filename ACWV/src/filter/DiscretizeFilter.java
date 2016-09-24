package filter;

import java.io.File;

import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSink;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.Filter;
import weka.filters.supervised.attribute.Discretize;
import weka.filters.unsupervised.attribute.NumericToNominal;

/**
 * 
 *  @fileName :   filter.Discretize.java
 *
 *	@version : 1.0
 *
 * 	@see { }
 *
 *	@author   :   fan
 *
 *	@since : JDK1.4
 *  
 *  Create date  : 2016年9月20日 上午10:37:40
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

public class DiscretizeFilter {
     public static void main(String []args) {
    	 File folder = new File("keel_unbalance");
    	 File[]files = folder.listFiles();
    	 Discretize discretize = new Discretize(); 
    	 NumericToNominal nn = new NumericToNominal();
    	 String[] options = null;
    	 for(File f:files){
    		 try{
    		 Instances instances = DataSource.read(f.getPath()); 
    		 instances.setClassIndex(instances.numAttributes()-1);
    		 System.out.println(instances.toSummaryString());
    		 options = new String[2]; 
    		 options[0] = "-R"; 
    		 options[1] = "first-last"; 
    		 discretize.setOptions(options);
    		 discretize.setInputFormat(instances); 
    		 Instances newInstances2 = Filter.useFilter(instances, discretize); 
    		 
    		 System.err.println(newInstances2.toSummaryString()); 
    		 DataSink.write("keel/"+f.getName(), newInstances2);
    		 }catch(Exception e){
    			 e.printStackTrace();
    		 }
    	 }
     }
}
