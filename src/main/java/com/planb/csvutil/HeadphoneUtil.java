package com.planb.csvutil;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

public class HeadphoneUtil {
	
	final CellProcessor[] processors = new CellProcessor[] { 
			
			new Optional(),
			new Optional(),
			new Optional(),
			new Optional(),
			new Optional(),
			new Optional(),
			new Optional(),
			new Optional(),
			new Optional(),
			new Optional(),
			new Optional(),
			new Optional(),
			new Optional(),
			new Optional(),
			new Optional(),
			new Optional(),
			new Optional(),
			new Optional(),
			
			
	};
	
	public List<HeadphoneUtilPojo> readFromCSV(String csvFilePath) throws IOException{


		List<HeadphoneUtilPojo> HeadphoneUtilList = new ArrayList<HeadphoneUtilPojo>();
		ICsvBeanReader beanReader = null;
		try {
			try {
				beanReader = new CsvBeanReader(new FileReader(csvFilePath), CsvPreference.STANDARD_PREFERENCE);
			} catch (FileNotFoundException e) {

				System.out.println("File Not Found");
				e.printStackTrace();
			}

			// the header elements are used to map the values to the bean (names must match)
			final String[] header = beanReader.getHeader(true);

			while(true) {
				HeadphoneUtilPojo obj = beanReader.read(HeadphoneUtilPojo.class, header, processors);
				if(obj==null) {
					break;
				}
				HeadphoneUtilList.add(obj);
			}
		}
		finally {
			if( beanReader != null ) {
				beanReader.close();
			}
		}
		return HeadphoneUtilList;	
	
	}
	
	public void writeToCSV(String csvFilePath,List<HeadphoneUtilPojo> HeadphoneUtilList){
		if(HeadphoneUtilList != null) {
			ICsvBeanWriter beanWriter = null;
			

			try {
				beanWriter = new CsvBeanWriter(new FileWriter(csvFilePath),CsvPreference.STANDARD_PREFERENCE);
				String[] header = {"topHeadlineFeatures","name","description","brand","mainImageUrl","restImageUrl","bestBuyPrice","colors","weight","headphoneType","wireType","design","mic","cordLength","jackDiameter","soundOutput","accessories","additionalFeatures"};
				beanWriter.writeHeader(header);

				for (HeadphoneUtilPojo obj : HeadphoneUtilList) {
					beanWriter.write(obj, header, processors);
				}

			} catch (IOException ex) {
				ex.printStackTrace();
			} finally {
				if (beanWriter != null) {
					try {
						beanWriter.close();
					} catch (IOException ex) {
						System.err.println("Error closing the writer: " + ex);
					}
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		HeadphoneUtil m=new HeadphoneUtil();
		List<HeadphoneUtilPojo> list=m.readFromCSV("C://Users//mmt5185//Desktop//HeadphoneUtil.csv");
		for(HeadphoneUtilPojo obj:list ){
			System.out.println(obj.toString());
		}
		
		m.writeToCSV("C://Users//mmt5185//Desktop//testnew.csv", list);
	}
	
}

