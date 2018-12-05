package com.supermap.desktop.develop;

import com.supermap.desktop.controls.ui.controls.SmDialog;
/*import com.supermap.data.GeoCircle;
import com.supermap.data.GeoCompound;
import com.supermap.data.GeoLine;
import com.supermap.data.GeoStyle;
import com.supermap.data.GeoText;
import com.supermap.data.Point2D;
import com.supermap.data.Point2Ds;
import com.supermap.data.Rectangle2D;
import com.supermap.data.TextPart;
import com.supermap.data.TextStyle;
import com.supermap.desktop.controls.ControlsProperties;
import com.supermap.desktop.core.Application;
import com.supermap.desktop.core.Interface.IFormManager;
import com.supermap.desktop.core.event.ActiveFormChangedEvent;
import com.supermap.desktop.core.event.ActiveFormChangedListener;
import com.supermap.desktop.core.ui.SMSpinner;
import com.supermap.desktop.core.ui.controls.GridBagConstraintsHelper;
import com.supermap.desktop.core.utilties.MapUtilities;
import com.supermap.desktop.core.utilties.StringUtilities;
import com.supermap.mapping.MapDrawnEvent;
import com.supermap.mapping.MapDrawnListener;
import com.supermap.mapping.TrackingLayer;
import com.supermap.ui.InteractionMode;
import com.supermap.ui.MapControl;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.JSpinner.DefaultEditor;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;*/

/**
 * @author LiYong
 */
public class DialogDetailMap extends SmDialog {
	/*private Point2D pointCenter = new Point2D();
	private double ratio = 2.0D;
	private MapControl mapControlDetailMap;
	private JLabel labelRatio;
	private DialogDetailMap.RatioSMSpinner spinnerRatio;
	private JLabel targetLabel;
	private MapDrawnListener mapDrawnListener = new MapDrawnListener() {
		public void mapDrawn(MapDrawnEvent mapDrawnEvent) {
			if (DialogDetailMap.this.spinnerRatio.isEnabled()) {
				DialogDetailMap.this.getMapControlDetailMap().getMap().fromXML(MapUtilities.getActiveMap().toXML());
				DialogDetailMap.this.initStates();
			}

		}
	};
	private MouseMotionAdapter mouseMotionAdapter = new MouseMotionAdapter() {
		public void mouseMoved(MouseEvent e) {
			if (DialogDetailMap.this.spinnerRatio.isEnabled()) {
				DialogDetailMap.this.pointCenter = MapUtilities.getMapControl().getMap().pixelToMap(e.getPoint());
				DialogDetailMap.this.initStates();
			}

		}
	};
	//private ActiveFormChangedListener activeFormChangedListener = new ActiveFormChangedListener();
	ActiveFormChangedListener activeFormChangedListener = new ActiveFormChangedListener() {
		@Override
		public void activeFormChanged(ActiveFormChangedEvent activeFormChangedEvent) {

		}
	};
	public void windowClosing(WindowEvent e) {
		IFormManager var2 = Application.getActiveApplication().getMainFrame().getFormManager();
		var2.removeActiveFormChangedListener(this.activeFormChangedListener);
		this.dispose();
	}

	protected void resetLocation() {
	}




	private void initLayout() {
		this.setLayout(new GridBagLayout());
		this.add(this.mapControlDetailMap, (new GridBagConstraintsHelper(0, 0, 2, 2)).setAnchor(10).setFill(1).setWeight(1.0D, 1.0D));
		this.add(this.labelRatio, (new GridBagConstraintsHelper(0, 2, 1, 1)).setAnchor(17).setFill(0).setInsets(5, 5, 5, 5));
		this.add(this.spinnerRatio, (new GridBagConstraintsHelper(1, 2, 1, 1)).setAnchor(17).setFill(2).setWeight(1.0D, 0.0D).setInsets(5, 5, 5, 5));
		this.getLayeredPane().add(this.getTargetLabel(), -1);
		this.getLayeredPane().setOpaque(false);
	}

	protected void dialogInit() {
		super.dialogInit();
		this.getTargetLabel().setBounds(0, 25, 320, 290);
	}

	public void repaint(long time, int x, int y, int width, int height) {
		super.repaint(time, x, y, width, height);
		this.getTargetLabel().setBounds(0, 25, this.getMapControlDetailMap().getWidth(), this.getMapControlDetailMap().getHeight());
		if (!this.spinnerRatio.isEnabled()) {
			this.displayNoMap();
		}

	}

	public void initStates() {
		if (this.spinnerRatio.isEnabled()) {
			this.getMapControlDetailMap().getMap().setCenter(this.pointCenter);
			this.getMapControlDetailMap().getMap().setScale(MapUtilities.getMapControl().getMap().getScale() * this.ratio);
			this.getMapControlDetailMap().getMap().refresh();
		}
	}

	private void initListener() {
		IFormManager var1 = Application.getActiveApplication().getMainFrame().getFormManager();
		var1.addActiveFormChangedListener(this.activeFormChangedListener);
		MapUtilities.getMapControl().addMouseMotionListener(this.mouseMotionAdapter);
		MapUtilities.getMapControl().getMap().addDrawnListener(this.mapDrawnListener);
	}

	public MapControl getMapControlDetailMap() {
		if (this.mapControlDetailMap == null) {
			this.mapControlDetailMap = new MapControl();
			this.mapControlDetailMap.setMarginPanEnabled(false);
			this.mapControlDetailMap.setInteractionMode(InteractionMode.CUSTOMALL);
			this.mapControlDetailMap.setWaitCursorEnabled(false);
			this.mapControlDetailMap.getMap().setWorkspace(Application.getActiveApplication().getWorkspace());
			this.mapControlDetailMap.getMap().fromXML(MapUtilities.getActiveMap().toXML());
			this.mapControlDetailMap.getMap().refresh();
		}

		return this.mapControlDetailMap;
	}

	public JLabel getTargetLabel() {
		if (this.targetLabel == null) {
			this.targetLabel = new JLabel() {
				public void paint(Graphics g) {
					g.setColor(Color.RED);
					g.drawLine(0, DialogDetailMap.this.getMapControlDetailMap().getHeight() / 2, DialogDetailMap.this.getMapControlDetailMap().getWidth(), DialogDetailMap.this.getMapControlDetailMap().getHeight() / 2);
					g.drawLine(DialogDetailMap.this.getMapControlDetailMap().getWidth() / 2, 0, DialogDetailMap.this.getMapControlDetailMap().getWidth() / 2, DialogDetailMap.this.getMapControlDetailMap().getHeight());
					int var2 = DialogDetailMap.this.getMapControlDetailMap().getWidth() / 2 - DialogDetailMap.this.getMapControlDetailMap().getWidth() / 4;
					int var3 = DialogDetailMap.this.getMapControlDetailMap().getHeight() / 2 - DialogDetailMap.this.getMapControlDetailMap().getWidth() / 4;
					g.drawOval(var2, var3, DialogDetailMap.this.getMapControlDetailMap().getWidth() / 2, DialogDetailMap.this.getMapControlDetailMap().getWidth() / 2);
				}
			};
		}

		return this.targetLabel;
	}

	private void displayTarget() {
		Rectangle2D var1 = this.getMapControlDetailMap().getMap().getViewBounds();
		GeoCompound var2 = new GeoCompound();
		GeoCircle var3 = new GeoCircle(this.getMapControlDetailMap().getMap().getCenter(), var1.getWidth() <= var1.getHeight() ? var1.getWidth() / 3.0D : var1.getHeight() / 3.0D);
		Point2D var4 = new Point2D(var1.getLeft(), var1.getTop() - var1.getHeight() / 2.0D);
		Point2D var5 = new Point2D(var1.getRight(), var1.getTop() - var1.getHeight() / 2.0D);
		Point2D var6 = new Point2D(var1.getLeft() + var1.getWidth() / 2.0D, var1.getTop());
		Point2D var7 = new Point2D(var1.getLeft() + var1.getWidth() / 2.0D, var1.getBottom());
		Point2Ds var8 = new Point2Ds();
		var8.add(var6);
		var8.add(var7);
		GeoLine var9 = new GeoLine(var8);
		Point2Ds var10 = new Point2Ds();
		var10.add(var4);
		var10.add(var5);
		GeoLine var11 = new GeoLine(var10);
		GeoStyle var12 = new GeoStyle();
		var12.setFillBackOpaque(false);
		var12.setFillOpaqueRate(0);
		var12.setLineWidth(0.1D);
		var12.setLineColor(Color.RED);
		var3.setStyle(var12);
		var9.setStyle(var12);
		var11.setStyle(var12);
		var2.addPart(var9);
		var2.addPart(var11);
		var2.addPart(var3);
		TrackingLayer var13 = this.getMapControlDetailMap().getMap().getTrackingLayer();
		var13.clear();
		var13.add(var2, "");
		this.getMapControlDetailMap().getMap().refreshTrackingLayer();
		this.getMapControlDetailMap().getMap().refresh();
	}

	private void displayNoMap() {
		Point2D var1 = new Point2D(this.getMapControlDetailMap().getMap().getViewBounds().getLeft(), this.getMapControlDetailMap().getMap().getViewBounds().getTop());
		TextPart var2 = new TextPart();
		TextStyle var3 = new TextStyle();
		var3.setForeColor(Color.RED);
		var3.setBold(true);
		var3.setFontHeight(6.0D);
		GeoText var4 = new GeoText();
		var4.setEmpty();
		((GeoText)var4).addPart(var2);
		((GeoText)var4).setTextStyle(var3);
		TrackingLayer var5 = this.getMapControlDetailMap().getMap().getTrackingLayer();
		var5.clear();
		var5.add(var4, "");
		this.getMapControlDetailMap().getMap().refreshTrackingLayer();
		this.getMapControlDetailMap().getMap().refresh();
	}

	class RatioSMSpinner extends SMSpinner {
		private CaretListener caretListener = new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				JFormattedTextField var2 = ((DefaultEditor)RatioSMSpinner.this.getEditor()).getTextField();
				String var3 = var2.getText();
				if (!StringUtilities.isNullOrEmptyString(var3) && StringUtilities.isNumber(var3) && StringUtilities.getNumber(var3) >= 1.0D && StringUtilities.getNumber(var3) <= 100.0D) {
					DialogDetailMap.this.ratio = StringUtilities.getNumber(var3);
					DialogDetailMap.this.initStates();
				}

			}
		};

		public RatioSMSpinner(SpinnerModel model) {
			super(model);
			if (this.getEditor() instanceof DefaultEditor) {
				DefaultEditor var3 = (DefaultEditor)this.getEditor();
				JFormattedTextField var4 = var3.getTextField();
				var4.removeCaretListener(this.caretListener);
				var4.addCaretListener(this.caretListener);
			}

		}
	}*/
/*	private Point2D pointCenter = new Point2D();
	private MapControl mapControl;
	private double ratio = 2.0D;
	private DialogDetailMap.RatioSMSpinner spinnerRatio;
	private MapControl mapControlDetailMap;
	private MapDrawnListener mapDrawnListener = new MapDrawnListener() {
		public void mapDrawn(MapDrawnEvent mapDrawnEvent) {
			if (DialogDetailMap.this.spinnerRatio.isEnabled()) {
				DialogDetailMap.this.getMapControlDetailMap().getMap().fromXML(MapUtilities.getActiveMap().toXML());
				DialogDetailMap.this.initStates();
			}
		}
	};
	private MouseMotionAdapter mouseMotionAdapter = new MouseMotionAdapter() {
		public void mouseMoved(MouseEvent e) {
			if (DialogDetailMap.this.spinnerRatio.isEnabled()) {
				DialogDetailMap.this.pointCenter = MapUtilities.getMapControl().getMap().pixelToMap(e.getPoint());
				DialogDetailMap.this.initStates();
			}

		}
	};


	public MapControl getMapControlDetailMap() {
		if (this.mapControlDetailMap == null) {
			this.mapControlDetailMap = new MapControl();
			this.mapControlDetailMap.setMarginPanEnabled(false);
			this.mapControlDetailMap.setInteractionMode(InteractionMode.CUSTOMALL);
			this.mapControlDetailMap.setWaitCursorEnabled(false);
			this.mapControlDetailMap.getMap().setWorkspace(Application.getActiveApplication().getWorkspace());
			this.mapControlDetailMap.getMap().fromXML(MapUtilities.getActiveMap().toXML());
			this.mapControlDetailMap.getMap().refresh();
		}

		return this.mapControlDetailMap;
	}

	public void initStates() {
		if (this.spinnerRatio.isEnabled()) {
			this.getMapControlDetailMap().getMap().setCenter(this.pointCenter);
			this.getMapControlDetailMap().getMap().setScale(MapUtilities.getMapControl().getMap().getScale() * this.ratio);
			this.getMapControlDetailMap().getMap().refresh();
		}
	}

	private void displayTarget() {
		Rectangle2D var1 = this.getMapControlDetailMap().getMap().getViewBounds();
		GeoCompound var2 = new GeoCompound();
		GeoCircle var3 = new GeoCircle(this.getMapControlDetailMap().getMap().getCenter(), var1.getWidth() <= var1.getHeight() ? var1.getWidth() / 3.0D : var1.getHeight() / 3.0D);
		Point2D var4 = new Point2D(var1.getLeft(), var1.getTop() - var1.getHeight() / 2.0D);
		Point2D var5 = new Point2D(var1.getRight(), var1.getTop() - var1.getHeight() / 2.0D);
		Point2D var6 = new Point2D(var1.getLeft() + var1.getWidth() / 2.0D, var1.getTop());
		Point2D var7 = new Point2D(var1.getLeft() + var1.getWidth() / 2.0D, var1.getBottom());
		Point2Ds var8 = new Point2Ds();
		var8.add(var6);
		var8.add(var7);
		GeoLine var9 = new GeoLine(var8);
		Point2Ds var10 = new Point2Ds();
		var10.add(var4);
		var10.add(var5);
		GeoLine var11 = new GeoLine(var10);
		GeoStyle var12 = new GeoStyle();
		var12.setFillBackOpaque(false);
		var12.setFillOpaqueRate(0);
		var12.setLineWidth(0.1D);
		var12.setLineColor(Color.RED);
		var3.setStyle(var12);
		var9.setStyle(var12);
		var11.setStyle(var12);
		var2.addPart(var9);
		var2.addPart(var11);
		var2.addPart(var3);
		TrackingLayer var13 = this.getMapControlDetailMap().getMap().getTrackingLayer();
		var13.clear();
		var13.add(var2, "");
		this.getMapControlDetailMap().getMap().refreshTrackingLayer();
		this.getMapControlDetailMap().getMap().refresh();
	}

	class RatioSMSpinner extends SMSpinner {
		private CaretListener caretListener = new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				JFormattedTextField var2 = ((DefaultEditor)RatioSMSpinner.this.getEditor()).getTextField();
				String var3 = var2.getText();
				if (!StringUtilities.isNullOrEmptyString(var3) && StringUtilities.isNumber(var3) && StringUtilities.getNumber(var3) >= 1.0D && StringUtilities.getNumber(var3) <= 100.0D) {
					DialogDetailMap.this.ratio = StringUtilities.getNumber(var3);
					DialogDetailMap.this.initStates();
				}

			}
		};

		public RatioSMSpinner(SpinnerModel model) {
			super(model);
			if (this.getEditor() instanceof DefaultEditor) {
				DefaultEditor var3 = (DefaultEditor)this.getEditor();
				JFormattedTextField var4 = var3.getTextField();
				var4.removeCaretListener(this.caretListener);
				var4.addCaretListener(this.caretListener);
			}

		}
	}*/

}
