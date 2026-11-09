package com.gona.app.controller.backoffice;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.gona.app.constant.GoNaConstants;

@Controller
public class DashboardController {
	
	@GetMapping("/")
	public String dashboardViewPage(Model model) {
		return GoNaConstants.DASHBOARD_PAGE;
	}

}
