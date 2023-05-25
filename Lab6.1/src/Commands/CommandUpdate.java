package Commands;

import Models.CollectionManager;
import Models.SpaceMarine;

public class CommandUpdate extends Command {

    public CommandUpdate(CollectionManager collectionManager) {
        super(
                Titles.update,
                Descriptions.update,
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
        if (this.CheckParams(params, 2)) {
            if (params[0] == null)
                throw new Exception("Не указан ID!");
            if (params[1] == null)
                throw new Exception("Не указан Морпех!");
            return this.collectionManager.update(Integer.parseInt(params[0].toString()), (SpaceMarine) params[1]);
        } else {
            return null;
        }
    }


}
