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

public class LaptopUtil {
	
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
			new Optional(),
			new Optional(),
			new Optional(),
			new Optional(),
			new Optional(),
			new Optional(),
			new Optional(),
			
			
	};
	
	public List<LaptopUtilPojo> readFromCSV(String csvFilePath) throws IOException{


		List<LaptopUtilPojo> LaptopUtilList = new ArrayList<LaptopUtilPojo>();
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
				LaptopUtilPojo obj = beanReader.read(LaptopUtilPojo.class, header, processors);
				if(obj==null) {
					break;
				}
				LaptopUtilList.add(obj);
			}
		}
		finally {
			if( beanReader != null ) {
				beanReader.close();
			}
		}
		return LaptopUtilList;	
	
	}
	
	public void writeToCSV(String csvFilePath,List<LaptopUtilPojo> LaptopUtilList){
		if(LaptopUtilList != null) {
			ICsvBeanWriter beanWriter = null;
			

			try {
				beanWriter = new CsvBeanWriter(new FileWriter(csvFilePath),CsvPreference.STANDARD_PREFERENCE);
				String[] header = {"topHeadlineFeatures","name","description","brand","mainImageUrl","laptopType","restImageUrl","bestBuyPrice","colors","weight","dimension","displaySize","accessories","batteryLife","batteryQuality","WarrantyAndAdditionalFeatures","releasedOS","memoryRamIncluded","memoryRamPossible","memoryStorage","memoryStorageTechnology","processorProcessor","processorProcessorImgUrl","processorProcessorSpeed","processorProcessorCores","processorCache","graphicsgraphicsProcessorSupport","graphicsTotalGraphicsMemory","displayDisplaySize","displayDisplayType","displayPixalDensity","displayResolution","connectivityEthernet","connectivityWifi","connectivityBluetooth","connectivityLanport","connectivityCardReader","connectivityHeadPhoneJack","connectivitySecurityLockPort","inputCamera","inputSpeaker","inputSound","inputOpticalDrive"};
				beanWriter.writeHeader(header);

				for (LaptopUtilPojo obj : LaptopUtilList) {
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
		LaptopUtil m=new LaptopUtil();
		List<LaptopUtilPojo> list=m.readFromCSV("C://Users//mmt5185//Desktop//LaptopUtil.csv");
		for(LaptopUtilPojo obj:list ){
			System.out.println(obj.toString());
		}
		
		m.writeToCSV("C://Users//mmt5185//Desktop//testnew.csv", list);
	}
	
}

