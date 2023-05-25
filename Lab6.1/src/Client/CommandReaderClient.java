package Client;

import Commands.Command;
import Commands.CommandReader;
import Models.CollectionManager;
import Models.SpaceMarine;
import Models.WeaponType;
import Server.Data;

import java.io.InputStream;
import java.util.List;

public class CommandReaderClient extends CommandReader {
    /**
     * Конструктор CommandReader.
     *
     * @param inputStream входной поток для чтения команд
     */
    public CommandReaderClient(InputStream inputStream) {
        super(null, inputStream);
    }

    /**
     * Выбполняет команду с несколькими параметрами в виде объектов
     *
     * @param commandName имя команды
     * @param params      параметры
     * @return Объект с результатом
     * @throws Exception
     */
    @Override
    public Data[] Execute(String commandName, Object[] params) throws Exception {
        Command currentCommand = this.commandHelp.GetCommand(commandName);
        if (currentCommand == null) {
            throw new Exception("Получена несуществующая команда");
        }
        //region Ничего не возвращают
        if (commandName.equals(Command.Titles.exit)) {
            currentCommand.Execute();
            return null;
        }
        //endregion
        //region Возвращают Data
        if (commandName.equals(Command.Titles.save))
            return new Data[]{new Data(currentCommand, false)};
        if (commandName.equals(Command.Titles.clear) || commandName.equals(Command.Titles.removeHead) ||
                commandName.equals(Command.Titles.help) || commandName.equals(Command.Titles.history) ||
                commandName.equals(Command.Titles.show) || commandName.equals(Command.Titles.info) ||
                commandName.equals(Command.Titles.countLessThanHealth) || commandName.equals(Command.Titles.printFieldDescendingWeaponType) ||
                commandName.equals(Command.Titles.filterContainsName))
            return new Data[]{new Data(currentCommand, null)};
        if (commandName.equals(Command.Titles.removeById))
            return new Data[]{new Data(currentCommand, params[0])};
        //if(commandName.equals(Command.Titles.executeScript))
         //   return new Data[]{new Data(currentCommand, params[0])};
        if (commandName.equals(Command.Titles.add)) {
            this.UpdateReader();
            return new Data[]{new Data(currentCommand, this.inputReader.GetSpaceMarine())};
        }
        if (commandName.equals(Command.Titles.update)) {
            this.UpdateReader();
            return new Data[]{new Data(currentCommand, new Object[]{
                    this.inputReader.GetInt("Введите ID объекта, который хотите обновить"),
                    this.inputReader.GetSpaceMarine()}
            )};
        }
        if (commandName.equals(Command.Titles.addIfMin)) {
            this.UpdateReader();
            return new Data[]{new Data(currentCommand, this.inputReader.GetSpaceMarineWithId())};
        }
        //endregion
        return null;
    }

    public Object Execute(String commandName, String params) throws Exception {
        return null;
    }


}
