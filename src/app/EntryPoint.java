package app;

import visual.standard.CommandLine;

/**
 * @author Roman
 */
public class EntryPoint {
    /**
     * @param args Аргументы передаваемые в метод
     */
    public static void main(String[] args) {
        //Запуск приложения
        new Application(new CommandLine()).run();
    }
}
