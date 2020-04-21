 # BGS
 Небольшая система на основе которой сделаны шахматы, но можно реализовать и другие настольные игры.

 ### Точка входа
 Класс точка-входа находится в app/EntryPoint.java. При дальнейшем усовершенствовании основной класс Application можно использовать где-либо еще. 

 ## Пример стандартной области:
        
    | 56 | 57 | 58 | 59 | 60 | 61 | 62 | 63 |
    | 48 | 49 | 50 | 51 | 52 | 53 | 54 | 55 |
    | 40 | 41 | 42 | 43 | 44 | 45 | 46 | 47 |
    | 32 | 33 | 34 | 35 | 36 | 37 | 38 | 39 |
    | 24 | 25 | 26 | 27 | 28 | 29 | 30 | 31 |
    | 16 | 17 | 18 | 19 | 20 | 21 | 22 | 23 |
    | 08 | 09 | 10 | 11 | 12 | 13 | 14 | 15 |
    | 00 | 01 | 02 | 03 | 04 | 05 | 06 | 07 |

 ### Для просмотра или изменения последовательности запрашиваемых параметров:
 Смотрите пакет INI, потом AbstractApplicationBase.java.
 Для изменения последовательности параметров ручного ввода изменить массив входящих 
 параметром в Application.java, AbstractApplicationBase.java и ConfigFields.java. 

 ### Для добавления новых ботов: 
 Для добавления новых ботов нужно прописать их логику в brains/bots и 
 в AbstractApplicationBase.java добавить вариант выбора для создания бота.
 
 #### Пример: src/brains/bots/Bot_0.java

 ### Для добавления новых фигур или других игровых объектов:
 Новые классы объектов прописываются в каталоге objects. Предварительно для этой новой группы объектов создается родительский абстрактный класс который наследуется от aбстрактного класса в gameCore.
 Для добавления новых фигур или изменения их логики поведения
 изменять/добавлять классы фигур в пакет figures + добавлять методы
 в фабрику area/factory. В AbstractApplicationBase.java добавить вариант выбора для 
 создания конкретной конфигурации области.
 
 ### Для динамической замены объектов:
 Пример правильной замены пешки на королеву в методе isInRange:

     if (stepValid && (Board.getYCoordinate(SquareNumber) == Board.getAreaHeight() - 1 ||
             Board.getYCoordinate(SquareNumber) == 0)) {
              --------------------------
               Board.putObject(new Queen(this.squareNumber, this.color));
               return true;
              --------------------------
            }
              
 ### Для добавления нового визуала:
  Для добавления нового визуала добавлять классы исполняющие интерфейс IVisual в пакет visual. Создавать класс 
  Application с объектом нового визуала в конструкторе.
  Для изменения стандартного визуала заменить класс в пакете visual/standard. 

 ### Для добавления новых стандартных консольных команд:
  Для добавления новых стандартных консольных команд править метод consoleAction в standard/ConsoleVisual. 
   
 #### Пример настроек:
 
    [AreaSettings]
    areaType         = standard
    
    [FirstPlayerSettings]
    firstPlayerColor = standard
    firstPlayerType  = bot_0
    firstPlayerName  = BOT
    
    [SecondPlayerSettings]
    secondPlayerType = bot_0
    secondPlayerName = BOT
    
    [LogSettings]
    logFilePath      = var/log/source_log.txt
    
    [ColorSettings]
    FirstColor       = white
    SecondColor      = black
