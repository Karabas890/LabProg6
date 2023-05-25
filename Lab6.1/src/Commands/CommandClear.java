package Commands;

import Models.CollectionManager;

public class CommandClear extends Command {

    public CommandClear(CollectionManager collectionManager) {
        super(
                Titles.clear,
                Descriptions.clear,
                collectionManager
        );
    }

    /**
     * Выполняет команду с хранилищем объектов
     *
     * @param params параметры команды
     * @return результат выполнения команды
     */
    @Override
    protected Object excecute(Object[] params) throws Exception {
        this.collectionManager.clear();
        return null;
    }

}
