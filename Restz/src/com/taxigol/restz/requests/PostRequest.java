package com.taxigol.restz.requests;

import java.io.IOException;

public interface PostRequest {

	public String getBaseUrl();
	public String getContent() throws IOException;
}
