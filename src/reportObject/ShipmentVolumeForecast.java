package reportObject;

import java.util.HashSet;

public class ShipmentVolumeForecast extends Report{
	public ShipmentVolumeForecast(){
		super("ShipmentVolumeForecast", 5, 14, 27, new String[]{".//*[@id='id_mstr79']/img", ".//*[@id='id_mstr108']/img", ".//*[@id='id_mstr136']/img", ".//*[@id='id_mstr166']/img", ".//*[@id='id_mstr196']/img"}, "//*[@id='id_mstr206']", ".//*[@id='id_mstr43']");
		
		HashSet<String> set1 = new HashSet<String>();
		set1.add("CANADA");
		set1.add("CHINA");
		set1.add("EUROPE");
		set1.add("GREATER CHINA");
		set1.add("JAPAN");
		set1.add("UNITED STATES");	
		super.getList().add(set1);

		HashSet<String> set2 = new HashSet<String>();
		set2.add("Air");
		set2.add("Ocean");
		super.getList().add(set2);
	
		HashSet<String> set3 = new HashSet<String>();
		set3.add("Plan Ship Cancel Date Custom Selection");
		set3.add("Plan Ship Cancel Date Next 4 Weeks");
		set3.add("Plan Ship Cancel Date Next 8 Weeks");
		super.getList().add(set3);
	
		HashSet<String> set4 = new HashSet<String>();
		set4.add("Plan InDC Fiscal Week");
		set4.add("Plan Ship Cancel Fiscal Week");
		super.getList().add(set4);
	
		HashSet<String> set5 = new HashSet<String>();
		set5.add("FEUs - Ordered Units");
		set5.add("Tons - Ordered Units");
		set5.add("DPO Count");
		set5.add("Ordered Units - PO/DPO");
		set5.add("Forecast Units - VMI Orders Fcst");
		super.getList().add(set5);
	}
	
}
