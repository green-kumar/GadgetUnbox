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

public class TabletUtil {
	
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
	
	public List<TabletUtilPojo> readFromCSV(String csvFilePath) throws IOException{


		List<TabletUtilPojo> TabletUtilList = new ArrayList<TabletUtilPojo>();
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
				TabletUtilPojo obj = beanReader.read(TabletUtilPojo.class, header, processors);
				if(obj==null) {
					break;
				}
				TabletUtilList.add(obj);
			}
		}
		finally {
			if( beanReader != null ) {
				beanReader.close();
			}
		}
		return TabletUtilList;	
	
	}
	
	public void writeToCSV(String csvFilePath,List<TabletUtilPojo> TabletUtilList){
		if(TabletUtilList != null) {
			ICsvBeanWriter beanWriter = null;
			

			try {
				beanWriter = new CsvBeanWriter(new FileWriter(csvFilePath),CsvPreference.STANDARD_PREFERENCE);
				String[] header = {"size","tabletWeight","topHeadlineFeatures","name","brand","mainImageUrl","restImageUrl","bestBuyPrice","colors","processor","releasedOS","accessories","additionalFeatures","ram","overViewDescription","overViewReleaseDate","overViewMarketStaus","overViewPhoneWeight","overViewDimension","overViewBodyMaterial","overViewReleaseOS","cameraFrontcameraInMegaPixalWithFeatures","cameraRearCameraInMegaPixalWithFeatures","cameraVideoResolution","cameraOtherCameraFeatures","displayScreenSize","displayScreenResolution","displayScreenMaterial","displayDisplayTechnology","displayDtherDisplayFeatures","batteryTalkTime","batteryBatteryCapacity","batteryBatteryTechnology","InternalStorage","ExpendableStorageFeatures","connectivityAndFeaturesWireLessConnectivityFeatures","connectivityAndFeaturesDualSimSupportabilty","connectivityAndFeaturesBluetoothVersion","connectivityAndFeaturesSimCardSize","connectivityAndFeaturesChargingOptions","connectivityAndFeaturesSensors","connectivityAndFeaturesothersConnectivityFeatures","connectivityAndFeaturesNetworkFeatures"};
				beanWriter.writeHeader(header);

				for (TabletUtilPojo obj : TabletUtilList) {
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
		TabletUtil m=new TabletUtil();
		List<TabletUtilPojo> list=m.readFromCSV("C://Users//mmt5185//Desktop//tabletUtil.csv");
		for(TabletUtilPojo obj:list ){
			System.out.println(obj.toString());
		}
		
		m.writeToCSV("C://Users//mmt5185//Desktop//testnew.csv", list);
	}
	
}

