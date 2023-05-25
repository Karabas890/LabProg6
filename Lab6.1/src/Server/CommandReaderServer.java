package Server;

import Commands.Command;
import Commands.CommandReader;
import Models.CollectionManager;
import Models.SpaceMarine;
import Models.WeaponType;

import java.io.InputStream;
import java.util.List;

public class CommandReaderServer extends CommandReader {
    //region Конструкторы

    /**
     * Конструктор CommandReader.
     *
     * @param collectionManager менеджер коллекции
     * @param inputStream       входной поток для чтения команд
     */
    public CommandReaderServer(CollectionManager collectionManager, InputStream inputStream) {
        super(collectionManager, inputStream);
    }
    //endregion


    public Object Execute(String commandName, String params) throws Exception {
        return null;
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
    public Object Execute(String commandName, Object[] params) throws Exception {
        Command currentCommand = this.commandHelp.GetCommand(commandName);
        if (currentCommand == null) {
            throw new Exception("Получена несуществующая команда");
        }
        //region Ничего не возвращают
        if (commandName.equals(Command.Titles.clear) || commandName.equals(Command.Titles.removeHead) || commandName.equals(Command.Titles.exit))
            currentCommand.Execute(null);
        if (commandName.equals(Command.Titles.executeScript)) {
            if (params == null || params.length == 0)
                throw new Exception("Передано неверное число аргументов");
            currentCommand.Execute(params);
        }
        //endregion
        //region Возвращают строку
        if (commandName.equals(Command.Titles.help) || commandName.equals(Command.Titles.history) ||
                commandName.equals(Command.Titles.show) || commandName.equals(Command.Titles.info))
            return currentCommand.Execute(null);
        //endregion
        //region Возвращают число
        if (commandName.equals(Command.Titles.add)) {
            if (params == null || params.length == 0)
                throw new Exception("Передано неверное число аргументов");
            this.UpdateReader();
            Object result = currentCommand.Execute(params[0]);
            if (result == null)
                return "Не удалось добавить объект";
            else
                return String.format("Объект добавлен, его ID в коллекции:%d", Integer.valueOf(result.toString()));

        }
        if (commandName.equals(Command.Titles.countLessThanHealth)) {
            if (params == null || params.length == 0)
                throw new Exception("Передано неверное число аргументов");
            Object result = currentCommand.Execute(params[0]);
            if (result == null)
                return "Не удалось найти значение";
            else
                return String.format("Число объектов со здоровьем меньше указанного:%d", Integer.valueOf(result.toString()));
        }
        //endregion
        //region Возвращают булевское значение
        if (commandName.equals(Command.Titles.save)) {
            if (params == null || params.length == 0)
                throw new Exception("Передано неверное число аргументов");
            if ((Boolean.parseBoolean(params[0].toString()))) {
                Object result = currentCommand.Execute(null);
                if (result == null || !((boolean) result))
                    return "Не удалось сохранить коллекцию";
                else
                    return "Коллекция сохранена";
            } else {
                return "Сохранение может быть вызвано только на стороне сервера";
            }
        }
        if (commandName.equals(Command.Titles.update)) {
            if (params == null || params.length != 2)
                throw new Exception("Передано неверное число аргументов");
            this.UpdateReader();
            Object result = currentCommand.Execute(params);
            if (result == null || !((boolean) result))
                return "Не удалось обновить указанный элемент";
            else
                return "Объект успешно обновлен";
        }
        if (commandName.equals(Command.Titles.removeById)) {
            if (params == null || params.length != 1)
                throw new Exception("Передано неверное число аргументов");
            Object result = currentCommand.Execute(params);
            if (result == null || !((boolean) result))
                return "Не удалось удалить указанный элемент";
            else
                return "Объект успешно удален";
        }
        if (commandName.equals(Command.Titles.addIfMin)) {
            if (params == null || params.length != 1)
                throw new Exception("Передано неверное число аргументов");
            this.UpdateReader();
            Object result = currentCommand.Execute(params[0]);
            if (result == null || !((boolean) result))
                return "Не удалось добавить указанный элемент";
            else
                return "Объект успешно добавлен";
        }
        //endregion
        //region Возвращает список
        if (commandName.equals(Command.Titles.printFieldDescendingWeaponType)) {
            List<WeaponType> weaponTypes = (List<WeaponType>) currentCommand.Execute();
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < weaponTypes.size(); i++) {
                result.append((i + 1)).append(". ").append(weaponTypes.get(i)).append("\n");
            }
            return result.toString();
        }
        if (commandName.equals(Command.Titles.filterContainsName)) {
            List<SpaceMarine> marines = (List<SpaceMarine>) currentCommand.Execute(params);
            StringBuilder result = new StringBuilder();
            for (SpaceMarine marine : marines) {
                result.append(marine).append("\n");
            }
            return result.toString();
        }
        //endregion
        return null;
    }
}
