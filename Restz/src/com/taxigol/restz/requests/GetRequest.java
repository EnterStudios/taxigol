package com.taxigol.restz.requests;

import java.io.IOException;

public interface GetRequest {

	public String getRequestString();
	public String getContent() throws IOException;
}
