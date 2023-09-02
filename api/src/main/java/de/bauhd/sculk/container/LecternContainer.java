package de.bauhd.sculk.container;

/**
 * Represents a container of a lectern block.
 */
public interface LecternContainer extends Container {

    void setPage(int page);

    int getPage();
}
