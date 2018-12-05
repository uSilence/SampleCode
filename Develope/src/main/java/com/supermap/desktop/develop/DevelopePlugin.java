package com.supermap.desktop.develop;

import com.supermap.data.ProductType;
import com.supermap.desktop.core.AbstractPlugin;
import com.supermap.desktop.core.PluginInfo;
import com.supermap.desktop.core.license.LicenseException;
import com.supermap.desktop.core.license.LicenseManager;

/**
 * @author SuperMap
 * @time 2018/4/16 0016 上午 10:05
 */
public class DevelopePlugin extends AbstractPlugin {

	public DevelopePlugin(String name, PluginInfo pluginInfo) throws LicenseException {
		super(name, pluginInfo);
	}

	@Override
	public boolean isGranted() {
		return LicenseManager.INSTANCE.isGranted(ProductType.IDESKTOPJAVA_STANDARD);
	}

	@Override
	public String getPluginTitle() {
		return "Develope";
	}

	@Override
	public String getPluginName() {
		return "SuperMap.Desktop.Develope";
	}


}

