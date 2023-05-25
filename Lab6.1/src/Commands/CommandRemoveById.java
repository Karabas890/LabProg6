package Commands;

import Models.CollectionManager;

public class CommandRemoveById extends Command {

    public CommandRemoveById(CollectionManager collectionManager) {

        super(
                Titles.removeById,
                Descriptions.removeHead,
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
        if (this.CheckParams(params, 1)) {
            if (params[0] == null)
                throw new Exception("Не указан ID!");
            return this.collectionManager.removeById(Integer.parseInt(params[0].toString()));
        } else {
            return null;
        }
    }


}
