package com.supermap.desktop.develop;

import com.supermap.ui.MapControl;
import com.supermap.desktop.core.Application;
import com.supermap.desktop.core.Interface.IFormMap;
import javax.swing.*;
import java.awt.*;

/**
 * @author LiYong
 */
public class DetailMap2 extends JFrame {
	public DetailMap2(){
		newComponent();
		drawMap();

		groupLayout.setAutoCreateContainerGaps(true);
		groupLayout.setAutoCreateGaps(true);
		frame.setContentPane(panel);
		frame.setLocation(600, 300);
		frame.setSize(500, 400);
	}

	private JFrame frame = new JFrame("放大镜");
	private JPanel panel = new JPanel();
	private GroupLayout groupLayout = new GroupLayout(panel);

	private MapControl mapControl;
	private JLabel label2;
	private JLabel label;
	private JTextField textField;

	private void newComponent(){
//		mapControl = new MapControl();
//		IFormMap formMap = (IFormMap) Application.getActiveApplication().getActiveForm();
//		mapControl = formMap.getMapControl();
		label2 = new JLabel("234214");
		label = new JLabel("放大比例");
		textField = new JTextField("2.0");
	}

	private void drawMap(){
		panel.setLayout(groupLayout);
		GroupLayout.SequentialGroup hSequentialGroup = groupLayout.createSequentialGroup()
				.addComponent(label, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE, Integer.MAX_VALUE)
				.addComponent(textField, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE, Integer.MAX_VALUE);
		GroupLayout.ParallelGroup hParallelGroup = groupLayout.createParallelGroup()
				.addComponent(label2, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE, Integer.MAX_VALUE)
				.addGroup(hSequentialGroup);
		groupLayout.setHorizontalGroup(hParallelGroup);

		GroupLayout.ParallelGroup vParallelGroup1 = groupLayout.createParallelGroup()
				.addComponent(label, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE, Integer.MAX_VALUE)
				.addComponent(textField, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE, Integer.MAX_VALUE);
		GroupLayout.SequentialGroup vSequentialGroup = groupLayout.createSequentialGroup()
				.addComponent(label2, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE, Integer.MAX_VALUE)
				.addGroup(vParallelGroup1);
		groupLayout.setVerticalGroup(vSequentialGroup);
	}

	private void showMap(){
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		DetailMap2 map = new DetailMap2();
		map.showMap();
	}
}
