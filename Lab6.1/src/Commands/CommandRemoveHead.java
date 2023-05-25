package Commands;

import Models.CollectionManager;

public class CommandRemoveHead extends Command {

    public CommandRemoveHead(CollectionManager collectionManager) {
        super(
                Titles.removeHead,
                Descriptions.removeHead,
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
            return this.collectionManager.removeHead();
        }
        return null;
    }


}
