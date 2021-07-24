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

public class cameraUtil {
	
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
			
			
	};
	
	public List<cameraUtilPojo> readFromCSV(String csvFilePath) throws IOException{


		List<cameraUtilPojo> cameraUtilList = new ArrayList<cameraUtilPojo>();
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
				cameraUtilPojo obj = beanReader.read(cameraUtilPojo.class, header, processors);
				if(obj==null) {
					break;
				}
				cameraUtilList.add(obj);
			}
		}
		finally {
			if( beanReader != null ) {
				beanReader.close();
			}
		}
		return cameraUtilList;	
	
	}
	
	public void writeToCSV(String csvFilePath,List<cameraUtilPojo> cameraUtilList){
		if(cameraUtilList != null) {
			ICsvBeanWriter beanWriter = null;
			

			try {
				beanWriter = new CsvBeanWriter(new FileWriter(csvFilePath),CsvPreference.STANDARD_PREFERENCE);
				String[] header = {"topHeadlineFeatures","name","cameraType","mainImageUrl","restImageUrl","bestBuyPrice","colors","viewFinderType","brand","displaySize","accessories","additionalFeatures","totalMegaPixal","effectiveMegaPixal","overViewDescription","overViewReleaseDate","overViewMarketStaus","overViewCameraWeight","overViewDimension","overViewDodyMaterial","SensorImageSensorFormat","SensorSensorTyp","SensorSensorSize","videoRecordingVideoResolution","videoRecordingVideoFileFormat","videoRecordingMicType","exposureExposureCompensation","exposureAutoExposureBracketing","exposureExposureMating","exposureExposureModes","lensDigitalZoom","lensLensType","lensLensElement","lensLensGroup","lensLilterSize","focusFocusAdjustment","focusAutoFocusTechnology","focusFocusRange","cameraBatteryBatteryLife","cameraBatteryBatteryDetails","cameraStorageFileFormat","cameraStorageStorageMedia"};
				beanWriter.writeHeader(header);

				for (cameraUtilPojo obj : cameraUtilList) {
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
		cameraUtil m=new cameraUtil();
		List<cameraUtilPojo> list=m.readFromCSV("C://Users//mmt5185//Desktop//cameraUtil.csv");
		for(cameraUtilPojo obj:list ){
			System.out.println(obj.toString());
		}
		
		m.writeToCSV("C://Users//mmt5185//Desktop//testnew.csv", list);
	}
	
}
