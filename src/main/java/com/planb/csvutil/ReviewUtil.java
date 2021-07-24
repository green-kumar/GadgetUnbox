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

public class ReviewUtil {
	
	final CellProcessor[] processors = new CellProcessor[] { 
			
			new Optional(),
			new Optional(),
			new Optional(),
			new Optional(),
			new Optional()
	};
	
	public List<ReviewUtilPojo> readFromCSV(String csvFilePath) throws IOException{


		List<ReviewUtilPojo> ReviewUtilList = new ArrayList<ReviewUtilPojo>();
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
				ReviewUtilPojo obj = beanReader.read(ReviewUtilPojo.class, header, processors);
				if(obj==null) {
					break;
				}
				ReviewUtilList.add(obj);
			}
		}
		finally {
			if( beanReader != null ) {
				beanReader.close();
			}
		}
		return ReviewUtilList;	
	
	}
	
	public void writeToCSV(String csvFilePath,List<ReviewUtilPojo> ReviewUtilList){
		if(ReviewUtilList != null) {
			ICsvBeanWriter beanWriter = null;
			

			try {
				beanWriter = new CsvBeanWriter(new FileWriter(csvFilePath),CsvPreference.STANDARD_PREFERENCE);
				String[] header = {"reviewId","rating","reviewHeadline","reviewContent","reviewDate"};
				beanWriter.writeHeader(header);

				for (ReviewUtilPojo obj : ReviewUtilList) {
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
		ReviewUtil r=new ReviewUtil();
		List<ReviewUtilPojo> list=new ArrayList<ReviewUtilPojo>(); //r.readFromCSV("C://Users//mmt5185//Desktop//test.csv");
		/*for(ReviewUtilPojo obj:list ){
			System.out.println(obj.toString());
		}*/
		
		r.writeToCSV("C://Users//green//Dropbox//planb//review-aggregator-electronics//src//main//java//com//planb//csvutil//ReviewUtil.csv", list);
	}
	
}
