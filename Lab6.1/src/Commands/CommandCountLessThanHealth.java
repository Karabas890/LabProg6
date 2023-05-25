package Commands;//package commands;
//
//import Models.CollectionManager;
//import data.SpaceMarine;
//

import Models.CollectionManager;

public class CommandCountLessThanHealth extends Command {

    public CommandCountLessThanHealth(CollectionManager collectionManager) {
        super(
                Titles.countLessThanHealth,
                Descriptions.countLessThanHealth,
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
                throw new Exception("Не укзано значение здоровья");
            return collectionManager.countLessThanHealth(Integer.parseInt(params[0].toString()));
        } else {
            return null;
        }
    }

}
