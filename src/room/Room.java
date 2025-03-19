package data_files.room;

public enum Room {
    LIVING_ROOM("Living Room"),
    KITCHEN("Kitchen"),
    BEDROOM("Bedroom"),
    BATHROOM("Bathroom"),
    DINING_ROOM("Dining Room"),
    OFFICE("Office"),
    GUEST_ROOM("Guest Room"),
    LAUNDRY_ROOM("Laundry Room"),
    HALLWAY("Hallway"),
    BASEMENT("Basement"),
    ATTIC("Attic"),
    GARAGE("Garage");

    private final String caption;

    // Constructor
    Room(String caption) {
        this.caption = caption;
    }

    // Getter for the caption
    public String getCaption() {
        return caption;
    }
}
