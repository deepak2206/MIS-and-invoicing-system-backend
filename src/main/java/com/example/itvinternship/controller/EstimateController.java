package com.example.itvinternship.controller;

import com.example.itvinternship.model.Estimate;
import com.example.itvinternship.service.EstimateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/estimates")
@CrossOrigin(origins = {"http://localhost:5173", "https://mis-and-invoicing-system-frontend.onrender.com"}, allowCredentials = "true")
public class EstimateController {

    @Autowired
    private EstimateService estimateService;

    @GetMapping
    public List<Estimate> getAllEstimates() {
        return estimateService.getAllEstimates();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estimate> getEstimate(@PathVariable Long id) {
        return estimateService.getEstimate(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Estimate> createEstimate(@RequestBody Map<String, String> body) {
        Long chainId = Long.parseLong(body.get("chainId"));
        Long zoneId = Long.parseLong(body.get("zoneId"));
        String service = body.get("service");
        int qty = Integer.parseInt(body.get("qty"));
        float costPerUnit = Float.parseFloat(body.get("costPerUnit"));
        float totalCost = Float.parseFloat(body.get("totalCost"));
        LocalDate deliveryDate = LocalDate.parse(body.get("deliveryDate"));
        String deliveryDetails = body.get("deliveryDetails");

        return ResponseEntity.ok(
            estimateService.createEstimate(chainId, zoneId, service, qty, costPerUnit, totalCost, deliveryDate, deliveryDetails)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Estimate> updateEstimate(@PathVariable Long id, @RequestBody Estimate updatedEstimate) {
        return ResponseEntity.ok(estimateService.updateEstimate(id, updatedEstimate));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEstimate(@PathVariable Long id) {
        estimateService.deleteEstimate(id);
        return ResponseEntity.noContent().build();
    }
}
