package com.google.appinventor.components.runtime;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class EventDispatcher {
  private static final boolean DEBUG = false;
  
  private static final Map<HandlesEventDispatching, EventRegistry> mapDispatchDelegateToEventRegistry = new HashMap<HandlesEventDispatching, EventRegistry>();
  
  private static boolean delegateDispatchEvent(HandlesEventDispatching paramHandlesEventDispatching, Set<EventClosure> paramSet, Component paramComponent, Object... paramVarArgs) {
    boolean bool = false;
    for (EventClosure eventClosure : paramSet) {
      if (paramHandlesEventDispatching.dispatchEvent(paramComponent, eventClosure.componentId, eventClosure.eventName, paramVarArgs))
        bool = true; 
    } 
    return bool;
  }
  
  public static boolean dispatchEvent(Component paramComponent, String paramString, Object... paramVarArgs) {
    boolean bool1 = false;
    boolean bool2 = false;
    HandlesEventDispatching handlesEventDispatching = paramComponent.getDispatchDelegate();
    if (handlesEventDispatching.canDispatchEvent(paramComponent, paramString)) {
      Set<EventClosure> set = (Set)(getEventRegistry(handlesEventDispatching)).eventClosuresMap.get(paramString);
      bool1 = bool2;
      if (set != null) {
        bool1 = bool2;
        if (set.size() > 0)
          bool1 = delegateDispatchEvent(handlesEventDispatching, set, paramComponent, paramVarArgs); 
      } 
      if (!bool1) {
        bool2 = true;
      } else {
        bool2 = false;
      } 
      handlesEventDispatching.dispatchGenericEvent(paramComponent, paramString, bool2, paramVarArgs);
    } 
    return bool1;
  }
  
  private static EventRegistry getEventRegistry(HandlesEventDispatching paramHandlesEventDispatching) {
    EventRegistry eventRegistry2 = mapDispatchDelegateToEventRegistry.get(paramHandlesEventDispatching);
    EventRegistry eventRegistry1 = eventRegistry2;
    if (eventRegistry2 == null) {
      eventRegistry1 = new EventRegistry(paramHandlesEventDispatching);
      mapDispatchDelegateToEventRegistry.put(paramHandlesEventDispatching, eventRegistry1);
    } 
    return eventRegistry1;
  }
  
  public static String makeFullEventName(String paramString1, String paramString2) {
    return paramString1 + '$' + paramString2;
  }
  
  public static void registerEventForDelegation(HandlesEventDispatching paramHandlesEventDispatching, String paramString1, String paramString2) {
    EventRegistry eventRegistry = getEventRegistry(paramHandlesEventDispatching);
    Set<EventClosure> set2 = (Set)eventRegistry.eventClosuresMap.get(paramString2);
    Set<EventClosure> set1 = set2;
    if (set2 == null) {
      set1 = new HashSet();
      eventRegistry.eventClosuresMap.put(paramString2, set1);
    } 
    set1.add(new EventClosure(paramString1, paramString2));
  }
  
  public static void removeDispatchDelegate(HandlesEventDispatching paramHandlesEventDispatching) {
    EventRegistry eventRegistry = removeEventRegistry(paramHandlesEventDispatching);
    if (eventRegistry != null)
      eventRegistry.eventClosuresMap.clear(); 
  }
  
  private static EventRegistry removeEventRegistry(HandlesEventDispatching paramHandlesEventDispatching) {
    return mapDispatchDelegateToEventRegistry.remove(paramHandlesEventDispatching);
  }
  
  public static void unregisterAllEventsForDelegation() {
    Iterator<EventRegistry> iterator = mapDispatchDelegateToEventRegistry.values().iterator();
    while (iterator.hasNext())
      (iterator.next()).eventClosuresMap.clear(); 
  }
  
  public static void unregisterEventForDelegation(HandlesEventDispatching paramHandlesEventDispatching, String paramString1, String paramString2) {
    Set set = (Set)(getEventRegistry(paramHandlesEventDispatching)).eventClosuresMap.get(paramString2);
    if (set != null && !set.isEmpty()) {
      HashSet<EventClosure> hashSet = new HashSet();
      for (EventClosure eventClosure : set) {
        if (eventClosure.componentId.equals(paramString1))
          hashSet.add(eventClosure); 
      } 
      Iterator<EventClosure> iterator = hashSet.iterator();
      while (true) {
        if (iterator.hasNext()) {
          set.remove(iterator.next());
          continue;
        } 
        return;
      } 
    } 
  }
  
  private static final class EventClosure {
    private final String componentId;
    
    private final String eventName;
    
    private EventClosure(String param1String1, String param1String2) {
      this.componentId = param1String1;
      this.eventName = param1String2;
    }
    
    public boolean equals(Object param1Object) {
      if (this != param1Object) {
        if (param1Object == null || getClass() != param1Object.getClass())
          return false; 
        param1Object = param1Object;
        if (!this.componentId.equals(((EventClosure)param1Object).componentId))
          return false; 
        if (!this.eventName.equals(((EventClosure)param1Object).eventName))
          return false; 
      } 
      return true;
    }
    
    public int hashCode() {
      return this.eventName.hashCode() * 31 + this.componentId.hashCode();
    }
  }
  
  private static final class EventRegistry {
    private final HandlesEventDispatching dispatchDelegate;
    
    private final HashMap<String, Set<EventDispatcher.EventClosure>> eventClosuresMap = new HashMap<String, Set<EventDispatcher.EventClosure>>();
    
    EventRegistry(HandlesEventDispatching param1HandlesEventDispatching) {
      this.dispatchDelegate = param1HandlesEventDispatching;
    }
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/EventDispatcher.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */