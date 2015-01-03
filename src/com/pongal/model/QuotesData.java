package com.pongal.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class QuotesData implements Serializable {

	private List<QuotesList> response = new ArrayList<QuotesList>();

	public class QuotesList {
		private String message;

		public String getMessage() {
			return message;
		}

		public void setMessage(String title) {
			this.message = title;
		}

	}

	public List<QuotesList> getQuotesList() {
		return response;
	}

	public void setQuotesList(List<QuotesList> quotesLists) {
		this.response = quotesLists;
	}
}
