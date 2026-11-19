package com.gona.app.controller.webservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gona.app.facade.CMSComponentFacade;
import com.gona.app.facade.CMSPageFacade;
import com.gona.app.model.CMSVersion;

import io.micrometer.common.util.StringUtils;

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
	
	@GetMapping("/preview-page/{uid}/{version}")
    @ResponseBody
    public PageData getPagePreviewModeData(@PathVariable("uid") String uid,
    		@PathVariable("version") String version) {
		final CMSVersion cmsVersion = StringUtils.isNotBlank(version) ? Enum.valueOf(CMSVersion.class, version) : CMSVersion.Draft;
		PageData pageData = cmsPageFacade.getPageDataPreviewMode(uid, cmsVersion);
		if (pageData == null) {
			pageData = cmsPageFacade.getPageDataPreviewMode("404page", cmsVersion);
		}
		return pageData;
	}
	
	@GetMapping("/component/{uid}")
    @ResponseBody
    public String getComponentData(@PathVariable("uid") String uid) {
		return cmsComponentFacade.getComponentData(uid);
	}
	
	@GetMapping("/preview-component/{uid}/{version}")
    @ResponseBody
    public String getComponentPreviewModeData(@PathVariable("uid") String uid,
    		@PathVariable("version") String version) {
		final CMSVersion cmsVersion = StringUtils.isNotBlank(version) ? Enum.valueOf(CMSVersion.class, version) : CMSVersion.Draft;
		return cmsComponentFacade.getComponentPreviewModeData(uid, cmsVersion);
	}

}
