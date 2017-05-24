package com.baidu.ueditor.upload;

import com.baidu.ueditor.define.State;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class Uploader {


	private Base64Uploader base64Uploader=new Base64Uploader();

	private BinaryUploader binaryUploader=new BinaryUploader();

	private HttpServletRequest request = null;
	private Map<String, Object> conf = null;

	public Uploader(HttpServletRequest request, Map<String, Object> conf) {
		this.request = request;
		this.conf = conf;
	}



	public final State doExec() {
		String filedName = (String) this.conf.get("fieldName");
		State state = null;

		if ("true".equals(this.conf.get("isBase64"))) {
			state = base64Uploader.save(this.request.getParameter(filedName),
					this.conf);
		} else {
			state = binaryUploader.save(this.request, this.conf);
		}

		return state;
	}


}
