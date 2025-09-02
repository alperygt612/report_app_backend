package com.dashboard.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import com.dashboard.Model.Demand;

@Service
public class MailService {
    
    private static final Logger logger = LoggerFactory.getLogger(MailService.class);

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String mailUsername;

    @Value("${spring.mail.password}")
    private String mailPassword;

    public void sendSupportEmail(Demand message) {
        try {
            logger.info("E-posta gönderimi başlatılıyor...");
            logger.info("Mail sunucusu bilgileri - Kullanıcı adı: {}", mailUsername);
            logger.info("Gönderen: {}", message.getEmail());
            
            if (mailUsername == null || mailPassword == null) {
                logger.error("Mail sunucusu bilgileri eksik!");
                throw new RuntimeException("Mail sunucusu bilgileri eksik!");
            }
            
            SimpleMailMessage email = new SimpleMailMessage();
            email.setTo("alperyigit88@gmail.com");
            email.setSubject("Yeni Destek Mesajı: " + message.getSubject());
            email.setText("Gönderen: " + message.getName() + "\n"
                        + "Email: " + message.getEmail() + "\n\n"
                        + "Mesaj:\n" + message.getMessage());

            logger.info("E-posta hazırlandı, gönderiliyor...");
            javaMailSender.send(email);
            logger.info("E-posta başarıyla gönderildi");
        } catch (Exception e) {
            logger.error("E-posta gönderilirken hata oluştu: {}", e.getMessage(), e);
            throw new RuntimeException("E-posta gönderilemedi: " + e.getMessage(), e);
        }
    }
}
