package ch.nolix.planningpoker.business;

import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.planningpoker.datamodel.Room;
import ch.nolix.planningpoker.datamodel.User;
import ch.nolix.planningpokerapi.businessapi.IDataController;
import ch.nolix.planningpokerapi.datamodelapi.IRoom;
import ch.nolix.planningpokerapi.datamodelapi.IUser;
import ch.nolix.system.objectdatabase.database.DatabaseAdapter;

public final class DataController implements IDataController {
	
	public static DataController withDatabaseAdapter(final DatabaseAdapter databaseAdapter) {
		return new DataController(databaseAdapter);
	}
	
	private final DatabaseAdapter databaseAdapter;
	
	private DataController(final DatabaseAdapter databaseAdapter) {
		
		GlobalValidator.assertThat(databaseAdapter).thatIsNamed(DatabaseAdapter.class).isNotNull();
		
		this.databaseAdapter = databaseAdapter;
	}
	
	@Override
	public IRoom createAndEnterNewRoom(final IUser user) {
		
		final var room = Room.fromParentCreator((User)user);
		databaseAdapter.insert(room);
		
		room.addVisitor(user);
		
		return room;
	}
	
	@Override
	public IUser createUserWithName(final String name) {
		
		final var user = User.withName(name);
		databaseAdapter.insert(user);
		
		return user;
	}
	
	@Override
	public IRoom getRefRoomByIdentification(final String identification) {
		return
		databaseAdapter
		.getRefTableByEntityType(Room.class)
		.getRefEntities()
		.getRefFirst(r -> r.getIdentification().equals(identification));		
	}
	
	@Override
	public IUser getRefUserById(final String id) {
		return databaseAdapter.getRefTableByEntityType(User.class).getRefEntityById(id);
	}
	
	@Override
	public boolean hasChanges() {
		return databaseAdapter.hasChanges();
	}
	
	@Override
	public void saveChanges() {
		databaseAdapter.saveChangesAndReset();
	}
}
