package app.src;

import area.factory.src.GameColors;
import brains.src.IPlayer;
import brains.src.rep.StepLog;
import visual.src.IVisual;

/**
 * @author Roman
 */
public abstract class AbstractApplication extends AbstractApplicationBase {

    /**
     * Задается визуал
     *
     * @param Visual Объект заключающий в себе функционал визуала
     */
    public AbstractApplication(IVisual Visual) {
        this.Visual = Visual;
    }

    /**
     * Запуск приложения
     *
     * @param configData Массив параметров
     */
    public void run(String[] configData) {
        try {
            this.applySettings(configData);
            this.runGame();
        } catch (Exception | Error e) {
            this.Visual.showMessage(e.getMessage(), false, false);
        } finally {
            this.Visual.showMessage("Завершение работы...", false, false);
        }
    }

    /**
     * Основной игровой цикл
     */
    protected void runGame() {
        //Решение порядка ходов
        boolean stepPriority = this.Players[0].getColor() == GameColors.firstStepColor;
        IPlayer firstPlayer = stepPriority ? this.Players[0] : this.Players[1];
        IPlayer secondPlayer = !stepPriority ? this.Players[0] : this.Players[1];
        while (true) {
            boolean exit = false;
            while (true) {
                this.Visual.Draw(this.Area);
                StepLog stepResult = firstPlayer.step();
                if (stepResult == StepLog.DEFEAT) {
                    exit = true;
                }
                if (stepResult == StepLog.STEP_IS_IMPOSSIBLE) continue;
                break;
            }
            if (exit) break;
            while (true) {
                this.Visual.Draw(this.Area);
                StepLog stepResult = secondPlayer.step();
                if (stepResult == StepLog.DEFEAT) {
                    exit = true;
                }
                if (stepResult == StepLog.STEP_IS_IMPOSSIBLE) continue;
                break;
            }
            if (exit) break;
        }
    }

}
