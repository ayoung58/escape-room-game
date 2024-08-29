package model;

import java.util.ArrayList;
import java.util.List;

// The Wall abstract class provides methods and elements that all walls in the game should have
public abstract class Walls implements Item {
    // TODO: delete all obsolete list of items fields and methods if not needed
    public abstract void addItems(Item i);

    public abstract void removeItems(Item i);
}
