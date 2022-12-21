package ch.nolix.planningpokerapi.datamodelapi;

import ch.nolix.coreapi.containerapi.mainapi.IContainer;

public interface IRoom {
	
	String getIdentification();
	
	IContainer<? extends IEstimationRound> getRefEstimationRounds();
	
	IUser getRefParentCreator();
	
	IContainer<? extends IRoomVisit> getRefVisits();
	
	boolean hasSetEstimationsInvisible();
	
	boolean hasSetEstimationsVisible();
	
	void setEstimationsInvisible();
	
	void setEstimationsVisible();
}
