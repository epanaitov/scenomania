package com.scenomania.utils;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author eugene
 */
public class DojoDataSource {
	
	private class DojoDataSourceItem {
		String id = "";
		String label = "";

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getLabel() {
			return label;
		}

		public void setLabel(String label) {
			this.label = label;
		}

	}

	String identifier = "id";
	String label = "label";
	List<DojoDataSourceItem> items = new LinkedList<DojoDataSourceItem>();

	public void addItem(String id, String label) {
		DojoDataSourceItem item = new DojoDataSourceItem();
		item.setId(id);
		item.setLabel(label);
		items.add(item);
	}
}
