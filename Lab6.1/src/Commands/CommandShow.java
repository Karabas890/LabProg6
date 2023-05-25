package Commands;

import Models.CollectionManager;

public class CommandShow extends Command {

    public CommandShow(CollectionManager collectionManager) {
        super(
                Titles.show,
                Descriptions.show,
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
        return this.collectionManager.show();
    }


}
