package com.narainox.journalapplication.service;

import com.narainox.journalapplication.dto.journal.JournalEntryRequest;
import com.narainox.journalapplication.dto.journal.JournalEntryResponse;

import java.util.List;

public interface JournalEntryService {
    JournalEntryResponse createEntry(String name, JournalEntryRequest journalEntryRequest);

    void deleteEntry(Integer id);

    JournalEntryResponse getEntry(Integer id, String name);

    List<JournalEntryResponse> getAllEntry(String name);

    JournalEntryResponse updateEntry(JournalEntryRequest journalEntryRequest,String username,int id);
}
