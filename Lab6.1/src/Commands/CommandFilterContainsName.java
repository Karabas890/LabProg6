package Commands;

import Models.CollectionManager;

public class CommandFilterContainsName extends Command {

    public CommandFilterContainsName(CollectionManager collectionManager) {
        super(
                Titles.filterContainsName,
                Descriptions.filterContainsName,
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
                throw new Exception("Не укзано имя");
            return this.collectionManager.filterContainsName(params[0].toString());
        }
        return null;
    }


}
