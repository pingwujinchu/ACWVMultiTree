package filter;

import java.io.File;

import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSink;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.Filter;
import weka.filters.supervised.attribute.Discretize;

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
     public static void mian(String []args) throws Exception{
    	 File folder = new File("keel_unbalance");
    	 File[]files = folder.listFiles();
    	 Discretize discretize = new Discretize(); 
    	 String[] options = null;
    	 for(File f:files){
    		 Instances instances = DataSource.read(f.getPath()); 
    		 System.out.println(instances.toSummaryString());
    		 options = new String[6]; 
    		 options[0] = "-B"; 
    		 options[1] = "8"; 
    		 options[2] = "-M"; 
    		 options[3] = "-1.0"; 
    		 options[4] = "-R"; 
    		 options[5] = "2-last"; 
    		 discretize.setOptions(options);
    		 discretize.setInputFormat(instances); 
    		 Instances newInstances2 = Filter.useFilter(instances, discretize); 
    		 System.err.println(newInstances2.toSummaryString()); 
    		 DataSink.write("keel/"+f.getName(), newInstances2); 
    	 }
     }
}
