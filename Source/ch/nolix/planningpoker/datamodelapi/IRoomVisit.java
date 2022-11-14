package ch.nolix.planningpoker.datamodelapi;

public interface IRoomVisit {
	
	void deleteEstimation();
	
	double getEstimationInStoryPoints();
	
	IRoom getRefParentRoom();
	
	IUser getRefParentVisitor();
	
	boolean hasEstimation();
	
	void setEstimationInStoryPoints(double estimationInStoryPoints);
}
