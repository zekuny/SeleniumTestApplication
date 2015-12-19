package reportObject;

import java.util.HashMap;

public class ReportManager{
	HashMap<String, Report> reportMap = new HashMap<String, Report>();
	public ReportManager(){
		reportMap.put("ShipmentVolumeForecast", new ShipmentVolumeForecast());
	}
	
	public HashMap<String, Report> getMap(){
		return reportMap;
	}
	
	public void addToMap(String name, Report report){
		reportMap.put(name, report);
	}
	
	public Report getReport(String name){
		return reportMap.get(name);
	}
}