


import java.util.List;
public interface PetDao
{
	int update(Pet pet);
	
	int delete(Pet pet);
	
	Pet find(int id);
	
	List<Pet> findAll();
}