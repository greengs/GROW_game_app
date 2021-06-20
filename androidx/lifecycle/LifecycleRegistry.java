package androidx.lifecycle;

import android.util.Log;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.arch.core.internal.FastSafeIterableMap;
import androidx.arch.core.internal.SafeIterableMap;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class LifecycleRegistry extends Lifecycle {
  private static final String LOG_TAG = "LifecycleRegistry";
  
  private int mAddingObserverCounter = 0;
  
  private boolean mHandlingEvent = false;
  
  private final WeakReference<LifecycleOwner> mLifecycleOwner;
  
  private boolean mNewEventOccurred = false;
  
  private FastSafeIterableMap<LifecycleObserver, ObserverWithState> mObserverMap = new FastSafeIterableMap();
  
  private ArrayList<Lifecycle.State> mParentStates = new ArrayList<Lifecycle.State>();
  
  private Lifecycle.State mState;
  
  public LifecycleRegistry(@NonNull LifecycleOwner paramLifecycleOwner) {
    this.mLifecycleOwner = new WeakReference<LifecycleOwner>(paramLifecycleOwner);
    this.mState = Lifecycle.State.INITIALIZED;
  }
  
  private void backwardPass(LifecycleOwner paramLifecycleOwner) {
    Iterator<Map.Entry> iterator = this.mObserverMap.descendingIterator();
    while (iterator.hasNext() && !this.mNewEventOccurred) {
      Map.Entry entry = iterator.next();
      ObserverWithState observerWithState = (ObserverWithState)entry.getValue();
      while (observerWithState.mState.compareTo(this.mState) > 0 && !this.mNewEventOccurred && this.mObserverMap.contains(entry.getKey())) {
        Lifecycle.Event event = downEvent(observerWithState.mState);
        pushParentState(getStateAfter(event));
        observerWithState.dispatchEvent(paramLifecycleOwner, event);
        popParentState();
      } 
    } 
  }
  
  private Lifecycle.State calculateTargetState(LifecycleObserver paramLifecycleObserver) {
    Map.Entry entry = this.mObserverMap.ceil(paramLifecycleObserver);
    if (entry != null) {
      Lifecycle.State state1 = ((ObserverWithState)entry.getValue()).mState;
    } else {
      entry = null;
    } 
    if (!this.mParentStates.isEmpty()) {
      Lifecycle.State state1 = this.mParentStates.get(this.mParentStates.size() - 1);
      return min(min(this.mState, (Lifecycle.State)entry), state1);
    } 
    Lifecycle.State state = null;
    return min(min(this.mState, (Lifecycle.State)entry), state);
  }
  
  private static Lifecycle.Event downEvent(Lifecycle.State paramState) {
    switch (paramState) {
      default:
        throw new IllegalArgumentException("Unexpected state value " + paramState);
      case INITIALIZED:
        throw new IllegalArgumentException();
      case CREATED:
        return Lifecycle.Event.ON_DESTROY;
      case STARTED:
        return Lifecycle.Event.ON_STOP;
      case RESUMED:
        return Lifecycle.Event.ON_PAUSE;
      case DESTROYED:
        break;
    } 
    throw new IllegalArgumentException();
  }
  
  private void forwardPass(LifecycleOwner paramLifecycleOwner) {
    SafeIterableMap.IteratorWithAdditions<Map.Entry> iteratorWithAdditions = this.mObserverMap.iteratorWithAdditions();
    while (iteratorWithAdditions.hasNext() && !this.mNewEventOccurred) {
      Map.Entry entry = iteratorWithAdditions.next();
      ObserverWithState observerWithState = (ObserverWithState)entry.getValue();
      while (observerWithState.mState.compareTo(this.mState) < 0 && !this.mNewEventOccurred && this.mObserverMap.contains(entry.getKey())) {
        pushParentState(observerWithState.mState);
        observerWithState.dispatchEvent(paramLifecycleOwner, upEvent(observerWithState.mState));
        popParentState();
      } 
    } 
  }
  
  static Lifecycle.State getStateAfter(Lifecycle.Event paramEvent) {
    switch (paramEvent) {
      default:
        throw new IllegalArgumentException("Unexpected event value " + paramEvent);
      case INITIALIZED:
      case CREATED:
        return Lifecycle.State.CREATED;
      case STARTED:
      case RESUMED:
        return Lifecycle.State.STARTED;
      case DESTROYED:
        return Lifecycle.State.RESUMED;
      case null:
        break;
    } 
    return Lifecycle.State.DESTROYED;
  }
  
  private boolean isSynced() {
    if (this.mObserverMap.size() == 0)
      return true; 
    Lifecycle.State state1 = ((ObserverWithState)this.mObserverMap.eldest().getValue()).mState;
    Lifecycle.State state2 = ((ObserverWithState)this.mObserverMap.newest().getValue()).mState;
    return (state1 == state2 && this.mState == state2);
  }
  
  static Lifecycle.State min(@NonNull Lifecycle.State paramState1, @Nullable Lifecycle.State paramState2) {
    return (paramState2 != null && paramState2.compareTo(paramState1) < 0) ? paramState2 : paramState1;
  }
  
  private void moveToState(Lifecycle.State paramState) {
    if (this.mState == paramState)
      return; 
    this.mState = paramState;
    if (this.mHandlingEvent || this.mAddingObserverCounter != 0) {
      this.mNewEventOccurred = true;
      return;
    } 
    this.mHandlingEvent = true;
    sync();
    this.mHandlingEvent = false;
  }
  
  private void popParentState() {
    this.mParentStates.remove(this.mParentStates.size() - 1);
  }
  
  private void pushParentState(Lifecycle.State paramState) {
    this.mParentStates.add(paramState);
  }
  
  private void sync() {
    LifecycleOwner lifecycleOwner = this.mLifecycleOwner.get();
    if (lifecycleOwner == null) {
      Log.w("LifecycleRegistry", "LifecycleOwner is garbage collected, you shouldn't try dispatch new events from it.");
      return;
    } 
    while (!isSynced()) {
      this.mNewEventOccurred = false;
      if (this.mState.compareTo(((ObserverWithState)this.mObserverMap.eldest().getValue()).mState) < 0)
        backwardPass(lifecycleOwner); 
      Map.Entry entry = this.mObserverMap.newest();
      if (!this.mNewEventOccurred && entry != null && this.mState.compareTo(((ObserverWithState)entry.getValue()).mState) > 0)
        forwardPass(lifecycleOwner); 
    } 
    this.mNewEventOccurred = false;
  }
  
  private static Lifecycle.Event upEvent(Lifecycle.State paramState) {
    switch (paramState) {
      default:
        throw new IllegalArgumentException("Unexpected state value " + paramState);
      case INITIALIZED:
      case DESTROYED:
        return Lifecycle.Event.ON_CREATE;
      case CREATED:
        return Lifecycle.Event.ON_START;
      case STARTED:
        return Lifecycle.Event.ON_RESUME;
      case RESUMED:
        break;
    } 
    throw new IllegalArgumentException();
  }
  
  public void addObserver(@NonNull LifecycleObserver paramLifecycleObserver) {
    Lifecycle.State state;
    if (this.mState == Lifecycle.State.DESTROYED) {
      state = Lifecycle.State.DESTROYED;
    } else {
      state = Lifecycle.State.INITIALIZED;
    } 
    ObserverWithState observerWithState = new ObserverWithState(paramLifecycleObserver, state);
    if ((ObserverWithState)this.mObserverMap.putIfAbsent(paramLifecycleObserver, observerWithState) == null) {
      LifecycleOwner lifecycleOwner = this.mLifecycleOwner.get();
      if (lifecycleOwner != null) {
        boolean bool;
        if (this.mAddingObserverCounter != 0 || this.mHandlingEvent) {
          bool = true;
        } else {
          bool = false;
        } 
        state = calculateTargetState(paramLifecycleObserver);
        this.mAddingObserverCounter++;
        while (observerWithState.mState.compareTo(state) < 0 && this.mObserverMap.contains(paramLifecycleObserver)) {
          pushParentState(observerWithState.mState);
          observerWithState.dispatchEvent(lifecycleOwner, upEvent(observerWithState.mState));
          popParentState();
          state = calculateTargetState(paramLifecycleObserver);
        } 
        if (!bool)
          sync(); 
        this.mAddingObserverCounter--;
        return;
      } 
    } 
  }
  
  @NonNull
  public Lifecycle.State getCurrentState() {
    return this.mState;
  }
  
  public int getObserverCount() {
    return this.mObserverMap.size();
  }
  
  public void handleLifecycleEvent(@NonNull Lifecycle.Event paramEvent) {
    moveToState(getStateAfter(paramEvent));
  }
  
  @MainThread
  public void markState(@NonNull Lifecycle.State paramState) {
    moveToState(paramState);
  }
  
  public void removeObserver(@NonNull LifecycleObserver paramLifecycleObserver) {
    this.mObserverMap.remove(paramLifecycleObserver);
  }
  
  static class ObserverWithState {
    GenericLifecycleObserver mLifecycleObserver;
    
    Lifecycle.State mState;
    
    ObserverWithState(LifecycleObserver param1LifecycleObserver, Lifecycle.State param1State) {
      this.mLifecycleObserver = Lifecycling.getCallback(param1LifecycleObserver);
      this.mState = param1State;
    }
    
    void dispatchEvent(LifecycleOwner param1LifecycleOwner, Lifecycle.Event param1Event) {
      Lifecycle.State state = LifecycleRegistry.getStateAfter(param1Event);
      this.mState = LifecycleRegistry.min(this.mState, state);
      this.mLifecycleObserver.onStateChanged(param1LifecycleOwner, param1Event);
      this.mState = state;
    }
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/lifecycle/LifecycleRegistry.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */