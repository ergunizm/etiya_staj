package com.etiya.staj.dataAccess;

import com.etiya.staj.model.ItemDb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<ItemDb, Long> {
}
