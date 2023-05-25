/*

package Client;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import Application.InputReader;
import Commands.Command;
import Commands.CommandAdd;
import Commands.CommandHelp;
import Commands.CommandReader;
import Models.AstartesCategory;
import Models.CollectionManager;
import Models.SpaceMarine;
import Models.WeaponType;
import Server.Data;
public class ReadFromFile {
    SpaceMarine spaceMarine;
    static InputReader inputReader;

    public static Data readCommandsFromFile(String nameOfFile, CollectionManager collectionManager) throws Exception {
        String line;
        String firstPart = null;
        Scanner scanner = new Scanner(nameOfFile);
        while (scanner.hasNext()) {
            line = scanner.next();
            switch (line) {
                case "add": {
                    String line0 = scanner.nextLine();
                    String name2 = line0;
                    String line1 = scanner.nextLine();
                    float x1 = Float.parseFloat(line1);
                    String line2 = scanner.nextLine();
                    int y1 = Integer.parseInt(line2);
                    String line3 = scanner.nextLine();
                    int health1 = Integer.parseInt(line3);
                    String line4 = scanner.nextLine();
                    String achievements = line4;
                    String line5 = scanner.nextLine();
                    AstartesCategory AstartesCategory1 = inputReader.GetEnumValue(AstartesCategory.class, line5);
                    String line6 = scanner.nextLine();
                    WeaponType weapon1 = inputReader.GetEnumValue(WeaponType.class, line6);
                    String line7 = scanner.nextLine();
                    String chapterName1 = line7;
                    String line8 = scanner.nextLine();
                    String parentLegion1 = line8;
                    SpaceMarine spaceMarineAdd = new SpaceMarine();
                    spaceMarineAdd = inputReader.GetSpaceMarine(name2, x1, y1, health1, achievements, AstartesCategory1,
                            weapon1, chapterName1, parentLegion1);
                    CommandAdd commandAdd = new CommandAdd(collectionManager);
                    Data data= new Data(commandAdd, spaceMarineAdd);



                } default:{
                    System.out.println("В скрипте несуществующая команда, скрипт приостоновлен");
                }
            }
        }
    }
}


*/
