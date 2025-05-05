package com.example.itvinternship.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.example.itvinternship.model.Chain;
import com.example.itvinternship.service.ChainService;

@RestController
@RequestMapping("/api/chains")
@CrossOrigin(origins = {
    "http://localhost:5173",
    "https://mis-and-invoicing-system-frontend.onrender.com"
}, allowCredentials = "true")
public class ChainController {

    @Autowired
    private ChainService chainService;

    @GetMapping
    public ResponseEntity<List<Chain>> getAllChains() {
        return ResponseEntity.ok(chainService.getAllChains());
    }

    @GetMapping("/group/{groupId}")
    public ResponseEntity<List<Chain>> getChainsByGroup(@PathVariable Long groupId) {
        return ResponseEntity.ok(chainService.getChainsByGroup(groupId));
    }

    @PostMapping
    public ResponseEntity<?> addChain(@RequestBody Map<String, String> req) {
        String name = req.get("companyName");
        String gstn = req.get("gstnNo");
        Long groupId = Long.valueOf(req.get("groupId"));

        if (name == null || gstn == null || groupId == null) {
            return ResponseEntity.badRequest().body("Missing fields");
        }

        try {
            return ResponseEntity.ok(chainService.addChain(name, gstn, groupId));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateChain(@PathVariable Long id, @RequestBody Map<String, String> req) {
        String name = req.get("companyName");
        String gstn = req.get("gstnNo");
        Long groupId = Long.valueOf(req.get("groupId"));

        try {
            return ResponseEntity.ok(chainService.updateChain(id, name, gstn, groupId));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteChain(@PathVariable Long id) {
        try {
            chainService.softDeleteChain(id);
            return ResponseEntity.ok("Deleted successfully.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
