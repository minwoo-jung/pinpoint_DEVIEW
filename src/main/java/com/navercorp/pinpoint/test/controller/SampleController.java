package com.navercorp.pinpoint.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SampleController {

	@RequestMapping("/hello")
	@ResponseBody
	public String loginform() {
		return "hello";
	}
}
