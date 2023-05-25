
package Commands;

import Models.CollectionManager;

import java.io.*;

public class CommandExecuteScript extends Command {

    public CommandExecuteScript(CollectionManager collectionManager) {
        super(
                Titles.executeScript,
                Descriptions.executeScript,
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
            String fileName = params[0].toString();
            FileInputStream fileInputStream = new FileInputStream(fileName);
            if (this.collectionManager.CheckExecuteScript(fileName)) {
                throw new Exception("Обнаружен рекурсивный вызов");
            }
            this.collectionManager.AddExecuteScript(fileName);
            CommandReader commandReader = new CommandReader(this.collectionManager, fileInputStream);
            commandReader.Start();
            this.collectionManager.RemoveExecuteScript(fileName);

        }
        return null;
    }

}

