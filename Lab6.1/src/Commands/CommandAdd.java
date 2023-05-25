package Commands;

import Models.CollectionManager;
import Models.SpaceMarine;

public class CommandAdd extends Command {

    public CommandAdd(CollectionManager collectionManager) {
        super(
                Titles.add,
                Descriptions.add,
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
        if (this.CheckParams(params, 1)) {
            return collectionManager.add((SpaceMarine) params[0]);
        } else {
            return null;
        }
    }


}
