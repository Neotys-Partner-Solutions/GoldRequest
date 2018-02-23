package com.neotys.protocol.GoldRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.Icon;

import com.google.common.base.Optional;
import com.neotys.extensions.action.Action;
import com.neotys.extensions.action.ActionParameter;
import com.neotys.extensions.action.engine.ActionEngine;

public final class GoldRequestAction implements Action{
	private static final String BUNDLE_NAME = "com.neotys.protocol.GoldRequest.bundle";
	private static final String DISPLAY_NAME = ResourceBundle.getBundle(BUNDLE_NAME, Locale.getDefault()).getString("displayName");
	private static final String DISPLAY_PATH = ResourceBundle.getBundle(BUNDLE_NAME, Locale.getDefault()).getString("displayPath");
	static final String Host="Host";
	static final String Port="Port";
	static final String TimeOut="TimeOut";
	static final String FunctionName="FunctionName";
	@Override
	public String getType() {
		return "GoldRequest";
	}

	@Override
	public List<ActionParameter> getDefaultActionParameters() {
		List<ActionParameter> actionParameters=new ArrayList<ActionParameter>();
		actionParameters.add(new ActionParameter(Host,"Host of the Gold Server"));
		actionParameters.add(new ActionParameter(Port,"Port"));
		actionParameters.add(new ActionParameter(TimeOut,"10"));
		actionParameters.add(new ActionParameter(FunctionName,"FunctionName"));
		actionParameters.add(new ActionParameter("Param1","Parameter 1 of the function"));
		return 	actionParameters;
	}

	@Override
	public Class<? extends ActionEngine> getEngineClass() {
		return GoldRequestActionEngine.class;
	}

	@Override
	public Icon getIcon() {
		// TODO Add an icon
		return null;
	}

	@Override
	public String getDescription() {
		final StringBuilder description = new StringBuilder();
		// TODO Add description
		description.append("GoldRequest is the action required to send a Gold request against a Gold Server.\n")
		.append("Parameters are:\n")
		.append("- "+Host+": Host of the application.\n")
		.append("- "+Port+": Port.\n")
		.append("- "+TimeOut+": the timeout when receiving the response in seconds.\n")
		.append("- "+FunctionName+": Name of the Gold Function\n")
		.append("- Param1: Parameter 1 of the function\n")
		.append("- Paramxx: Parameter x of the function\n")
		.append("This action can accept Parameters from Param1 to Param50");
	

		return description.toString();
	}

	@Override
	public String getDisplayName() {
		return DISPLAY_NAME;
	}

	@Override
	public String getDisplayPath() {
		return DISPLAY_PATH;
	}

	@Override
	public Optional<String> getMinimumNeoLoadVersion() {
		return Optional.of("6.1");
	}

	@Override
	public Optional<String> getMaximumNeoLoadVersion() {
		return Optional.absent();
	}
}
