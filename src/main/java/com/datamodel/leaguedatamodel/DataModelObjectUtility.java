package com.datamodel.leaguedatamodel;

public class DataModelObjectUtility implements IDataModelObjectUtility {

	@Override
	public boolean isDifferentObject(Object object1, Object object2) {
		if(object1.equals(object2)) {
			return false;
		}
		return true;
	}

	@Override
	public boolean isNotNull(Object object) {
		if(object == null) {
			return false;
		}
		return true;
	}

}