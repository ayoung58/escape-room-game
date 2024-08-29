package model;

// The Keys abstract class provides methods and elements that all keys in the game should have
public abstract class Keys implements Item {
    protected boolean isUsed;
    // nothing to solve for with a single key
    boolean isInteractable = false;
    boolean isCollectable = true;
    // can combine with locks
    boolean isCombinable = true;

    @Override
    public boolean getInteractableStatus() {
        EventLog.getInstance().logEvent(new Event("Interactable filter filtered out a key."));
        return isInteractable; // stub
    }

    @Override
    public boolean getCollectableStatus() {
        return isCollectable; // stub
    }

    @Override
    public boolean getCombinableStatus() {
        EventLog.getInstance().logEvent(new Event("Combinable filter filtered to show a key."));
        return isCombinable; // stub
    }

    public abstract boolean isUsed();

    public abstract void setUsedState(boolean isUsed);
}
