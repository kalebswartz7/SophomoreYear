package teama;

import java.util.ArrayList;
import java.util.HashMap;

public class EventBus {
    /**
     * Maps the events to an event handler.
     */
    private static HashMap<String, ArrayList<EventHandler>> handlerTable = new HashMap<>();

    /**
     * Uses the eventName to find and execute the appropriate event handler.
     *
     * @param eventName The name of the event.
     * @param object    The data to be sent.
     */
    public static void fire(String eventName, Object object) {
        ArrayList<EventHandler> eventHandlers = handlerTable.get(eventName);
        if (eventHandlers != null) {
            for (EventHandler eventHandler : eventHandlers) {
                eventHandler.handleEvent(object);
            }
        }
    }

    /**
     * Add an event listener to the handler table.
     *
     * @param eventName    The event to listen for.
     * @param eventHandler The event handler
     */
    public static void addListener(String eventName, EventHandler eventHandler) {
        if (handlerTable.containsKey(eventName)) {
            handlerTable.get(eventName).add(eventHandler);
        } else {
            ArrayList<EventHandler> eventHandlers = new ArrayList<>();
            eventHandlers.add(eventHandler);
            handlerTable.put(eventName, eventHandlers);
        }
    }
}
