package com.cheer.dao;

import com.cheer.domain.Pet;
import java.util.List;

public interface PetDao
{
	int save(Pet pet);

	int delete(Pet pet);

	int update(Pet pet);

	Pet find(int id);

	List<Pet> findAll();
}