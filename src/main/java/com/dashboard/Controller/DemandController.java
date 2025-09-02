package com.dashboard.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.transaction.annotation.Transactional;
import java.util.HashMap;
import java.util.Map;

import com.dashboard.Model.Demand;
import com.dashboard.Repository.DemandRepository;
import com.dashboard.Service.MailService;

@RequestMapping("/api/support")
@Controller
@CrossOrigin(origins = "*")
public class DemandController {
    
    private final DemandRepository demandRepository;
    private final MailService javaMailService;

    public DemandController(DemandRepository demandRepository, MailService javaMailService) {
        this.demandRepository = demandRepository;
        this.javaMailService = javaMailService;
    }

    @PostMapping("/message")
    @Transactional
    public ResponseEntity<Map<String, String>> receiveSupportMessage(@RequestBody Demand message, @RequestHeader(value = "X-Schema", required = false) String schema) { 
        Map<String, String> response = new HashMap<>();

        try {
            if (message.getName() == null || message.getEmail() == null || message.getSubject() == null || message.getMessage() == null) {
                response.put("status", "error");
                response.put("message", "Lütfen tüm alanları doldurun.");
                return ResponseEntity.badRequest().body(response);
            }

            // user_id ataması
            if (schema != null && schema.startsWith("user")) {
                message.setUserId(Long.parseLong(schema.replace("user", "")));
            }

            demandRepository.save(message);
            javaMailService.sendSupportEmail(message);

            response.put("status", "success");
            response.put("message", "Mesajınız başarıyla alındı ve iletildi.");
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", "İşlem sırasında bir hata oluştu.");
            return ResponseEntity.internalServerError().body(response);
        }
    }
}
