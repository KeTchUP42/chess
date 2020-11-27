 ## Пример настроек: 
   * *Блок ColorSettings позволяет переопределить цвета в GameColors.*  
   * *Для log_file_path - предварительно нужно создать структуру каталогов.*
   * *The turn sequence depends on the player’s color.*
    
    [AreaSettings]
    area_type         = standard
    
    [StepOrderSettings]
    step_order        = standard

    [FirstPlayerSettings]
    first_player_type  = bot_0
    first_player_name  = BOT

    [SecondPlayerSettings]
    second_player_type = bot_0
    second_player_name = BOT

    [LogSettings]
    log_file_path      = var/logs/source.log

    [ColorSettings]
    first_color       = white
    second_color      = black