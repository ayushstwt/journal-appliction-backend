package com.narainox.journalapplication.service.impl;

import com.narainox.journalapplication.dto.journal.JournalEntryRequest;
import com.narainox.journalapplication.dto.journal.JournalEntryResponse;
import com.narainox.journalapplication.entity.JournalEntry;
import com.narainox.journalapplication.entity.User;
import com.narainox.journalapplication.exception.RecordNotFoundException;
import com.narainox.journalapplication.repository.JournalEntryRepository;
import com.narainox.journalapplication.repository.UserRepository;
import com.narainox.journalapplication.service.JournalEntryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JournalEntryServiceImpl implements JournalEntryService {
    @Autowired
    private JournalEntryRepository journalEntryRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepository userRepository;

    @Override
    public JournalEntryResponse createEntry(String name, JournalEntryRequest journalEntryRequest) {
        JournalEntry entry = modelMapper.map(journalEntryRequest, JournalEntry.class);
        entry.setCreatedAt(LocalDateTime.now());
        entry.setUser(userRepository.findByUsername(name).get());
        JournalEntry save = journalEntryRepository.save(entry);
        return modelMapper.map(save,JournalEntryResponse.class);
    }

    @Override
    public void deleteEntry(Integer id) {
        JournalEntry entry = journalEntryRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Journal is not found with id"));
        journalEntryRepository.delete(entry);
    }

    @Override
    public JournalEntryResponse getEntry(Integer id, String name) {
        User user = userRepository.findByUsername(name).get();
        JournalEntry entry = user.getJournalEntryList()
                .stream()
                .filter(x -> x.getId().equals(id))
                .findAny()
                .orElseThrow(()->new RecordNotFoundException("entry is not found"));
        return modelMapper.map(entry,JournalEntryResponse.class);
    }

    @Override
    public List<JournalEntryResponse> getAllEntry(String name) {
        User user = userRepository.findByUsername(name).get();
        List<JournalEntryResponse> journalEntryResponses = user.getJournalEntryList()
                .stream()
                .map(this::journalEntryToJournalEntryResponse)
                .collect(Collectors.toList());
        return journalEntryResponses;
    }

    @Override
    public JournalEntryResponse updateEntry(JournalEntryRequest journalEntryRequest,String name,int id) {
        User user = userRepository.findByUsername(name).get();
        JournalEntry entry = user.getJournalEntryList().stream()
                .filter(x -> x.getId().equals(id))
                .findAny()
                .orElseThrow(() -> new RecordNotFoundException("Journal is not found with id"));
        entry.setUpdatedAt(LocalDateTime.now());
        entry.setContent(journalEntryRequest.getContent());
        entry.setTitle(journalEntryRequest.getTitle());
        JournalEntry journalEntry = journalEntryRepository.save(entry);
        return modelMapper.map(journalEntry,JournalEntryResponse.class);
    }

    public JournalEntryResponse journalEntryToJournalEntryResponse(JournalEntry journalEntry)
    {
        return modelMapper.map(journalEntry,JournalEntryResponse.class);
    }

}
