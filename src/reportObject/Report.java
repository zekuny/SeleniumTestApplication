package reportObject;

import java.util.ArrayList;
import java.util.HashSet;

public abstract class Report{
	// how many sections in the report detail page
	protected int count;
	// the name of the report detail page
	protected String name;
	// contains all the details in each section
	protected ArrayList<HashSet<String>> list;
	// 
	// xpath for "clicks"
	protected String[] click;
	// the button to run report
	String runXpath;
	public String getTimeXpath() {
		return timeXpath;
	}

	public void setTimeXpath(String timeXpath) {
		this.timeXpath = timeXpath;
	}
	// the button to run report after selecting dates
	String timeXpath;
	
	// start index for parameters
	protected int start_index;
	// end index for parameters
	protected int end_index;
	
	public Report(String name, int count, int start, int end, String[] click, String runXpath, String timeXpath){
		this.name = name;
		this.count = count;
		start_index = start;
		end_index = end;
		list = new ArrayList<HashSet<String>>();
		this.runXpath = runXpath;
		this.timeXpath = timeXpath;
		this.click = click;
	}

	public String getRunXpath() {
		return runXpath;
	}

	public void setRunXpath(String runXpath) {
		this.runXpath = runXpath;
	}

	public ArrayList<HashSet<String>> getList() {
		return list;
	}

	public void setList(ArrayList<HashSet<String>> list) {
		this.list = list;
	}

	public int getStart_index() {
		return start_index;
	}

	public void setStart_index(int start_index) {
		this.start_index = start_index;
	}

	public int getEnd_index() {
		return end_index;
	}

	public void setEnd_index(int end_index) {
		this.end_index = end_index;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String[] getClick() {
		return click;
	}

	public void setClick(String[] click) {
		this.click = click;
	}
	
}