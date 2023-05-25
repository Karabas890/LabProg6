package Commands;

import Models.CollectionManager;
import Models.SpaceMarine;

public class CommandAddIfMin extends Command {

    public CommandAddIfMin(CollectionManager collectionManager) {
        super(
                Titles.addIfMin,
                Descriptions.addIfMin,
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
            return collectionManager.addIfMin((SpaceMarine) params[0]);
        } else {
            return null;
        }
    }


}
