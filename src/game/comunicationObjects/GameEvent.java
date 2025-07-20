package game.comunicationObjects;

public sealed interface GameEvent permits 
    GameEvent.PauseEvent, GameEvent.ResumeEvent, GameEvent.ChinEvent, GameEvent.WinEvent, GameEvent.AttachmentFixedEvent,
    GameEvent.PlayCardEvent, GameEvent.StartGameEvent, GameEvent.NoEvent {

    record PauseEvent() implements GameEvent {}
    record ResumeEvent() implements GameEvent {}
    record ChinEvent(GameStateInfo gameStateInfo) implements GameEvent {}
    record StartGameEvent(GameStateInfo gameStateInfo) implements GameEvent {}
    record WinEvent(int winnerPlayerNumber) implements GameEvent {}
    record AttachmentFixedEvent(GameStateInfo gameStateInfo) implements GameEvent {}
    record PlayCardEvent(GameStateInfo gameStateInfo) implements GameEvent {}
    record NoEvent() implements GameEvent { }
}

