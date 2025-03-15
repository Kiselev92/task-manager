package kiselev.anton.taskmanager.domain;

/**
 * Статус таски
 */
public enum TaskStatus {

    /** Таска создана (В ожидании обработки) */
    NEW,

    /** В процессе выполнения */
    IN_PROGRESS,

    /** Завершено */
    DONE
}
