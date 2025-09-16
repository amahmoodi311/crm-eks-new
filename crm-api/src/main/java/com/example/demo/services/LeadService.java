package com.example.demo.services;

import com.example.demo.entity.Lead;
import com.example.demo.repository.LeadRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LeadService {

    @Autowired
    private LeadRepo leadRepo;

    public Lead createLead(Lead lead) {
        return leadRepo.save(lead);
    }

    public Lead getLeadById(Long id) {
        return leadRepo.findById(id).orElseThrow(() -> new RuntimeException("Lead not found"));
    }

    public List<Lead> getAllLeads() {
        return leadRepo.findAll();
    }

    public Lead updateLead(Long id, Lead lead) {
        Lead existingLead = getLeadById(id);
        existingLead.setName(lead.getName());
        existingLead.setCc(lead.getCc());
        existingLead.setPhone(lead.getPhone());
        existingLead.setEmail(lead.getEmail());
        existingLead.setFeeQuoted(lead.getFeeQuoted());
        existingLead.setBatchTiming(lead.getBatchTiming());
        existingLead.setDescription(lead.getDescription());
        existingLead.setLeadStatus(lead.getLeadStatus());
        existingLead.setLeadSource(lead.getLeadSource());
        existingLead.setStack(lead.getStack());
        existingLead.setCourse(lead.getCourse());
        existingLead.setClassMode(lead.getClassMode());
        existingLead.setNextFollowUp(lead.getNextFollowUp());
        return leadRepo.save(existingLead);
    }

    public ResponseEntity<?> deleteLead(Long id) {
        if (leadRepo.existsById(id)) {
            leadRepo.deleteById(id);
            return ResponseEntity.ok("Lead deleted successfully!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Lead not found");
        }
    }
}