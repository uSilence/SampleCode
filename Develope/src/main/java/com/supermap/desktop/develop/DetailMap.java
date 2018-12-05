package com.supermap.desktop.develop;

import com.supermap.data.Point2D;
import com.supermap.data.Workspace;
import com.supermap.desktop.controls.ui.controls.SmDialog;
import com.supermap.desktop.core.Application;
import com.supermap.desktop.core.Interface.IFormMap;
import com.supermap.desktop.core.ui.SMSpinner;
import com.supermap.desktop.core.utilties.MapUtilities;
import com.supermap.ui.MapControl;
import com.supermap.desktop.core.ui.controls.GridBagConstraintsHelper;

import javax.swing.*;
import java.awt.*;
import com.supermap.mapping.Map;

/**
 * @author LiYong
 */
public class DetailMap extends SmDialog {
	private MapControl mapControl;
	private JLabel label;
	private SMSpinner smSpinner;
	private JTextField textField;
	//private Workspace workspace = new Workspace();
	private Map map = new Map();
	private Point2D mapCenter;
	private double ratio;

	private JFrame frame = new JFrame("放大镜");
	private GridBagLayout gridBagLayout = new GridBagLayout();

	public DetailMap(){
		//frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		newComponent();
		newMap();
		drawMap();
		//initLayout();

		frame.setLocation(300, 300);// 显示位置
		frame.setSize(400, 400);
	}

	private static Insets insets = new Insets(0, 0, 0, 0);
	private static void addComponents(Container container, Component component, int gridx, int gridy, int gridwidth,
	                                  int gridheight, int anchor, int fill) {
		GridBagConstraints panel2 = new GridBagConstraints(gridx, gridy, gridwidth, gridheight, 1.0, 1.0,
				anchor, fill, insets, 0, 0);
		container.add(component, panel2);
	}


	private void newComponent(){
		label = new JLabel("放大比例：");
		smSpinner = new SMSpinner();
		textField = new JTextField("2.0");
		//label.setFont(new Font("宋体", Font.PLAIN, 20));
/*		if (Application.getActiveApplication().getActiveForm() instanceof IFormMap) {
			IFormMap formMap = (IFormMap) Application.getActiveApplication().getActiveForm();
			mapControl = formMap.getMapControl();
		}*/
	}


	private void newMap(){
		ratio = Double.valueOf(textField.getText()); // 输入的缩放倍数
		if (Application.getActiveApplication().getActiveForm() instanceof IFormMap) {
			IFormMap formMap = (IFormMap) Application.getActiveApplication().getActiveForm();
			mapControl = formMap.getMapControl();
		}
		//mapCenter = MapUtilities.getActiveMap().getViewBounds().getCenter();
		mapCenter = map.getCenter(); // 地图中心点
		//double mapScale = map.getScale();
		//double mapScale = mapControl.getMap().getScale(); // 地图比例尺
		//map = mapControl.getMap();
		map.setViewBoundsLocked(true);
		map.setCenter(mapCenter);
		map.zoom(ratio);
		map.refresh(); // 重新绘制当前地图
	}

/*	public MapControl getMapControl(){
		if(mapControl == null){
			mapControl = new MapControl();
			mapControl.setMarginPanEnabled(false);
			mapControl.setWaitCursorEnabled(false);
			mapControl.getMap().setWorkspace(Application.getActiveApplication().getWorkspace());
			mapControl.getMap().refresh();
		}
		return mapControl;
	}*/

	private void initLayout() {
		this.setLayout(new GridBagLayout());
		this.add(mapControl, (new GridBagConstraintsHelper(0, 0, 2, 2)).setAnchor(10).setFill(1).setWeight(1.0D, 1.0D));
		this.add(label, (new GridBagConstraintsHelper(0, 2, 1, 1)).setAnchor(17).setFill(0).setInsets(5, 5, 5, 5));
		this.add(smSpinner, (new GridBagConstraintsHelper(1, 2, 1, 1)).setAnchor(17).setFill(2).setWeight(1.0D, 0.0D).setInsets(5, 5, 5, 5));
	}

	private void drawMap() {
		frame.setLayout(gridBagLayout);
		//显示地图区域
		addComponents(frame, mapControl, 0, 0, 20, 70, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
		addComponents(frame, label, 0, 70, 3, 15, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
		addComponents(frame, smSpinner, 3, 70, 17, 15, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
	}

	public void showDetailMap() {
		frame.setVisible(true);
	}

}
