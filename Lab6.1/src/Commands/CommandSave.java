package Commands;

import Models.CollectionManager;

public class CommandSave extends Command {
    public CommandSave(CollectionManager collectionManager) {
        super(
                Titles.save,
                Descriptions.save,
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
        if (params == null) {
            return collectionManager.saveToFile();
        } else {
            return null;
        }
    }
}
