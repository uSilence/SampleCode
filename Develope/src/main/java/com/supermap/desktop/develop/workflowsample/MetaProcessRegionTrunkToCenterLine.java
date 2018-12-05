/*package com.supermap.desktop.develop.workflowsample;

import com.supermap.analyst.spatialanalyst.Generalization;
import com.supermap.data.*;
import com.supermap.desktop.core.Application;
import com.supermap.desktop.core.properties.CoreProperties;
import com.supermap.desktop.core.utilties.DatasetUtilities;
import com.supermap.desktop.develop.DevelopProperties;
import com.supermap.desktop.process.constraint.ipls.DatasourceConstraint;
import com.supermap.desktop.process.constraint.ipls.EqualDatasourceConstraint;
import com.supermap.desktop.process.core.MetaProcess;
import com.supermap.desktop.process.parameter.interfaces.IParameters;
import com.supermap.desktop.process.parameter.ipls.*;
import com.supermap.desktop.process.types.DatasetTypes;*/

/**
 * @author SuperMap
 * @date 2018/11/6 0006、上午 9:12
 * @description 工作流中 面提取主干中心线代码示例
 */
/*public class MetaProcessRegionTrunkToCenterLine extends MetaProcess {

	private final static String INPUT_DATA = CoreProperties.getString("String_GroupBox_SourceData");
	private final static String OUTPUT_DATA = "TrunkCenterLineResult";

	private ParameterDatasourceConstrained sourceDatasource;
	private ParameterSingleDataset dataset;

	private ParameterSaveDataset saveDataset;

	public MetaProcessRegionTrunkToCenterLine() {
		setTitle(CoreProperties.getString("String_RegionTrunkToCenterLine"));
		initParameters();
		initParameterConstraint();
		initParametersState();
	}

	private void initParameters() {
		this.sourceDatasource = new ParameterDatasourceConstrained();
		this.dataset = new ParameterSingleDataset(DatasetType.REGION);
		this.saveDataset = new ParameterSaveDataset();

		ParameterCombine sourceData = new ParameterCombine();
		sourceData.setDescribe(CoreProperties.getString("String_GroupBox_SourceData"));
		sourceData.addParameters(this.sourceDatasource, this.dataset);
		ParameterCombine targetData = new ParameterCombine();
		targetData.setDescribe(CoreProperties.getString("String_GroupBox_ResultData"));
		targetData.addParameters(this.saveDataset);
		this.parameters.setParameters(sourceData, targetData);
		this.parameters.addInputParameters(INPUT_DATA, DatasetTypes.REGION, sourceData);
		this.parameters.addOutputParameters(OUTPUT_DATA, DevelopProperties.getString("String_Result_CenterLine"), DatasetTypes.LINE, targetData);
	}

	private void initParameterConstraint() {
		EqualDatasourceConstraint equalDatasourceConstraint = new EqualDatasourceConstraint();
		equalDatasourceConstraint.constrained(sourceDatasource, ParameterDatasource.DATASOURCE_FIELD_NAME);
		equalDatasourceConstraint.constrained(dataset, ParameterSingleDataset.DATASOURCE_FIELD_NAME);

		DatasourceConstraint.getInstance().constrained(saveDataset, ParameterSaveDataset.DATASOURCE_FIELD_NAME);
	}

	private void initParametersState() {
		this.saveDataset.setSelectedItem("result_RegionTrunkToCenterLine");
		Dataset defaultDataset = DatasetUtilities.getDefaultDataset(DatasetType.REGION);
		if (defaultDataset != null) {
			this.sourceDatasource.setSelectedItem(defaultDataset.getDatasource());
			this.dataset.setSelectedItem(defaultDataset);
			this.saveDataset.setResultDatasource(defaultDataset.getDatasource());
		}
		this.sourceDatasource.setDescribe(CoreProperties.getString("String_Label_Datasource"));

	}

	@Override
	public boolean execute() {
		boolean isSuccessful = false;
		try {

			String datasetName = this.saveDataset.getDatasetName();
			datasetName = this.saveDataset.getResultDatasource().getDatasets().getAvailableDatasetName(datasetName);
			DatasetVector src = null;
			if (this.getParameters().getInputs().getData(INPUT_DATA).getValue() != null) {
				src = (DatasetVector) this.getParameters().getInputs().getData(INPUT_DATA).getValue();
			} else {
				src = (DatasetVector) this.dataset.getSelectedItem();
			}

			Recordset recordset = src.getRecordset(false, CursorType.STATIC);

			Generalization.addSteppedListener(steppedListener);
			Dataset result = Generalization.regionToCenterLine(recordset, this.saveDataset.getResultDatasource(), datasetName);
			this.getParameters().getOutputs().getData(OUTPUT_DATA).setValue(result);
			isSuccessful = result != null;
		} catch (Exception e) {
			Application.getActiveApplication().getOutput().output(e.getMessage());
			e.printStackTrace();
		} finally {
			Generalization.removeSteppedListener(steppedListener);
		}
		return isSuccessful;
	}

	@Override
	public String getKey() {
		return "SampleCodeRegionTrunkToCenterLine";
	}

}*/

