package com.podio;

import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientRequest;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.filter.ClientFilter;

public class TestFilter extends ClientFilter {

	@Override
	public ClientResponse handle(ClientRequest cr)
			throws ClientHandlerException {
		if (!cr.getURI().getPath().startsWith("/oauth")) {
			cr.getHeaders().putSingle("hoist.api.test", "1");
		}

		return getNext().handle(cr);
	}

}