package com.supermap.desktop.develop;

//import com.sun.javafx.collections.MappingChange;
import com.supermap.data.Point2D;
import com.supermap.mapping.Map;
import com.supermap.data.Workspace;
import com.supermap.data.Workspace;
import com.supermap.ui.MapControl;
import com.supermap.desktop.core.Application;
import com.supermap.desktop.core.Interface.IBaseItem;
import com.supermap.desktop.core.Interface.IForm;
import com.supermap.desktop.core.Interface.IFormMap;
import com.supermap.desktop.core.implement.CtrlAction;

//import java.awt.geom.Point2D;
import java.security.PrivateKey;

/**
 * @author SuperMap
 * @time 2018/4/16 0016 上午 10:07
 */
public class CtrlActionSampleCode extends CtrlAction {
	private DialogDetailMap a;

	public CtrlActionSampleCode(IBaseItem caller) {
		super(caller);
	}

	@Override
	public void run() {
//		Workspace workspace = new Workspace();
//		Map map = new Map(workspace);
		if (Application.getActiveApplication().getActiveForm() instanceof IFormMap) {
			IFormMap formMap = (IFormMap) Application.getActiveApplication().getActiveForm();
			MapControl mapControl = formMap.getMapControl();
			String mapName = mapControl.getMap().getName(); // 地图名称
			Point2D mapCenter = mapControl.getMap().getCenter(); // 地图中心点
			double mapScale = mapControl.getMap().getScale(); // 地图比例尺
			Workspace mapWorkSpace = mapControl.getMap().getWorkspace(); // 地图工作空间
			//Application.getActiveApplication().getOutput().output(mapName + " , " + mapCenter + " , " + mapScale + " , " + mapWorkSpace);

		}
		/*IForm activeForm = Application.getActiveApplication().getMainFrame().getFormManager().getActiveForm();
		IFormMap formMap = (IFormMap) Application.getActiveApplication().getActiveForm();
		MapControl mapControl = formMap.getMapControl();
		Application.getActiveApplication().getOutput().output(mapControl.getMap().getName());*/

		//Point2D point2D = map.getCenter();

		DetailMap detailMap = new DetailMap();
		detailMap.showDetailMap();
	}

}
