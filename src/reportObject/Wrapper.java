package reportObject;

import java.util.ArrayList;

public class Wrapper {
	ArrayList<Object[]> stepList = new ArrayList<Object[]>();
	ArrayList<String> queryList = new ArrayList<String>();
	
	public Wrapper(ArrayList<Object[]> sl, ArrayList<String> ql){
		stepList = sl;
		queryList = ql;
	}

	public ArrayList<Object[]> getStepList() {
		return stepList;
	}

	public void setStepList(ArrayList<Object[]> stepList) {
		this.stepList = stepList;
	}

	public ArrayList<String> getQueryList() {
		return queryList;
	}

	public void setQueryList(ArrayList<String> queryList) {
		this.queryList = queryList;
	}
}
