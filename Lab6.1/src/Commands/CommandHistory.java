package Commands;

import Models.CollectionManager;

public class CommandHistory extends Command {

    public CommandHistory(CollectionManager collectionManager) {
        super(
                Titles.history,
                Descriptions.history,

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
        StringBuilder stringBuilder = new StringBuilder();
        for (String item : this.collectionManager.history()) {
            stringBuilder.append(item);
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}
