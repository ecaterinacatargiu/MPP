package main;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class ClientApp
{
    public static void main(String[] args)
    {
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        TCPClient tcpClient = new TCPClient();
        PublisherServiceInterface thisService = new PublisherServiceClient(executorService, tcpClient);

        ClientConsole console = new ClientConsole(thisService);
        UIConsole ui = new UIConsole(thisService, console);
        ui.talkToUser();
        //console.runConsoleDummy();

        executorService.shutdown();

    }
}
