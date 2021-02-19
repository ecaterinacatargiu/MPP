package clientside;

import interfaces.BookPublisherInterface;
import interfaces.BookServiceInterface;
import org.springframework.beans.BeansException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import clientside.config.*;

import java.rmi.RemoteException;

public class ClientApp {

    public static void main(String[] args) throws RemoteException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(
                    "clientside.config"
                );

        BookPublisherInterface bookPublisherInterfaceService = (BookPublisherInterface) context.getBean("rmiProxyFactoryBeanBookPublisher");
        UIConsole ui = new UIConsole(bookPublisherInterfaceService);
        ui.talkToUser();

    }
}
