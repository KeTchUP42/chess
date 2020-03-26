package app;

import visual.standard.CommandLine;

public class EntryPoint {
    /**
     * @param args Аргументы передаваемые в метод
     */
    public static void main(String[] args) {
        //Запуск  приложения
        Application app = new Application(new CommandLine());
        app.run();
    }
}
