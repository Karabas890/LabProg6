package Commands;

import Models.CollectionManager;

public class CommandPrintFieldDescendingWeaponType extends Command {

    public CommandPrintFieldDescendingWeaponType(CollectionManager collectionManager) {
        super(
                Titles.printFieldDescendingWeaponType,
                Descriptions.printFieldDescendingWeaponType,
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
        if (params == null) {
            return this.collectionManager.printFieldDescendingWeaponType();
        }
        return null;
    }


}
