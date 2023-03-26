package mail;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.io.FileUtils;

import objects.AutotestsRecord;
import objects.AutotestsView;
import objects.BuildRecord;
import objects.BuildStatus;
import objects.BuildView;
import objects.UnitTestsRecord;
import objects.UnittestsView;

public class SMTPService {
	/** **/
	protected Configuration configuration =  Configuration.getConfiguration();
	
	/**
	 * @throws IOException  **/
    public void SendEmailTest(final BuildView build) throws IOException
    {
        final String destAddress = "andtokm@yandex.ru";
        final String srcAddress = "andtokmtest@yandex.ru";
        final String username = "andtokmtest@yandex.ru";
        final String password = "ziudjagaggggggggg";
        
    	/*
        final String destAddress = "a.tokmakov@corp.mail.ru";
        final String srcAddress = "browser-build@corp.mail.ru";
        final String username = "browser-build@corp.mail.ru";
        final String password = "umjkrfazivteqfme";
		*/

        Properties props = new Properties();
        props.put("mail.smtp.host", configuration.getSmtpHost());        
        props.put("mail.debug", String.valueOf(configuration.isDebug()));
        props.put("mail.smtp.auth", String.valueOf(configuration.isAuthorizationRequired()));
        props.put("mail.smtp.port", configuration.getSmtpPort());
        props.put("mail.smtp.socketFactory.port", configuration.getSocketFactoryPort());
        props.put("mail.smtp.socketFactory.class", configuration.getSocketFactoryClass());
        props.put("mail.smtp.socketFactory.fallback", String.valueOf(configuration.getSocketFactoryFallback()));

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
        		protected PasswordAuthentication getPasswordAuthentication() {
        				return new PasswordAuthentication(username, password);
             }
        });

        try {
            Message message = new MimeMessage(session);
            InternetAddress from = new InternetAddress(srcAddress);
            InternetAddress to   = new InternetAddress(destAddress);
            
            String message_body = "<html><body>"; 
            message_body += "        <h4>Build properties.</h4>\r\n"; 
            message_body += "        <table cellspacing=\"0\" style=\"border: 2px dashed #FB4314; width: 100%;\"> \r\n"; 
            message_body += "           <tr><th>Project name:</th><td >Atom browser</td></tr> \r\n"; 
            message_body += "			<tr><th>Status:</th><td>" + build.getStatus() + "</td></tr> \r\n"; 
            message_body += "			<tr><th>Start time:</th><td>" + build.getStartTime() + "</td></tr> \r\n";
            if (null != build.getEndTime())
            	message_body += "			<tr><th>End time:</th><td>" + build.getEndTime() + "</td></tr> \r\n";
            if (false == build.isNightly()) 
            	message_body += "			<tr><th>Builds reason:</th><td>Git commit</td></tr> \r\n";
            else
				message_body += "			<tr><th>isNightly:</th><td>" + build.isNightly() + "</td></tr> \r\n"; 
            message_body += "			<tr><th>Browser Version:</th><td>"+ build.getBrowserVersion() + "</td></tr> \r\n"; 
            message_body += "			<tr><th>Owners:</th><td>" + build.getOwners() + "</td></tr> \r\n"; 
            message_body += "			<tr><th>Branch:</th><td>"+ build.getBranch() + "</td></tr> \r\n"; 
            message_body += "			<tr><th>Repository:</th><td>"+ build.getRepository() + "</td></tr> \r\n"; 
            message_body += "			<tr><th>Revision:</th><td>"+ build.getRevision()+ "</td></tr> \r\n"; 
            message_body += "			<tr><th>Uuid:</th><td>"+ build.getUuid() + "</td></tr> \r\n"; 
            message_body += "			<tr><th>Changes:</th><td>....................</td></tr> \r\n"; 
            message_body += "        </table> \r\n"; 
            
            message_body += "       <h4>Unit tests summary.</h4>\r\n"; 
            message_body += "		<table cellspacing=\"0\" style=\"border: 2px dashed #FB4314; width: 100%;\"> \r\n"; 
	        final List<UnittestsView> unitTests = build.getUnittests();
	        for (UnittestsView utView : unitTests) {
	        	message_body += "			<tr><th>Worker: " + utView.getWorkerName() + ": </th><td align=\"left\">"; 
	            message_body += " [Passed: " + utView.getPassedTestsCount();
	            message_body += ", Failed, : " + utView.getFailedTestsCount();
	            message_body += ", Skipped : " + utView.getSkippeddTestsCount();
	            message_body += "]</td></tr>\r\n"; 
	        }
            message_body += "        </table>"; 
            
            /** If its the commit tests, we should add the autotests block. **/
            if (false == build.isNightly()) {
                message_body += "       <h4>Auto tests summary.</h4>\r\n"; 
                message_body += "		<table cellspacing=\"0\" style=\"border: 2px dashed #FB4314; width: 90%;\"> \r\n"; 
                final List<AutotestsView> autoTests = build.getAutotests();
    	        for (final AutotestsView test : autoTests) {
    	        	message_body += "			<tr><th>Worker: " + test.getWorkerName() + ": </th><td align=\"left\">"; 
    	            message_body += " [Passed: " + test.getPassedTestsCount();
    	            message_body += ", Failed, : " + test.getFailedTestsCount();
    	            message_body += "]</td></tr>\r\n"; 
                }
                message_body += "        </table>"; 
            } 
            
            message_body += "    </body></html>";
            
            // message_body = FileUtils.readFileToString(new File("src\\templates\\Test3.html"), "UTF-8");

            message.setFrom(from);
            message.setRecipient(Message.RecipientType.TO, to);
            
            message.setSubject("[Atom Testing BB] Browser " + build.getBrowserVersion() + " results");
            message.setContent(message_body, "text/html");
            
            //System.out.println(message);
            
           
            
            // message.setText(message_text);
            Transport.send(message);
        } catch (final MessagingException exc) {
        	exc.printStackTrace();
        }
    }
    
	public static LocalDateTime str2LocalDateTime(final String strDateTime) {
		final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		try {
			LocalDateTime dateTime = LocalDateTime.parse(strDateTime, formatter);
			return dateTime;
		} catch (DateTimeParseException parseException) {
			System.err.println(parseException);
			return null;
		}
	}
	
    
    public static BuildView init_build_view()
    {
    	final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy H:m:s");
    	
		BuildRecord rec = new BuildRecord();
		rec.setUuid("ad03a85b-24f9-4966-b491-e5d5649a4be6");
		rec.setStatus(BuildStatus.InstallersAssembled);
		rec.setBuildnumber(233);
		rec.setNightly(false);
		rec.setBrowserVersion("6.0.0.1");
		rec.setBranch("atom_2.34");
		rec.setRepository("git@gitlab.corp.mail.ru:browser/browser.git1");
		rec.setRevision("8f4b42241b363482450a55cb3c0d2c9d76dc04d6");
		rec.setOwners(Arrays.asList("Owner1", "Owner2", "Owner3", "Owner5"));
		rec.setStartTime(LocalDateTime.parse("10/10/2019 11:22:33", formatter));
		rec.setEndTime(LocalDateTime.parse("10/10/2019 22:33:55", formatter));
    	
    	BuildView build = new BuildView(rec);
    	
    	build.addUnittest(initUnittestsView("WorkerTesterWin7x32", 10001, 7, 32, "2016-03-04 11:00:00", "2016-03-04 12:00:00"));
    	build.addUnittest(initUnittestsView("WorkerTesterWin7x64", 10002, 7, 64, "2016-03-04 11:00:00", "2016-03-04 12:00:00"));
    	build.addUnittest(initUnittestsView("WorkerTesterWin8x32", 10003, 8, 32, "2016-03-04 11:00:00", "2016-03-04 12:00:00"));
    	build.addUnittest(initUnittestsView("WorkerTesterWin8x64", 10004, 8, 64, "2016-03-04 11:00:00", "2016-03-04 12:00:00"));
    	build.addUnittest(initUnittestsView("WorkerTesterWin10x32", 10005, 10, 32, "2016-03-04 11:00:00", "2016-03-04 12:00:00"));
    	build.addUnittest(initUnittestsView("WorkerTesterWin10x64", 10006, 10, 64, "2016-03-04 11:00:00", "2016-03-04 12:00:00"));
    	
    	build.addAutotest(initAutotestsView("UpdateInstallTesterWin7x64", 10001, 7, "2016-03-04 11:00:00", "2016-03-04 12:00:00"));
    	build.addAutotest(initAutotestsView("UpdateInstallTesterWin8x64", 10002, 7, "2016-03-04 11:00:00", "2016-03-04 12:00:00"));
    	build.addAutotest(initAutotestsView("UpdateInstallTesterWin10x64", 10003, 8, "2016-03-04 11:00:00", "2016-03-04 12:00:00"));

    	return build;
    }
    
    public static UnittestsView initUnittestsView(final String workerName,
		    									   long passed, 
		    									   long failed,
		    									   long skipped,
		    									   final String startTime,
		    									   final String endTime) {
    	UnitTestsRecord rec = new UnitTestsRecord();
    	rec.setWorkerName(workerName);
    	rec.setStartTime(str2LocalDateTime(startTime));
    	rec.setEndTime(str2LocalDateTime(endTime));
    	
    	UnittestsView unit_tests_view = new UnittestsView(rec, passed, failed, skipped);
    	return unit_tests_view;
    }
    
    public static AutotestsView initAutotestsView(final String workerName,
												  long passed, 
												  long failed,
												  final String startTime,
												  final String endTime) {
    	AutotestsRecord rec = new AutotestsRecord();
		rec.setWorkerName(workerName);
		rec.setStartTime(str2LocalDateTime(startTime));
		rec.setEndTime(str2LocalDateTime(endTime));
		
		AutotestsView auto_tests_view = new AutotestsView(rec, passed, failed);
		return auto_tests_view;
	}    
    
    /** MAIN 
     * @throws IOException **/
	public static void main(String[] args) throws IOException {
		SMTPService srvMail = new SMTPService();
		srvMail.SendEmailTest(init_build_view());
	}
}
