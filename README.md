# BGS
Небольшая система на основе которой сделаны шахматы, но можно реализовать и другие настольные игры.

### Точка входа
Класс точка-входа находится в app/EntryPoint.java. При дальнейшем усовершенствовании основной класс Application можно использовать где-либо еще. 

## Пример стандартной области в которой могут ноходиться объекты:
        
    | 56 | 57 | 58 | 59 | 60 | 61 | 62 | 63 |
    | 48 | 49 | 50 | 51 | 52 | 53 | 54 | 55 |
    | 40 | 41 | 42 | 43 | 44 | 45 | 46 | 47 |
    | 32 | 33 | 34 | 35 | 36 | 37 | 38 | 39 |
    | 24 | 25 | 26 | 27 | 28 | 29 | 30 | 31 |
    | 16 | 17 | 18 | 19 | 20 | 21 | 22 | 23 |
    | 08 | 09 | 10 | 11 | 12 | 13 | 14 | 15 |
    | 00 | 01 | 02 | 03 | 04 | 05 | 06 | 07 |

### Для просмотра или изменения последовательности параметров в массиве:
Смотрите файл INIParser.java, потом AbstractConfigSetter.java.
Для изменения последовательности параметров для ручного ввода изменить массив входящих параметром в AbstractApplication.java и AbstractConfigSetter.java. 

### Для добавления новых ботов: 
 Для добавления новых ботов нужно прописать их логику в brains/bots и 
 в AbstractConfigSetter.java добавить вариант выбора для создания бота.
#### Пример:
    /**
     * @return 0 - норма, 1 - проиграл, 2 - ход невозможен
     */
    @Override
    public int step() {
        if (this.scanner.isKingDead(this.Color)) {
            this.visual.sendMessage(this.nickName + " проиграл на " + this.stepNumber + " ходе.", false, false);
            return 1;
        }
        int index = 0;
        do {
            if (index == 1000) {
                this.visual.sendMessage(this.nickName + " проиграл на " + this.stepNumber + " ходе.", false, false);
                return 1;
            } else index++;

            while (true) {
                if (this.boardArea.moveObjectSafe((int) (Math.random() * this.boardArea.getMaxSquareNumber()),
                        (int) (Math.random() * this.boardArea.getMaxSquareNumber()),
                        this.Color))
                    break;
            }
        } while (this.scanner.kingUnderAttack(this.Color));
        return this.returnZero(2000);
    }
### Для добавления новых фигур или других игровых объектов:
 Новые классы объектов прописавыются в каталоге objects. Предварительно для этой новой группы объектов создается родительский абстрактный класс который наследуется от aбстрактного класса объекта в gameCore.
 Для добавления новых фигур или изменения их логики поведения
 изменять/добавлять классы фигур в пакет figures + добавлять методы
 в фабрику area/board/factory. В AbstractConfigSetter.java добавить вариант выбора для 
 создания конкретной конфигурации области.

 ### Для добавления нового визуала:
  Для добавления нового визуала добавлять классы исполняющие интерфейс IVisual в пакет visual. Создавать класс 
  Application с объектом нового визуала в конструкторе.
  Для изменения стандартного визуала заменить класс в пакете visual/standard. 

 ### Для добавления новых стандартных консольных комманд:
  Для добавления новых стандартных консольных комманд править метод consoleAction в standard/CommandLine. 
 ### Для изменения стандартной конфигурации:
 Изменять файл app/configs/config.ini

#### Настройки по умолчанию:
    
    [defaultSettings]

    firstPlayerColor = standard
    firstPlayerType  = bot_0
    firstPlayerNickName = Player_1

    secondPlayerType = bot_0
    secondPlayerNickName = BOT

    areaType     = standard

    [colorSettings]

    FirstColor   = white
    SecondColor  = black




