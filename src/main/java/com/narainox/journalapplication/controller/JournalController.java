package com.narainox.journalapplication.controller;

import com.narainox.journalapplication.dto.journal.JournalEntryRequest;
import com.narainox.journalapplication.dto.journal.JournalEntryResponse;
import com.narainox.journalapplication.entity.JournalEntry;
import com.narainox.journalapplication.service.JournalEntryService;
import com.narainox.journalapplication.service.UserService;
import com.narainox.journalapplication.utils.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/journal")
public class JournalController {
    @Autowired
    private JournalEntryService journalEntryService;
    @PostMapping("/v1/create")
    public ResponseEntity<Object> createEntry(@RequestBody JournalEntryRequest journalEntryRequest)
    {
        try {
            String name = SecurityContextHolder.getContext().getAuthentication().getName();
            JournalEntryResponse journalEntryResponse= journalEntryService.createEntry(name,journalEntryRequest);
            return ResponseHandler.generateResponse("journal entry is created!",HttpStatus.CREATED,journalEntryResponse);
        }
        catch (Exception e)
        {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR,null);
        }
    }
    @DeleteMapping("/v1/delete/{id}")
    public ResponseEntity<Object> deleteEntry(@PathVariable Integer id)
    {
        try {
            journalEntryService.deleteEntry(id);
            return ResponseHandler.generateResponse("entry is deleted!",HttpStatus.INTERNAL_SERVER_ERROR,null);
        }
        catch (Exception ex)
        {
            return ResponseHandler.generateResponse(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR,null);
        }
    }
    @GetMapping("/v1/getEntryById/{id}")
    public ResponseEntity<Object> getEntry(@PathVariable Integer id)
    {
        try {
            String name = SecurityContextHolder.getContext().getAuthentication().getName();
            JournalEntryResponse response=journalEntryService.getEntry(id,name);
            return ResponseHandler.generateResponse("entry is updated!",HttpStatus.INTERNAL_SERVER_ERROR,response);
        }
        catch (Exception ex)
        {
            return ResponseHandler.generateResponse(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR,null);
        }
    }
    @GetMapping("/v1/getAllEntryOfUser")
    public ResponseEntity<Object> getAllEntryOfUser()
    {
        try {
            String name = SecurityContextHolder.getContext().getAuthentication().getName();
            List<JournalEntryResponse> response=journalEntryService.getAllEntry(name);
            return ResponseHandler.generateResponse("entry is updated!",HttpStatus.INTERNAL_SERVER_ERROR,response);
        }
        catch (Exception ex)
        {
            return ResponseHandler.generateResponse(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR,null);
        }
    }
    @PutMapping("/v1/updateJournalEntry/{id}")
    public ResponseEntity<Object> updateJournalEntry(@RequestBody JournalEntryRequest journalEntryRequest,@PathVariable int id)
    {
        try {
            String name = SecurityContextHolder.getContext().getAuthentication().getName();
            JournalEntryResponse response=journalEntryService.updateEntry(journalEntryRequest,name,id);
            return ResponseHandler.generateResponse("journal entry is updated.",HttpStatus.OK,response);
        }
        catch (Exception ex)
        {
            return ResponseHandler.generateResponse(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR,null);
        }
    }



}
