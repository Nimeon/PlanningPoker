package ch.nolix.planningpoker.business;

import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.planningpokerapi.businessapi.IDataController;
import ch.nolix.planningpokerapi.businessapi.IEventController;
import ch.nolix.planningpokerapi.businessapi.IPlanningPokerController;
import ch.nolix.system.objectdatabase.database.DatabaseAdapter;

public final class PlanningPokerController implements IPlanningPokerController {
	
	public static PlanningPokerController withDatabaseAdapterAndEventController(
		final DatabaseAdapter databaseAdapter,
		final IEventController eventController
	) {
		return new PlanningPokerController(databaseAdapter, eventController);
	}
	
	private final IDataController dataController;
	private final IEventController eventController;
	
	private PlanningPokerController(
		final DatabaseAdapter databaseAdapter,
		final IEventController eventController
	) {
		
		GlobalValidator.assertThat(eventController).thatIsNamed("event controller").isNotNull();
		
		dataController = DataController.withDatabaseAdapter(databaseAdapter);
		this.eventController = eventController;
	}
	
	@Override
	public IDataController getRefDataController() {
		return dataController;
	}
	
	@Override
	public IEventController getRefEventController() {
		return eventController;
	}
}
