package kr.co.seoulit.logistics.logiinfosvc.compinfo.controller;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nexacro.java.xapi.data.PlatformData;
import com.nexacro.java.xapi.data.VariableList;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/compinfo/*")
public class SendEmailController {
   @Autowired
   private javax.sql.DataSource data;
   private Multipart multipart;

   @RequestMapping(value="/estimatereportemail")
   public void sendEstimateReportEmail(@RequestAttribute("reqData") PlatformData reqData,
                                       @RequestAttribute("resData") PlatformData resData) {
      String iReportFolderPath = "C:\\Users\\kss12\\OneDrive\\바탕 화면\\logi73\\logi_back\\Logistics71_SpringBoot\\src\\main\\resources\\report\\Estimate.jrxml";

      HashMap<String, Object> parameters = new HashMap<>();
      // 레포트 이름
      InputStream inputStream = null;
      String to = reqData.getVariable("emailId").getString();
      try {
         String orderDraftNo = reqData.getVariable("orderDraftNo").getString();
         parameters.put("orderDraftNo", orderDraftNo);

         Connection conn = data.getConnection();

         inputStream = new FileInputStream(iReportFolderPath);

         // jrxml 형식으로 읽어옴
         JasperDesign jasperDesign = JRXmlLoader.load(inputStream);
         // jrxml 을 내가 원하는 모양을 가지고옴
         JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
         // 그 틀에 맞춰서 파라메터의 정보를 넣어줌
         JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, conn);

         JasperExportManager.exportReportToPdfFile(jasperPrint, "C:\\Users\\kss12\\OneDrive\\바탕 화면\\logi73\\logi_back\\Logistics71_SpringBoot\\src\\main\\resources\\report\\estimateReport.pdf");


      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         if (inputStream != null) {
            try {
               inputStream.close();
            } catch (IOException e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
            }
         }
      }
      String fileName = "estimateReport.pdf";
      String savePath = "C:\\Users\\kss12\\OneDrive\\바탕 화면\\logi73\\logi_back\\Logistics71_SpringBoot\\src\\main\\resources\\report";

      String host = "smtp.gmail.com";
      final String user = "lsw9574";
      final String password = "eogkrqytlyrrmglc";//구글은 앱비밀번호 써야됨(다른거는 모르겠음)


      // Get the session object
      Properties props = new Properties();
      props.put("mail.smtp.ssl.protocols", "TLSv1.2");
      props.put("mail.smtp.starttls.enable", "true");
      props.put("mail.smtp.host", host);
      props.put("mail.smtp.auth", "true");

      Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
         protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(user, password);
         }
      });

      // Compose the message
      try {
         MimeMessage message = new MimeMessage(session);
         message.setFrom(new InternetAddress(user));
         message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

         // Subject

         message.setSubject("요청하신 견적서 입니다.");
         multipart = new MimeMultipart();

         // Text
         MimeBodyPart mbp1 = new MimeBodyPart();


         mbp1.setText("요청하신 견적서 입니다. ");
         multipart.addBodyPart(mbp1);

         // send the message
         if(fileName != null){
            DataSource source = new FileDataSource(savePath+"\\"+fileName);
            BodyPart messageBodyPart = new MimeBodyPart();
            DataHandler data= new DataHandler(source);
            messageBodyPart.setDataHandler(data);
            messageBodyPart.setFileName(fileName);
            multipart.addBodyPart(messageBodyPart);
         }
         message.setContent(multipart);
         Transport.send(message);
         System.out.println("메일 발송 성공!");

      } catch (MessagingException e) {
         e.printStackTrace();
      }
   }
   @RequestMapping(value="/contractreportemail")
   public void sendContractReportEmail(@RequestAttribute("reqData") PlatformData reqData,
                                       @RequestAttribute("resData") PlatformData resData) {
      String iReportFolderPath = "C:\\Users\\kss12\\OneDrive\\바탕 화면\\logi73\\logi_back\\Logistics71_SpringBoot\\src\\main\\resources\\report\\Contract.jrxml";

      HashMap<String, Object> parameters = new HashMap<>();
      // 레포트 이름
      InputStream inputStream = null;
      String to = reqData.getVariable("emailId").getString();
      try {
         String orderDraftNo = reqData.getVariable("orderDraftNo").getString();
         parameters.put("orderDraftNo", orderDraftNo);

         Connection conn = data.getConnection();

         inputStream = new FileInputStream(iReportFolderPath);

         // jrxml 형식으로 읽어옴
         JasperDesign jasperDesign = JRXmlLoader.load(inputStream);
         // jrxml 을 내가 원하는 모양을 가지고옴
         JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
         // 그 틀에 맞춰서 파라메터의 정보를 넣어줌
         JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, conn);

         JasperExportManager.exportReportToPdfFile(jasperPrint, "C:/Users/LEE/Desktop/Nexacro/logi73/logi_back/Logistics71_SpringBoot/src/main/resources/report/ContractReport.pdf");


      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         if (inputStream != null) {
            try {
               inputStream.close();
            } catch (IOException e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
            }
         }
      }
      String fileName = "ContractReport.pdf";
      String savePath = "C:\\Users\\LEE\\Desktop\\Nexacro\\logi73\\logi_back\\Logistics71_SpringBoot\\src\\main\\resources\\report";

      String host = "smtp.gmail.com";
      final String user = "lsw9574";
      final String password = "eogkrqytlyrrmglc";//구글은 앱비밀번호 써야됨(다른거는 모르겠음)


      // Get the session object
      Properties props = new Properties();
      props.put("mail.smtp.ssl.protocols", "TLSv1.2");
      props.put("mail.smtp.starttls.enable", "true");
      props.put("mail.smtp.host", host);
      props.put("mail.smtp.auth", "true");

      Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
         protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(user, password);
         }
      });

      // Compose the message
      try {
         MimeMessage message = new MimeMessage(session);
         message.setFrom(new InternetAddress(user));
         message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

         // Subject

         message.setSubject("요청하신 주문서 입니다.");
         multipart = new MimeMultipart();

         // Text
         MimeBodyPart mbp1 = new MimeBodyPart();
         mbp1.setText("요청하신 주문서 입니다. ");
         multipart.addBodyPart(mbp1);

         // send the message
         if(fileName != null){
            DataSource source = new FileDataSource(savePath+"\\"+fileName);
            BodyPart messageBodyPart = new MimeBodyPart();
            DataHandler data= new DataHandler(source);
            messageBodyPart.setDataHandler(data);
            messageBodyPart.setFileName(fileName);
            multipart.addBodyPart(messageBodyPart);
         }
         message.setContent(multipart);
         Transport.send(message);
         System.out.println("메일 발송 성공!");

      } catch (MessagingException e) {
         e.printStackTrace();
      }
   }
}