package Client;

import Application.InputReader;
import Commands.Command;
import Commands.CommandHelp;
import Models.CollectionManager;
import Server.CommandReaderServer;
import Server.Data;

import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;
import java.util.Objects;
import java.util.Scanner;
import Models.*;
import Commands.*;
//import org.junit.rules.Timeout;

/**
 * Класс UDPClient представляет клиента для обмена командами с сервером.
 */
public class UDPClient {

    //region Поля
    /**
     * Канал для отправки и получения сообщений от сервера.
     */
    private DatagramChannel channel;
     CollectionManager collectionManager;

    {
        try {
            collectionManager = new CollectionManager("data.json");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Сканер для чтения команд из консоли.
     */
    private Scanner scanner;

    /**
     * Распознает и выполняет команды, передеанные клиенту
     */
    private CommandReaderClient commandReader;
    //endregion

    //region Конструкторы

    /**
     * Конструктор по умолчанию инициализирует сканер для чтения команд.
     */
    public UDPClient() throws Exception {
        this(System.in);
    }

    public UDPClient(InputStream inputStream) throws Exception {
        this.scanner = new Scanner(inputStream);
        this.commandReader = new CommandReaderClient(inputStream);
    }
    //endregion

    //region Методы

    /**
     * Выводит указаный объект в консоль.
     *
     * @param object Объект для вывода.
     */
    void Print(Object object) {
        System.out.println(object);
    }

    /**
     * Запускает клиента и ждет ввода команд от пользователя.
     */
    public void Start() throws Exception {
        channel = DatagramChannel.open();
        channel.bind(null);

        channel.configureBlocking(false);
        Selector selector = Selector.open();
        channel.register(selector, SelectionKey.OP_READ);

        System.out.println("Клиент запущен...");

        while (true) {
            try {

                System.out.println("Введите команду или 'exit', чтобы выйти:");

                String commandName = scanner.nextLine().trim();  // Удаление лишних пробелов в начале и конце строки
                String[] words = commandName.split("\\s+");  // Разбиение строки на отдельные слова на основе пробелов
                if (Objects.equals(words[0], "execute_script")) {


                 //   System.out.println("aboba");
                    if (words.length != 2) {
                        System.out.println("Неверное количество аргументов");
                    } else {
                        String path = words[1];
                       // System.out.println(words[1]);
                        String line;
                        //   InputReader inputReader = null;
                        //  String firstPart = null;
                        Scanner scanner = new Scanner(new File(path));
                        CollectionManager collectionManager = null;
                        while (scanner.hasNext()) {
                            line = scanner.nextLine();
                           // System.out.println(line);
                            String[] arg = line.split("\\s+");
                            switch (arg[0]) {
                                case "add": {
                                    String line0 = scanner.nextLine();
                                   // System.out.println(line0);
                                    String name2 = line0;
                                    String line1 = scanner.nextLine();
                                  //  System.out.println(line1);
                                    float x1 = Float.parseFloat(line1);
                                    String line2 = scanner.nextLine();
                                    int y1 = Integer.parseInt(line2);
                                    String line3 = scanner.nextLine();
                                    int health1 = Integer.parseInt(line3);
                                    String line4 = scanner.nextLine();
                                    String achievements = line4;
                                    String line5 = scanner.nextLine();
                                    AstartesCategory AstartesCategory1 = this.commandReader.inputReader.GetEnumValue(AstartesCategory.class, line5);
                                    String line6 = scanner.nextLine();
                                    WeaponType weapon1 = this.commandReader.inputReader.GetEnumValue(WeaponType.class, line6);
                                    String line7 = scanner.nextLine();
                                    String chapterName1 = line7;
                                    String line8 = scanner.nextLine();
                                    String parentLegion1 = line8;
                                    SpaceMarine spaceMarineAdd = new SpaceMarine();
                                    // System.out.println("мы тут");
                                    spaceMarineAdd = this.commandReader.inputReader.GetSpaceMarine(name2, x1, y1, health1, achievements, AstartesCategory1,
                                            weapon1, chapterName1, parentLegion1);
                                    // System.out.println("мы тут1");
                                    //System.out.println(spaceMarineAdd);
                                    CommandAdd commandAdd = new CommandAdd(collectionManager);
                                    Data data = new Data(commandAdd, spaceMarineAdd);
                                    Send(data);
                                    Thread.sleep(10);
                                    Receive();
                                    break;


                                }
                                case "help": {
                                    CommandHelp CommandHelp = new CommandHelp(collectionManager);
                                    Data data = new Data(CommandHelp, null);
                                    Send(data);
                                    Thread.sleep(10);
                                    Receive();
                                    break;
                                }
                                case "info": {
                                    CommandInfo CommandInfo = new CommandInfo(collectionManager);
                                    Data data = new Data(CommandInfo, null);
                                    Send(data);
                                    Thread.sleep(10);
                                    Receive();
                                    break;
                                }
                                case "show": {
                                    CommandShow CommandShow = new CommandShow(collectionManager);
                                    Data data = new Data(CommandShow, null);
                                    Send(data);
                                    Thread.sleep(10);
                                    Receive();
                                    break;
                                }
                                case "clear": {
                                    CommandClear CommandClear = new CommandClear(collectionManager);
                                    Data data = new Data(CommandClear, null);
                                    Send(data);
                                    Thread.sleep(10);
                                    Receive();
                                    break;
                                }
                                case "remove_head": {
                                    CommandRemoveHead CommandRemoveHead = new CommandRemoveHead(collectionManager);
                                    Data data = new Data(CommandRemoveHead, null);
                                    Send(data);
                                    Thread.sleep(10);
                                    Receive();
                                    break;
                                }
                                case "history": {
                                    CommandHistory CommandHistory = new CommandHistory(collectionManager);
                                    Data data = new Data(CommandHistory, null);
                                    Send(data);
                                    Thread.sleep(10);
                                    Receive();
                                    break;
                                }
                                case "filter_contains_name": {
                                    CommandFilterContainsName CommandFilterContainsName = new CommandFilterContainsName(collectionManager);
                                    Data data = new Data(CommandFilterContainsName, arg[1]);
                                    Send(data);
                                    Thread.sleep(10);
                                    break;
                                }
                                case "removeById": {

                                    CommandRemoveById CommandRemoveById = new CommandRemoveById(collectionManager);
                                    Data data = new Data(CommandRemoveById, arg[1]);
                                    Send(data);
                                    Thread.sleep(10);
                                    Receive();
                                    break;
                                }
                                case "count_less_than_health": {

                                    CommandCountLessThanHealth CommandCountLessThanHealth = new CommandCountLessThanHealth(collectionManager);
                                    Data data = new Data(CommandCountLessThanHealth, arg[1]);
                                    Send(data);
                                    Thread.sleep(10);
                                    Receive();
                                    break;
                                }
                                case "print_field_descending_weapon_type": {

                                    CommandPrintFieldDescendingWeaponType CommandPrintFieldDescendingWeaponType = new CommandPrintFieldDescendingWeaponType(collectionManager);
                                    Data data = new Data(CommandPrintFieldDescendingWeaponType, null);
                                    Send(data);
                                    Thread.sleep(10);
                                    Receive();
                                    break;
                                }
                                case "update": {

                                    String line0 = scanner.nextLine();
                                    // System.out.println(line0);
                                    String name2 = line0;
                                    String line1 = scanner.nextLine();
                                    //  System.out.println(line1);
                                    float x1 = Float.parseFloat(line1);
                                    String line2 = scanner.nextLine();
                                    int y1 = Integer.parseInt(line2);
                                    String line3 = scanner.nextLine();
                                    int health1 = Integer.parseInt(line3);
                                    String line4 = scanner.nextLine();
                                    String achievements = line4;
                                    String line5 = scanner.nextLine();
                                    AstartesCategory AstartesCategory1 = this.commandReader.inputReader.GetEnumValue(AstartesCategory.class, line5);
                                    String line6 = scanner.nextLine();
                                    WeaponType weapon1 = this.commandReader.inputReader.GetEnumValue(WeaponType.class, line6);
                                    String line7 = scanner.nextLine();
                                    String chapterName1 = line7;
                                    String line8 = scanner.nextLine();
                                    String parentLegion1 = line8;
                                    SpaceMarine spaceMarineAdd = new SpaceMarine();
                                    // System.out.println("мы тут");
                                    spaceMarineAdd = this.commandReader.inputReader.GetSpaceMarine(name2, x1, y1, health1, achievements, AstartesCategory1,
                                            weapon1, chapterName1, parentLegion1);
                                    // System.out.println("мы тут1");
                                    //System.out.println(spaceMarineAdd);
                                    CommandUpdate CommandUpdate = new CommandUpdate(collectionManager);
                                    Data data = new Data(CommandUpdate,new Object[]{arg[1], spaceMarineAdd});
                                    Send(data);
                                    Thread.sleep(10);
                                    Receive();
                                    break;
                                }
                                case "add_if_min": {

                                    String line0 = scanner.nextLine();
                                    // System.out.println(line0);
                                    String name2 = line0;
                                    String line1 = scanner.nextLine();
                                    //  System.out.println(line1);
                                    float x1 = Float.parseFloat(line1);
                                    String line2 = scanner.nextLine();
                                    int y1 = Integer.parseInt(line2);
                                    String line3 = scanner.nextLine();
                                    int health1 = Integer.parseInt(line3);
                                    String line4 = scanner.nextLine();
                                    String achievements = line4;
                                    String line5 = scanner.nextLine();
                                    AstartesCategory AstartesCategory1 = this.commandReader.inputReader.GetEnumValue(AstartesCategory.class, line5);
                                    String line6 = scanner.nextLine();
                                    WeaponType weapon1 = this.commandReader.inputReader.GetEnumValue(WeaponType.class, line6);
                                    String line7 = scanner.nextLine();
                                    String chapterName1 = line7;
                                    String line8 = scanner.nextLine();
                                    String parentLegion1 = line8;
                                    SpaceMarine spaceMarineAdd = new SpaceMarine();
                                    // System.out.println("мы тут");
                                    spaceMarineAdd = this.commandReader.inputReader.GetSpaceMarine(name2, x1, y1, health1, achievements, AstartesCategory1,
                                            weapon1, chapterName1, parentLegion1);
                                    // System.out.println("мы тут1");
                                    //System.out.println(spaceMarineAdd);
                                    CommandAddIfMin CommandAddIfMin = new CommandAddIfMin(collectionManager);
                                    Data data = new Data(CommandAddIfMin,spaceMarineAdd);
                                    Send(data);
                                    Thread.sleep(10);
                                    Receive();
                                    break;
                                }
                                default: {
                                    System.out.println("В скрипте несуществующая команда");
                                    Thread.sleep(10);
                                    break;
                                }
                            }
                        }
                        System.out.println("Скрипт завершен");
                    }

                } else {
                    if (words.length == 1) {
                        if (commandName.equals(Command.Titles.exit))
                            return;
                        Data data1[] = this.commandReader.Execute(commandName, new Object[]{});
                        if (data1.length == 1) {
                            Send((data1[0]));
                        }
                    }
                    if (words.length > 1) {
                        String[] params = new String[words.length - 1];
                        System.arraycopy(words, 1, params, 0, words.length - 1);
                        Data data2[] = this.commandReader.Execute(words[0], params);
                        if (data2.length == 1) {
                            Send((data2[0]));
                        }

                    }
                    Receive();
                }
                } catch(Exception ex){
                    this.Print(ex.getMessage());
                }

        }
    }

    /**
     * Отправляет команду серверу.
     */
    private void Send(Data data) throws IOException {
        try {
            ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
            ObjectOutputStream objStream = new ObjectOutputStream(byteStream);

            objStream.writeObject(data);
            objStream.flush();

            ByteBuffer buffer = ByteBuffer.wrap(byteStream.toByteArray());

            channel.send(buffer, new InetSocketAddress("localhost", 8080));

        } catch (IOException e) {
            System.out.println("Сервер недоступен.");
            System.out.println(e.getMessage());
            // Здесь вы можете добавить действия, которые должны выполняться при недоступности сервера.
            // Например, вы можете прекратить работу клиента или попытаться переподключиться.
            //throw e; // Повторно бросить исключение, если вы хотите его обработать на более высоком уровне
        }
    }

    /**
     * Получает ответ от сервера.
     */
    private void Receive() throws IOException, ClassNotFoundException {
        ByteBuffer buffer = ByteBuffer.allocate(16384);
        Selector selector = this.channel.provider().openSelector();

        int interestSet = SelectionKey.OP_READ;
        this.channel.register(selector, interestSet);

        while (true) {
            if (selector.select(5000) == 0) {  // тайм-аут в миллисекундах
                throw new SocketTimeoutException("Тайм-аут: сервер недоступен или не отвечает");
            }

            Iterator keys = selector.selectedKeys().iterator();

            while (keys.hasNext()) {
                SelectionKey key = (SelectionKey) keys.next();

                if (!key.isValid()) {
                    continue;
                }

                if (key.isReadable()) {
                    SocketAddress serverAddress = channel.receive(buffer);

                    ByteArrayInputStream byteStream = new ByteArrayInputStream(buffer.array());
                    ObjectInputStream objStream = new ObjectInputStream(byteStream);

                    String response = (String) objStream.readObject();
                    System.out.println("Получен ответ от сервера: " + response);
                    keys.remove();
                    return;
                }
            }
        }
    }

    //endregion
}
