package Commands;

import Models.CollectionManager;

public class CommandExit extends Command {

    public CommandExit(CollectionManager collectionManager) {
        super(
                Titles.exit,
                Descriptions.exit,
                collectionManager);
    }

    /**
     * Выполняет команду с хранилищем объектов
     *
     * @param params параметры команды
     * @return результат выполнения команды
     */
    @Override
    protected Object excecute(Object[] params) throws Exception {
        System.out.println("Выход из программы!");
        //System.exit(0);
        Thread.currentThread().interrupt();
        return null;
    }


}
