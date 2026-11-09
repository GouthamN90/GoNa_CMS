package com.gona.app.controller.webservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gona.app.facade.CMSComponentFacade;
import com.gona.app.facade.CMSPageFacade;
import com.gona.app.data.PageData;

@Controller
//TODO check how to overcome this by config.
@CrossOrigin(origins = "http://localhost:4200")
public class CmsController {
	
	@Autowired
	private CMSPageFacade cmsPageFacade;
	
	@Autowired
	private CMSComponentFacade cmsComponentFacade;
	
	@GetMapping("/page/{uid}")
    @ResponseBody
    public PageData getPageData(@PathVariable("uid") String uid) {
		PageData pageData = cmsPageFacade.getPageData(uid);
		if (pageData == null) {
			pageData = cmsPageFacade.getPageData("404page");
		}
		return pageData;
		
	}
	
	@GetMapping("/component/{uid}")
    @ResponseBody
    public String getComponentData(@PathVariable("uid") String uid) {
		return cmsComponentFacade.getComponentData(uid);
	}

}
