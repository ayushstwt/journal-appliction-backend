package com.narainox.journalapplication.repository;

import com.narainox.journalapplication.entity.JournalEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JournalEntryRepository extends JpaRepository<JournalEntry,Integer> {
}
