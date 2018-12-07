package com.supermap.desktop.develop;

import com.supermap.desktop.controls.ui.controls.SmDialog;
import com.supermap.desktop.core.Interface.IOutput;
import com.supermap.desktop.core.utilties.MapUtilities;
import com.supermap.ui.MapControl;
import com.supermap.desktop.core.Application;
import com.supermap.desktop.core.Interface.IFormMap;
import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import com.supermap.data.Point2D;
import com.supermap.ui.InteractionMode;
import com.supermap.mapping.MapDrawnEvent;
import com.supermap.mapping.MapDrawnListener;
import com.supermap.desktop.core.Interface.IFormManager;
import com.supermap.desktop.core.event.ActiveFormChangedEvent;
import com.supermap.desktop.core.event.ActiveFormChangedListener;
import com.supermap.desktop.core.utilties.StringUtilities;
import javax.swing.JSpinner.DefaultEditor;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.text.ParseException;

/**
 * @author LiYong
 */
public class DetailMap2 extends SmDialog {
	public DetailMap2(){
		super((JFrame)Application.getActiveApplication().getMainFrame(), false);
		initComponent();
		initLayout();
		initListener();

		groupLayout.setAutoCreateContainerGaps(true);
		groupLayout.setAutoCreateGaps(true);
		frame.setContentPane(panel);
		// 显示位置
		frame.setLocation(((IFormMap)Application.getActiveApplication().getActiveForm()).getMapControl().getLocationOnScreen());
		frame.setAlwaysOnTop(true); // 小窗口总是显示在屏幕最前面
		frame.setSize(320, 340);
	}

	private JFrame frame = new JFrame("放大镜");
	private JPanel panel = new JPanel();
	private GroupLayout groupLayout = new GroupLayout(panel);

	private MapControl mapControlDetailMap;
	private JSpinner spinner;
	private JSpinner.NumberEditor editor;
	private JLabel label; // 放大比例
	private JLabel targetLabel; // 聚焦图形
	private Point2D mapCenter;
	private double ratio = 2.0;

	private MapDrawnListener mapDrawnListener = new MapDrawnListener() {
		public void mapDrawn(MapDrawnEvent mapDrawnEvent) {
			mapControlDetailMap.getMap().fromXML(MapUtilities.getActiveMap().toXML());
			initStates();
		}
	};
	private MouseMotionAdapter mouseMotionAdapter = new MouseMotionAdapter() {
		public void mouseMoved(MouseEvent e) {
			mapCenter = MapUtilities.getMapControl().getMap().pixelToMap(e.getPoint()); // 像素坐标转换为地图坐标
			initStates();
		}
	};
	// 监听Form层的变化
	private ActiveFormChangedListener activeFormChangedListener = new ActiveFormChangedListener() {
		public void activeFormChanged(ActiveFormChangedEvent e) {
			if(e.getNewActiveForm() != e.getOldActiveForm()){
				mapControlDetailMap.getMap().getTrackingLayer().clear();
				mapControlDetailMap.getMap().getLayers().clear();
				if(MapUtilities.getMapControl() != null) {
					MapUtilities.getMapControl().removeMouseMotionListener(mouseMotionAdapter);
					MapUtilities.getMapControl().addMouseMotionListener(mouseMotionAdapter);
					MapUtilities.getMapControl().getMap().removeDrawnListener(mapDrawnListener);
					MapUtilities.getMapControl().getMap().addDrawnListener(mapDrawnListener);
					mapControlDetailMap.getMap().fromXML(MapUtilities.getActiveMap().toXML());
					getTargetLabel().setVisible(true);
				} else {
					// 没有地图时，聚焦图形不可见
					getTargetLabel().setVisible(false);
				}
				mapControlDetailMap.getMap().refreshTrackingLayer();
				mapControlDetailMap.getMap().refresh();
			}
		}
	};

	// 根据输入值缩放（未实现）
	private KeyAdapter keyAdapter = new KeyAdapter() {
		@Override
		public void keyPressed(KeyEvent e) {
			String text = ((JSpinner.NumberEditor)spinner.getEditor()).getTextField().getText();
			ratio = Double.valueOf(text);
			initStates();
		}
	};
	// 根据输入值缩放（未实现）
	private ChangeListener changeListener2 = new ChangeListener() {
		@Override
		public void stateChanged(ChangeEvent e) {
			try {
				spinner.commitEdit(); // 将当前编辑的值提交给model
			} catch (ParseException pe) { // 如果当前编辑的值不能提交
				spinner.setValue(ratio);
			}
			ratio = Double.valueOf(spinner.getValue().toString());
			initStates();
		}
	};
	private CaretListener caretListener = new CaretListener() {
		@Override
		public void caretUpdate(CaretEvent e) {
			try {
				spinner.commitEdit(); // 将当前编辑的值提交给model
			} catch (ParseException pe) { // 如果当前编辑的值不能提交
				spinner.setValue(ratio);
			}
			ratio = Double.valueOf(spinner.getValue().toString());
			initStates();
		}
	};

	// 根据按键产生的值缩放
	private ChangeListener changeListener = new ChangeListener() {
		@Override
		public void stateChanged(ChangeEvent e) {
			JFormattedTextField var = ((JSpinner.DefaultEditor)DetailMap2.this.spinner.getEditor()).getTextField();
			ratio = Double.valueOf(var.getText());
			//ratio = getNum();
			initStates();
		}
	};

	private void initListener() {
		IFormManager var = Application.getActiveApplication().getMainFrame().getFormManager();
		var.addActiveFormChangedListener(this.activeFormChangedListener);
		MapUtilities.getMapControl().addMouseMotionListener(mouseMotionAdapter);
		MapUtilities.getMapControl().getMap().addDrawnListener(mapDrawnListener);
		spinner.addChangeListener(changeListener);

/*		if (spinner.getEditor() instanceof DefaultEditor) {
			DefaultEditor var2 = (JSpinner.NumberEditor) spinner.getEditor();
			JFormattedTextField var3 = var2.getTextField();
			var3.removeCaretListener(caretListener);
			var3.addCaretListener(caretListener);
		}*/
		//spinner.addChangeListener(changeListener2);
		//editor.getTextField().addKeyListener(keyAdapter);
	}

	// 根据输入值缩放方法二（未实现）
	private double getNum(){
		try {
			spinner.commitEdit(); // 将当前编辑的值提交给model
		} catch (ParseException pe) { // 如果当前编辑的值不能提交
//			JComponent editor = spinner.getEditor(); // 返回显示和潜在更改模型值的组件
//			if(editor instanceof JSpinner.DefaultEditor) {
//				((JSpinner.DefaultEditor)editor).getTextField().setValue(spinner.getValue());
//			}
			spinner.setValue(ratio);
		}
		return Double.valueOf(spinner.getValue().toString());
	}

	private void initComponent() {

		mapControlDetailMap = getMapControlDetailMap();
		mapControlDetailMap.setPreferredSize(new Dimension(300, 290));
		mapCenter = MapUtilities.getActiveMap().getViewBounds().getCenter();

		targetLabel = getTargetLabel();

		label = new JLabel("放大比例：");
		label.setPreferredSize(new Dimension(70, 50));

		double min = 0;
		double max = 100.0;
		double step = 0.01;
		SpinnerModel model = new SpinnerNumberModel(ratio, min, max, step);
		spinner = new JSpinner(model);
		spinner.setPreferredSize(new Dimension(230, 50));

		editor = new JSpinner.NumberEditor(spinner, "0");

	}

	// 放大之后的地图区域
	private MapControl getMapControlDetailMap() {
		if(mapControlDetailMap == null) {
			mapControlDetailMap = new MapControl();
			mapControlDetailMap.setMarginPanEnabled(false); // 关闭自动滚屏功能
			mapControlDetailMap.setInteractionMode(InteractionMode.CUSTOMALL); // 设置交互操作模式
			mapControlDetailMap.setWaitCursorEnabled(false); // 在地图绘制等待时，不会自动切换等待光标
			mapControlDetailMap.getMap().setWorkspace(Application.getActiveApplication().getWorkspace());
			// 返回原地图对象的XML字符串形式的描述
			// 根据指定的XML字符串创建新地图
			mapControlDetailMap.getMap().fromXML(MapUtilities.getActiveMap().toXML());
			mapControlDetailMap.getMap().refresh();
			// 添加直线和圆
			mapControlDetailMap.add(getTargetLabel());
		}
		return mapControlDetailMap;
	}

	private void initStates() {
		mapControlDetailMap.getMap().setCenter(mapCenter);
		mapControlDetailMap.getMap().setScale(MapUtilities.getActiveMap().getScale() * ratio);
		mapControlDetailMap.getMap().refresh();
	}

	// 小窗体整体布局
	private void initLayout() {
		panel.setLayout(groupLayout);
		GroupLayout.SequentialGroup hSequentialGroup = groupLayout.createSequentialGroup()
				.addComponent(label, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE, Integer.MAX_VALUE)
				.addComponent(spinner, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE, Integer.MAX_VALUE);
		GroupLayout.ParallelGroup hParallelGroup = groupLayout.createParallelGroup()
				.addComponent(mapControlDetailMap, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE, Integer.MAX_VALUE)
				.addGroup(hSequentialGroup);
		groupLayout.setHorizontalGroup(hParallelGroup);

		GroupLayout.ParallelGroup vParallelGroup1 = groupLayout.createParallelGroup()
				.addComponent(label, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE, Integer.MAX_VALUE)
				.addComponent(spinner, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE, Integer.MAX_VALUE);
		GroupLayout.SequentialGroup vSequentialGroup = groupLayout.createSequentialGroup()
				.addComponent(mapControlDetailMap, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE, Integer.MAX_VALUE)
				.addGroup(vParallelGroup1);
		groupLayout.setVerticalGroup(vSequentialGroup);
	}

	public void showMap() {
		frame.setVisible(true);
	}

	private JLabel getTargetLabel() {
		if(targetLabel == null) {
			targetLabel = new JLabel(){
				public void paint(Graphics g) {
					g.setColor(Color.RED);
					g.drawLine(0, mapControlDetailMap.getHeight() / 2, mapControlDetailMap.getWidth(), mapControlDetailMap.getHeight() / 2);
					g.drawLine(mapControlDetailMap.getWidth() / 2, 0, mapControlDetailMap.getWidth() / 2, mapControlDetailMap.getHeight());
					int x = mapControlDetailMap.getWidth() / 2 - mapControlDetailMap.getWidth() / 4;
					int y = mapControlDetailMap.getHeight() / 2 - mapControlDetailMap.getHeight() / 4;
					g.drawOval(x, y, mapControlDetailMap.getWidth() / 2, mapControlDetailMap.getHeight() / 2);
				}
			};
		}
		return targetLabel;
	}

}
