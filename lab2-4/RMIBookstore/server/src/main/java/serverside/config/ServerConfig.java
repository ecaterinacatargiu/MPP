package serverside.config;

import interfaces.BookPublisherInterface;
import interfaces.BookServiceInterface;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiServiceExporter;
import serverside.service.BookPublisherImpl;
import serverside.service.BookServiceImpl;

@Configuration
public class ServerConfig {

    @Bean
    RmiServiceExporter rmiServiceExporterBook() {

        RmiServiceExporter rmiServiceExporter = new RmiServiceExporter();
        rmiServiceExporter.setServiceName("BookPublisherInterface");
        rmiServiceExporter.setServiceInterface(BookPublisherInterface.class);
        rmiServiceExporter.setService(bookPublisherService());
        return rmiServiceExporter;
    }


    private Object bookPublisherService() {
        return new BookPublisherImpl();
    }


}
