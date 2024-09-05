package com.etiya.staj.business;

import com.etiya.staj.model.items.ItemDto;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailManager {
    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(List<ItemDto> items, String message, String to, String subject) throws MessagingException {
        MimeMessage mimemessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimemessage, true, "UTF-8");

        StringBuilder sb = new StringBuilder();
        sb.append("<html><body>");
        sb.append("<h3>").append(message).append("</h3>");
        sb.append("<table border='1' cellpadding='5' cellspacing='0'>");
        sb.append("<tr><th>Name</th><th>Namespace</th><th>Version</th></tr>");

        for (ItemDto item : items) {
            sb.append("<tr>");
            sb.append("<td>").append(item.getName()).append("</td>");
            sb.append("<td>").append(item.getNamespace()).append("</td>");
            sb.append("<td>").append(item.getVersion()).append("</td>");
            sb.append("</tr>");
        }

        sb.append("</table></body></html>");

        helper.setFrom("extrathings11@hotmail.com");
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(sb.toString(), true);

        mailSender.send(mimemessage);
    }
}
